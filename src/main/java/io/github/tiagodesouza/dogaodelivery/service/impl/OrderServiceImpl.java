package io.github.tiagodesouza.dogaodelivery.service.impl;

import io.github.tiagodesouza.dogaodelivery.model.AdditionalIngredient;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Order;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Product;
import io.github.tiagodesouza.dogaodelivery.repository.OrderRepository;
import io.github.tiagodesouza.dogaodelivery.service.CalculateAmountService;
import io.github.tiagodesouza.dogaodelivery.service.IngredientService;
import io.github.tiagodesouza.dogaodelivery.service.OrderService;
import io.github.tiagodesouza.dogaodelivery.service.ProductService;
import io.github.tiagodesouza.dogaodelivery.service.PromotionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final IngredientService ingredientService;
    private final ProductService productService;
    private final PromotionManager promotionManager;
    private final CalculateAmountService calculateAmountService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, IngredientService ingredientService,
                            ProductService productService, PromotionManager promotionManager,
                            CalculateAmountService calculateAmountService) {
        this.orderRepository = orderRepository;
        this.ingredientService = ingredientService;
        this.productService = productService;
        this.promotionManager = promotionManager;
        this.calculateAmountService = calculateAmountService;
    }

    @Override
    public Order createOrder(Order order) {
        validateProductExist(order);
        validateAdditionalIngredientsExist(order);

        BigDecimal amount = calculateAmountService.calculateOrderAmount(order);
        order.setAmount(amount);

        BigDecimal discount = promotionManager.applyPromotions(order);
        order.setDiscount(discount);
        order.setTotalOrderAmount(amount.subtract(discount));

        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAllOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size))
                .getContent();
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private void validateProductExist(Order order) {
        List<OrderProduct> orderProducts = order.getOrderProducts().stream()
                .map(orderProduct -> {
                    Product product = productService.findProductByName(orderProduct.getProduct().getName());
                    return new OrderProduct(product, orderProduct.getAdditionalIngredients());
                }).collect(Collectors.toList());

        order.setOrderProducts(orderProducts);
    }

    private void validateAdditionalIngredientsExist(Order order) {
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            if (!orderProduct.getAdditionalIngredients().isEmpty()) {
                List<AdditionalIngredient> additionalIngredients = orderProduct.getAdditionalIngredients().stream()
                        .map(additionalIngredient -> {
                            Ingredient ingredient = ingredientService
                                    .findByName(additionalIngredient.getIngredient().getName());
                            return new AdditionalIngredient(ingredient, additionalIngredient.getQuantity());
                        }).collect(Collectors.toList());
                orderProduct.setAdditionalIngredients(additionalIngredients);
            }
        }
    }
}

package io.github.tiagodesouza.dogaodelivery.controller;

import io.github.tiagodesouza.dogaodelivery.controller.dto.request.OrderRequest;
import io.github.tiagodesouza.dogaodelivery.controller.dto.response.OrderResponse;
import io.github.tiagodesouza.dogaodelivery.model.Order;
import io.github.tiagodesouza.dogaodelivery.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        List<OrderResponse> orderResponses = orderService.findAllOrders(page, size)
                .stream().map(OrderResponse::new)
                .collect(Collectors.toList());

        if (orderResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable("id") Long id) {
        return orderService.findOrderById(id)
                .map(order -> ResponseEntity.ok(new OrderResponse(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}

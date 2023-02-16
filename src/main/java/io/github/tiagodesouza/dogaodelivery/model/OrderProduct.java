package io.github.tiagodesouza.dogaodelivery.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product = new Product();
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_product_id")
    private List<AdditionalIngredient> additionalIngredients = new ArrayList<>();

    public OrderProduct() {
    }

    public OrderProduct(Product product, List<AdditionalIngredient> additionalIngredients) {
        this.product = product;
        this.additionalIngredients = additionalIngredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<AdditionalIngredient> getAdditionalIngredients() {
        return additionalIngredients;
    }

    public void setAdditionalIngredients(List<AdditionalIngredient> additionalIngredients) {
        this.additionalIngredients = additionalIngredients;
    }
}

package io.github.tiagodesouza.dogaodelivery.model;

import io.github.tiagodesouza.dogaodelivery.model.enumerated.StatusPromotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.TypePromotion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @OneToOne
    private Ingredient includeIngredient;
    @OneToOne
    private Ingredient excludeIngredient;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_promotion", nullable = false)
    private TypePromotion typePromotion;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_promotion", nullable = false)
    private StatusPromotion statusPromotion;
    @Column(name = "discount_percentage")
    private Integer discountPercentage;
    @Column(name = "quantity")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ingredient getIncludeIngredient() {
        return includeIngredient;
    }

    public void setIncludeIngredient(Ingredient includeIngredient) {
        this.includeIngredient = includeIngredient;
    }

    public Ingredient getExcludeIngredient() {
        return excludeIngredient;
    }

    public void setExcludeIngredient(Ingredient excludeIngredient) {
        this.excludeIngredient = excludeIngredient;
    }

    public TypePromotion getTypePromotion() {
        return typePromotion;
    }

    public void setTypePromotion(TypePromotion typePromotion) {
        this.typePromotion = typePromotion;
    }

    public StatusPromotion getStatusPromotion() {
        return statusPromotion;
    }

    public void setStatusPromotion(StatusPromotion statusPromotion) {
        this.statusPromotion = statusPromotion;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

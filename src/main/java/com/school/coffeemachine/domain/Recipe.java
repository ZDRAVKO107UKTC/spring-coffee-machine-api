package com.school.coffeemachine.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "recipes", uniqueConstraints = @UniqueConstraint(name = "uk_recipe_name", columnNames = "name"))
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int waterMl;

    @Column(nullable = false)
    private int milkMl;

    @Column(nullable = false)
    private int beansG;

    @Column(nullable = false)
    private int sugarG;

    public Recipe() {}

    public Recipe(String name, BigDecimal price, int waterMl, int milkMl, int beansG, int sugarG) {
        this.name = name;
        this.price = price;
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.beansG = beansG;
        this.sugarG = sugarG;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getWaterMl() { return waterMl; }
    public int getMilkMl() { return milkMl; }
    public int getBeansG() { return beansG; }
    public int getSugarG() { return sugarG; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setWaterMl(int waterMl) { this.waterMl = waterMl; }
    public void setMilkMl(int milkMl) { this.milkMl = milkMl; }
    public void setBeansG(int beansG) { this.beansG = beansG; }
    public void setSugarG(int sugarG) { this.sugarG = sugarG; }
}

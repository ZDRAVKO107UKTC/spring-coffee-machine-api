package com.school.coffeemachine.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateRecipeRequest {
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @Min(0) private int waterMl;
    @Min(0) private int milkMl;
    @Min(0) private int beansG;
    @Min(0) private int sugarG;

    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getWaterMl() { return waterMl; }
    public int getMilkMl() { return milkMl; }
    public int getBeansG() { return beansG; }
    public int getSugarG() { return sugarG; }

    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setWaterMl(int waterMl) { this.waterMl = waterMl; }
    public void setMilkMl(int milkMl) { this.milkMl = milkMl; }
    public void setBeansG(int beansG) { this.beansG = beansG; }
    public void setSugarG(int sugarG) { this.sugarG = sugarG; }
}

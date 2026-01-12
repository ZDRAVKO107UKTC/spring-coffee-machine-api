package com.school.coffeemachine.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private Long id; // always 1

    @Column(nullable = false)
    private int waterMl;

    @Column(nullable = false)
    private int milkMl;

    @Column(nullable = false)
    private int beansG;

    @Column(nullable = false)
    private int sugarG;

    @Column(nullable = false)
    private int cups;

    public Inventory() {}

    public Inventory(Long id, int waterMl, int milkMl, int beansG, int sugarG, int cups) {
        this.id = id;
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.beansG = beansG;
        this.sugarG = sugarG;
        this.cups = cups;
    }

    public Long getId() { return id; }
    public int getWaterMl() { return waterMl; }
    public int getMilkMl() { return milkMl; }
    public int getBeansG() { return beansG; }
    public int getSugarG() { return sugarG; }
    public int getCups() { return cups; }

    public void setId(Long id) { this.id = id; }
    public void setWaterMl(int waterMl) { this.waterMl = waterMl; }
    public void setMilkMl(int milkMl) { this.milkMl = milkMl; }
    public void setBeansG(int beansG) { this.beansG = beansG; }
    public void setSugarG(int sugarG) { this.sugarG = sugarG; }
    public void setCups(int cups) { this.cups = cups; }
}

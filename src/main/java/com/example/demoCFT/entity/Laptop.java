package com.example.demoCFT.entity;

import javax.persistence.Entity;

@Entity
public class Laptop extends Product {
    private Integer screenSize;//размер экрана

    public Laptop() {
    }

    public Laptop(Integer serialNumber, String manufacturer, Double price, Integer amount, Integer screenSize) {
        super(serialNumber, manufacturer, price, amount);
        this.screenSize = screenSize;
    }

    public Integer getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Integer screenSize) {
        this.screenSize = screenSize;
    }
}

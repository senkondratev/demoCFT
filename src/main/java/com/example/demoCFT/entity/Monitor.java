package com.example.demoCFT.entity;

import javax.persistence.Entity;

@Entity
public class Monitor extends Product{
    private Double diagonal;//диагональ

    public Monitor(Integer serialNumber, String manufacturer, Double price, Integer amount, Double diagonal) {
        super(serialNumber, manufacturer, price, amount);
        this.diagonal = diagonal;
    }

    public Monitor() {
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Double diagonal) {
        this.diagonal = diagonal;
    }
}

package com.example.demoCFT.entity;


import javax.persistence.Entity;

@Entity
public class HardDrive extends Product {
    private Integer volume;//объем диска

    public HardDrive(Integer serialNumber, String manufacturer, Double price, Integer amount, Integer volume) {
        super(serialNumber, manufacturer, price, amount);
        this.volume = volume;
    }

    public HardDrive() {
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}

package com.example.demoCFT.entity;

import javax.persistence.Entity;

@Entity
public class PersonalComputer extends  Product{
    private String form;//форм-фактор

    public PersonalComputer(Integer serialNumber, String manufacturer, Double price, Integer amount, String form) {
        super(serialNumber, manufacturer, price, amount);
        this.form = form;
    }
    public PersonalComputer(){}

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}

package com.orcl.design_pattern.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lea
 * @description
 * @history 2024-05-26 20:22 created by lea
 * @since 2024-05-26 20:22
 */
@Getter
@AllArgsConstructor
public class Product implements BoxComponent {

    private String title;

    private double price;

    private double tax;

    private double freightInsurance;

    @Override
    public double calculatePrice() {
        return price + tax + freightInsurance;
    }
}

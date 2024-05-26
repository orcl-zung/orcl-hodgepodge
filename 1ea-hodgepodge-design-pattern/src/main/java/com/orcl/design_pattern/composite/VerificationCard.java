package com.orcl.design_pattern.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lea
 * @description
 * @history 2024-05-26 21:03 created by lea
 * @since 2024-05-26 21:03
 */
@Getter
@AllArgsConstructor
public class VerificationCard implements BoxComponent {

    private String title;

    private double price;

    @Override
    public double calculatePrice() {
        return price;
    }
}

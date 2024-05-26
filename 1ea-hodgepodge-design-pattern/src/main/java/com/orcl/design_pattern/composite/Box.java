package com.orcl.design_pattern.composite;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lea
 * @description
 * @history 2024-05-26 20:21 created by lea
 * @since 2024-05-26 20:21
 */
@Data
public class Box implements BoxComponent {

    private final List<BoxComponent> boxComponents = new ArrayList<>();

    public void add(BoxComponent boxComponent) {
        this.boxComponents.add(boxComponent);
    }

    @Override
    public double calculatePrice() {
        double sumPrice = 0;

        // 跨境商品
//        if (null != products) {
//            sumPrice += products.stream().mapToDouble(Product::calculatePrice).sum();
//        }
//
//        // 核销券
//        if (null != cards) {
//            sumPrice += cards.stream().mapToDouble(VerificationCard::calculatePrice).sum();
//        }
//
//        // 小包裹
//        if (null != boxes) {
//            sumPrice += boxes.stream().mapToDouble(Box::calculatePrice).sum();
//        }

        for (BoxComponent boxComponent : boxComponents) {
            sumPrice += boxComponent.calculatePrice();
        }

        return sumPrice;
    }
}

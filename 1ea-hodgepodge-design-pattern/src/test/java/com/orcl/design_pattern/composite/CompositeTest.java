package com.orcl.design_pattern.composite;

import com.orcl.design_pattern.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lea
 * @description
 * @history 2024-05-26 20:47 created by lea
 * @since 2024-05-26 20:47
 */
@Slf4j
public class CompositeTest extends ApplicationTest {

    @Test
    public void test() {

        Box box = constuctBox();

        double totalSum = calculatePrice(box);
        log.info("totalSum: {}", totalSum);
    }

    @Test
    public void test_2() {
        Box box = constuctBox();
        double totalSum = box.calculatePrice();
        log.info("totalSum = : {}", totalSum);
    }

    @Test
    public void test_3() {
        Box box = constuctBox();
        double totalSum = box.calculatePrice();
        log.info("totalSum is : {}", totalSum);
    }

    private double calculatePrice(Box box) {

        double sum = 0;

//        for (Product product : box.getProducts()) {
//            sum += product.getPrice() + product.getTax() + product.getFreightInsurance();
//        }
//
//        for (Box subBox : box.getBoxes()) {
//            sum += calculatePrice(subBox);
//        }
//
//        for (VerificationCard card : box.getCards()) {
//            sum += card.getPrice();
//        }

        return sum;
    }

    private Box constuctBox() {
//        Box box1 = new Box();
//        box1.setBoxes(Collections.emptyList());
//        box1.setProducts(Arrays.asList(new Product("iphone", 5000, 0, 0), new Product("篮球鞋", 4000, 0, 0)));
//
//        Box box2 = new Box();
//        box2.setBoxes(Collections.emptyList());
//        box2.setProducts(Arrays.asList(new Product("铅笔", 400, 0, 0), new Product("橡皮", 300, 0, 0)));
//
//        List<Box> subBoxes = Arrays.asList(box1, box2);
//
//        Box bigBox = new Box();
//        bigBox.setBoxes(subBoxes);
//        bigBox.setProducts(Arrays.asList(new Product("笔记本电脑", 10000, 0, 0), new Product("鼠标垫", 300, 0, 0)));
//        return bigBox;

        Box box1 = new Box();
        box1.add(new Product("iphone", 5000, 0, 0));
        box1.add(new Product("篮球鞋", 4000, 0, 0));

        Box box2 = new Box();
        box2.add(new Product("铅笔", 400, 0, 0));
        box2.add(new Product("橡皮", 300, 0, 0));
        box2.add(new VerificationCard("核销券", 3.1));

        Box bigBox = new Box();
        bigBox.add(box1);
        bigBox.add(box2);
        bigBox.add(new Product("笔记本电脑", 10000, 0, 0));
        bigBox.add(new Product("鼠标垫", 300, 1, 0));
        return bigBox;

    }

}

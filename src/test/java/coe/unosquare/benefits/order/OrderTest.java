/*
 *  OrderTest
 *  1.0
 *  11/8/22, 8:28 PM
 *  Copyright (c) 2022 Unosquare
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.unosquare.benefits.order;

import coe.unosquare.benefits.exception.EmptyOrderException;
import coe.unosquare.benefits.product.PaymentType;
import coe.unosquare.benefits.product.Product;
import coe.unosquare.benefits.product.ProductType;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static coe.unosquare.benefits.util.PayOrderSimulator.payOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest{

    @Test
    void orderWithVisaMoreThan10ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(15);
        assertEquals(0.15, payOrder(products, PaymentType.VISA));
    }

    @Test
    void orderWithVisa10ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(10);
        assertEquals(0.15, payOrder(products, PaymentType.VISA));
    }

    @Test
    void orderWithVisa7ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(7);
        assertEquals(0.10, payOrder(products, PaymentType.VISA));
    }

    @Test
    void orderWithVisaLessThan7ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(5);
        assertEquals(0.05, payOrder(products, PaymentType.VISA));
    }

    @Test
    void orderWithMastercardMoreThan100AmountDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(150.00);
        assertEquals(0.17, payOrder(products, PaymentType.MASTERCARD));
    }

    @Test
    void orderWithMastercardWith100AmountDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(100.00);
        assertEquals(0.17, payOrder(products, PaymentType.MASTERCARD));
    }

    @Test
    void orderWithMastercardMoreThan75AmountDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(80.00);
        assertEquals(0.12, payOrder(products, PaymentType.MASTERCARD));
    }

    @Test
    void orderWithMastercardWith75AmountDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(75.00);
        assertEquals(0.12, payOrder(products, PaymentType.MASTERCARD));
    }

    @Test
    void orderWithMastercardWith10AmountDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(10.00);
        assertEquals(0.08, payOrder(products, PaymentType.MASTERCARD));
    }

    @Test
    void orderWithProductDeletion() throws EmptyOrderException {
        Product product =Product.builder()
                .setProductName("Product1")
                .setProductPrice(5.0)
                .setProductType(ProductType.valueOf(1))
                .build();

        Product product2 = Product.builder()
                .setProductName("Product")
                .setProductPrice(10.0)
                .setProductType(ProductType.WORKING_TOOL)
                .build();


        Order order = new Order(new HashMap<>());
        order.addProduct(product, 2);
        order.addProduct(product2, 3);
        order.removeProduct(product);

        Checkout checkout = Checkout.getCheckout();

        assertEquals(28.50, checkout.pay(order, PaymentType.VISA));
    }

    @Test
    void orderWithNoDiscount() {
        Product product =Product.builder()
                .setProductName("Product 1")
                .setProductPrice(5.0)
                .setProductType(ProductType.valueOf(1))
                .build();

        Product product2 =Product.builder()
                .setProductName("Product 2")
                .setProductPrice(10.0)
                .setProductType(ProductType.BASIC_NEED)
                .build();

        Order order = new Order(new HashMap<>());
        order.addProduct(product,1);
        order.addProduct(product2,1);

        Checkout checkout = Checkout.getCheckout();

        try {
            assertEquals(15.00, checkout.pay(order, PaymentType.OTHER));
        } catch (EmptyOrderException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void orderWithOneProductWithInvalidDataThrowException() {
        Product product = Product.builder()
                .setProductName("")
                .setProductPrice(-10.0)
                .setProductType(ProductType.LUXURY)
                .build();

        Order order = new Order(new HashMap<>());
        order.addProduct(product,2);

        Checkout checkout = Checkout.getCheckout();
        checkout.print(order);

        Exception exception = assertThrows(EmptyOrderException.class, () -> checkout.pay(order, PaymentType.VISA));

        String expectedMessage = "No products found in order.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void orderWithOneInvalidProduct() {
        Product product = Product.builder()
                .setProductName("")
                .setProductPrice(-10.0)
                .setProductType(ProductType.valueOf(1))
                .build();

        Product product2 = Product.builder()
                .setProductName("Product 1")
                .setProductPrice(10.0)
                .setProductType(ProductType.valueOf(10))
                .build();

        Order order = new Order(new HashMap<>());
        order.addProduct(product,2);
        order.addProduct(product2,2);

        Checkout checkout = Checkout.getCheckout();

        assertEquals(0.05,  payOrder(order.getOrder(), PaymentType.VISA));

    }

    @Test
    void orderWithTwoProductsRemovingOne() {
        Product product = Product.builder()
                .setProductName("Product 1")
                .setProductPrice(10.0)
                .setProductType(ProductType.valueOf(1))
                .build();

        Product product2 = Product.builder()
                .setProductName("Product 2")
                .setProductPrice(10.0)
                .setProductType(ProductType.valueOf(10))
                .build();

        Order order = new Order(new HashMap<>());
        order.addProduct(product,3);
        order.addProduct(product2,2);
        order.removeProduct(product);

        Checkout checkout = Checkout.getCheckout();

        assertEquals(0.08,  payOrder(order.getOrder(), PaymentType.MASTERCARD));

    }
}

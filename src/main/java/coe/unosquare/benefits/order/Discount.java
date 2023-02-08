package coe.unosquare.benefits.order;

import coe.unosquare.benefits.product.PaymentType;

/**
 * The type Discount
 */
public interface Discount {

    double calculateDiscount(PaymentType paymentType, int numOfProducts, double subtotal);
}

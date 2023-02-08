package coe.unosquare.benefits.order;

import coe.unosquare.benefits.product.PaymentType;

/**
 * The type DiscountVisa
 */
public class DiscountVisa implements Discount{


    /**
     * calculateDiscount
     *
     * @param paymentType the type of payment
     * @param numOfProducts the number of products
     * @param subtotal the subtotal of the order
     *
     * @return the amount of discount
     */
    public double calculateDiscount(PaymentType paymentType, int numOfProducts, double subtotal){
        double discount;

        if (numOfProducts >= 10) {
            discount = 0.15;
        } else if (numOfProducts >= 7) {
            discount = 0.10;
        } else {
            discount = 0.05;
        }
        return discount;
    }
}

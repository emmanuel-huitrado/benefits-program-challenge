package coe.unosquare.benefits.order;

import coe.unosquare.benefits.product.PaymentType;

/**
 * The type DiscountMastercard
 */
public class DiscountMastercard implements Discount{

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

        if (subtotal >= 100) {
            discount = 0.17;
        } else if (subtotal >= 75) {
            discount = 0.12;
        } else {
            discount = 0.08;
        }
        return discount;
    }
}

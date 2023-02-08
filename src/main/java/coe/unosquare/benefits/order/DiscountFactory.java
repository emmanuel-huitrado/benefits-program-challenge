package coe.unosquare.benefits.order;

import coe.unosquare.benefits.product.PaymentType;

import java.util.HashMap;
import java.util.Map;


/**
 * The type DiscountFactory
 */
public class DiscountFactory {

    static Map<PaymentType, Discount> discountMap = new HashMap<>();

    static {
        discountMap.put(PaymentType.VISA, new DiscountVisa());
        discountMap.put(PaymentType.MASTERCARD, new DiscountMastercard());
    }

    /**
     * addDiscount
     *
     * @param paymentType the type of payment
     * @param discount the discount class to add
     */
    public static void addDiscount(PaymentType paymentType, Discount discount){
        discountMap.put(paymentType, discount);
    }


    /**
     * getDiscount
     *
     * @param paymentType the type of payment
     * @return the discount instance for the type of payment
     */
    public static Discount getDiscount(PaymentType paymentType){
        return discountMap.get(paymentType);
    }
}

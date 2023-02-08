package coe.unosquare.benefits.order;

import coe.unosquare.benefits.exception.EmptyOrderException;
import coe.unosquare.benefits.product.PaymentType;

/**
 * The type Checkout
 */
public class Checkout {
    private static Checkout checkout;

    /**
     * Hide constructor to avoid creating instances of this class directly.
     */
    private Checkout() {}


    /**
     * getCheckout
     *
     * @return the only instance of Checkout
     */
    public static Checkout getCheckout() {
        if (checkout == null) {
            checkout = new Checkout();
        }
        return checkout;
    }

    /**
     * Pay double.
     *
     * @param order       the order
     * @param paymentType the payment type
     * @return the double
     */
    public Double pay(Order order, PaymentType paymentType) throws EmptyOrderException {
        if(order.getOrder().size() == 0){
            throw new EmptyOrderException("No products found in order.");
        }

        double finalDiscount = 0;
        double subtotal = calculateSubtotal(order);
        Discount discount = DiscountFactory.getDiscount(paymentType);

        if (discount != null) {
            finalDiscount = discount.calculateDiscount(paymentType, order.getNumberOfProductsInOrder(), subtotal);
        }

        return subtotal - subtotal * finalDiscount;
    }

    /**
     * Print.
     */
    public void print(Order order) {
        order.getOrder().forEach((product, quantity) ->
                System.out.println("Product:{" + product.getName() + ","
                        + product.getPrice() + ","
                        + product.getType()
                        + "},Quantity:" + quantity
                        + ",Total:" + product.getPrice() * quantity));
    }


    /**
     * calculateSubtotal
     *
     * @param order the order
     * @return the subtotal of the order
     */
    private Double calculateSubtotal(Order order){
        return order.getOrder().entrySet()
                .stream()
                .mapToDouble(product -> product.getKey().getPrice() * product.getValue())
                .sum();
    }
}

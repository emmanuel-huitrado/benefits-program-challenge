/*
 *  Order
 *  1.0
 *  11/8/22, 8:28 PM
 *  Copyright (c) 2022 Unosquare
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.unosquare.benefits.order;

import coe.unosquare.benefits.exception.ProductDataInvalidException;
import coe.unosquare.benefits.product.Product;
import java.util.Map;

/**
 * The type Order.
 */
public class Order {

    /**
     * Store the final list of products and quantity for each product.
     **/
    private final Map<Product, Integer> products;

    /**
     * Instantiates a new Order.
     *
     * @param productsMap the list of products added to the order
     */
    public Order(Map<Product, Integer> productsMap) {
        products = productsMap;
    }


    /**
     * addProduct
     *
     * @param product  the product
     * @param quantity the quantity for the product
     */
    public void addProduct(Product product, int quantity) {
        try {
            product.validate();

            if (products.containsKey(product)) {
                products.put(product, products.get(product) + quantity);

            } else {
                products.put(product, quantity);
            }
        } catch (ProductDataInvalidException e) {
            System.out.println(e.getMessage());
            e.getListOfErrors()
                    .forEach(System.err::println);
        }
    }


    /**
     * removeProduct
     *
     * @param product the product to remove
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }


    /**
     * getNumberOfProductsInOrder
     *
     * @return the size of the order
     */
    public int getNumberOfProductsInOrder(){
        return this.products.size();
    }

    /**
     * getOrder
     *
     * @return the products in the order
     */
    public Map<Product, Integer> getOrder(){
        return products;
    }

}

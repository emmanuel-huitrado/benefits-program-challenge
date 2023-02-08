/*
 *  Product
 *  1.0
 *  11/8/22, 8:30 PM
 *  Copyright (c) 2022 Unosquare
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.unosquare.benefits.product;

import coe.unosquare.benefits.exception.ValidationError;
import coe.unosquare.benefits.exception.ProductDataInvalidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Product.
 */
public class Product {
    /** The Product name. **/
    private final String productName;
    /** The Product price. **/
    private final Double productPrice;
    /** The Product type defined by: 1 = Basic need, 2 =  Work tool, 3 = Luxury.  **/
    private final ProductType productType;

    /**
     * Instantiates a new Product.
     *
     * @param name  the name
     * @param price the price
     * @param type  the type
     */
    private Product(final String name, final Double price, final ProductType type) {
        productName = name;
        productPrice = price;
        productType = type;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return productName;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Double getPrice() {
        return productPrice;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ProductType getType() {
        return productType;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + productName + '\''
                + ", price=" + productPrice
                + ", type=" + productType + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName) && productType == product.productType;
    }

    public void validate() throws ProductDataInvalidException {
        List<ValidationError> validationErrorList = new ArrayList<>();

        if(this.productName.isBlank()){
            validationErrorList.add(new ValidationError("productName","Product name shouldn't be empty"));
        }
        if(this.productPrice < 0){
            validationErrorList.add(new ValidationError("productPrice","Product price shouldn't be less than 0"));
        }

        if(validationErrorList.size() > 0){
            throw new ProductDataInvalidException("Validation errors", validationErrorList);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productType);
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public static final class ProductBuilder{
        private String productName;
        private Double productPrice;
        private ProductType productType;

        public ProductBuilder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder setProductPrice(Double productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public ProductBuilder setProductType(ProductType productType) {
            this.productType = productType;
            return this;
        }

        public Product build() {
            return new Product(this.productName, this.productPrice, this.productType);
        }
    }
}

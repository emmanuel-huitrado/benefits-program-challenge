package coe.unosquare.benefits.product;

import java.util.HashMap;
import java.util.Map;

public enum ProductType {
    BASIC_NEED(1),
    WORKING_TOOL(2),
    LUXURY(3),
    UNKNOWN(-1);

    private final int productType;

    ProductType(int productType){
        this.productType = productType;
    }

    private static final Map<Integer, ProductType> map = new HashMap<>();

    static {
        for (ProductType productTypeEnum : ProductType.values()) {
            map.put(productTypeEnum.productType, productTypeEnum);
        }
    }

    public static ProductType valueOf(int productTypeVal) {
        if(!map.containsKey(productTypeVal)){
            return map.get(-1);
        }
        return map.get(productTypeVal);
    }

}

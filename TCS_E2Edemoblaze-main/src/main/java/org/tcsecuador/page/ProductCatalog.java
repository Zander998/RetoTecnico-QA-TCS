package org.tcsecuador.page;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private static final Map<String, String> PRODUCT_IDS = new HashMap<>();

    static {
        PRODUCT_IDS.put("samsung galaxy s6", "1");
        PRODUCT_IDS.put("nokia lumia 1520", "2");
        PRODUCT_IDS.put("nexus 6", "3");
        PRODUCT_IDS.put("samsung galaxy s7", "4");
        PRODUCT_IDS.put("iphone 6 32gb", "5");
        PRODUCT_IDS.put("sony vaio i5", "8");
        PRODUCT_IDS.put("macbook air", "11");
    }

    public static String idFor(String productName) {
        String key = productName.toLowerCase();

        if (!PRODUCT_IDS.containsKey(key)) {
            throw new IllegalArgumentException("Producto no definido en el catálogo: " + productName);
        }

        return PRODUCT_IDS.get(key);
    }
}

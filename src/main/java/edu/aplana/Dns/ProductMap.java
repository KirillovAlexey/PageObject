package edu.aplana.Dns;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ProductMap {
/*    String price;
    String garantee;
    String description;

    ProductMap(String price, String garantee, String description){
        this.price = price;
        this.garantee = garantee;
        this.description = description;
    }*/

    private static Map<Object,Double> map = new HashMap<>();

    public static WebDriver driver;

    public static Object get(String key){
        return map.get(key);
    }

    public static void put(Object key, Double value){
        map.put(key, value);
    }
}

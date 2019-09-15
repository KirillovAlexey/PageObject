package edu.aplana.Dns;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ProductMap {

    public static Map<String, Double> map = new HashMap<>();

    public static WebDriver driver;

    public static Double get(String key) {
        return map.get(key);
    }

    public static void put(String key, Double value) {
        map.put(key, value);
    }
}

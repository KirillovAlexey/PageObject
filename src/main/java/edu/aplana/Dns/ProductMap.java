package edu.aplana.Dns;

import edu.aplana.Dns.Pages.ProductPage;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ProductMap {

    private static Map<Integer, ProductPage> map = new HashMap<>();

    public static WebDriver driver;

    public static ProductPage get(Integer key){
        return map.get(key);
    }

    public static void put(Integer key, ProductPage value){
        map.put(key, value);
    }
}

package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class BasePage {
    final WebDriver driver;
    private static Map<Object, Double> map = new HashMap<>();
    final By ProductGarantee = By.xpath("//span[contains(text(),'Гарантия')]");
    By check = By.xpath("//div[@class ='cart-list__product-additional-warranty cart-list__product-additional-warranty_wide-screen']//div[@class='radio radio_checked']//label[contains(text(),'+ 2 года')]");

    BasePage() {
        driver = ProductMap.driver;
        PageFactory.initElements(driver, this);
    }
}

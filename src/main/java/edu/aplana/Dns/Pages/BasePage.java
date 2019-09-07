package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

class BasePage {
    final WebDriver driver;
    private static Map<Object,Double> map = new HashMap<>();

    final By  productPrice = By.xpath("//div[@class='clearfix']//span[@class='current-price-value']");
    final By productName = By.xpath("//h1[@class='page-title price-item-title']");
    final By ProductGarantee = By.xpath("//span[contains(text(),'Гарантия')]");
    final By ProductDescription = By.xpath("//p[ancestor::div[@itemprop='description']]");
    final By ProductPurchase = By.xpath("//button[@class='btn btn-cart btn-lg']");
    final By chooseGarantee = By.xpath("//select[@class='form-control select']//option[@value='2']");

    BasePage() {
        driver = ProductMap.driver;
        PageFactory.initElements(driver, this);
    }
}

package edu.aplana.Dns.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {

    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseProduct(String name) {
        String productXpath = String.format("//div[@data-position-index='0']//a[contains(text(),'%s')]", name);
        WebElement productItem = driver.findElement(By.xpath(productXpath));
        productItem.click();
    }
}
package edu.aplana.Dns.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class PlayStationSearch {
    private WebDriver driver;

    public PlayStationSearch(WebDriver driver){
        this.driver = driver;
    }

    public void chooseProduct(String name){
        String productXpath = String.format("//div[@data-position-index='0']//a[contains(text(),'%s')]", name);
        WebElement productItem = driver.findElement(By.xpath(productXpath));
        productItem.findElement(By.xpath(productXpath)).click();
    }
}

package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class ProductPage extends BasePage {
    private WebDriver driver;
    private double priceProduct;
    private double priceProductGarantee;
    private String name;

    public String getName() {
        return name;
    }

    private String description;
    private String garantee;
    private static int count;

    @FindBy(xpath = "//select[@class='form-control select']")
    private WebElement checkGuarantee;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.name = driver.findElement(productName).getText();
        this.description = driver.findElement(ProductDescription).getText();
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public double getPriceProdductGarantee() {
        return priceProductGarantee;
    }

    public double savePrice() {
        priceProduct = parseToDouble(driver.findElement(productPrice).getText());
        return priceProduct;
    }

    public double savePriceGarantee() {
        checkGuarantee.click();
        driver.findElement(chooseGarantee).click();
        priceProductGarantee = parseToDouble(driver.findElement(productPrice).getText());
        return priceProductGarantee;
    }

    public void addBusket() throws InterruptedException {
        driver.findElement(ProductPurchase).click();
        (new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(driver.findElement(ProductPurchase)))).isSelected();
        if (priceProductGarantee != 0) {
            ProductMap.put(++count, this);
        } else
            ProductMap.put(++count, this);
    }

    public void checkPrice() {
        assertEquals("1", ProductMap.get(1).getPriceProdductGarantee() + ProductMap.get(2).getPriceProduct(),
                parseToDouble(driver.findElement(By.xpath("//div[@class='buttons']//span[@data-of = 'totalPrice']")).getText()));
    }

    public double parseToDouble(String s) {
        s.trim();
        s = s.replaceAll("\\ ", ".");
        double price = Double.parseDouble(s);
        return price;
    }
}

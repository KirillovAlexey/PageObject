package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    private WebDriver driver;
    private double priceProduct;
    private double priceProductGarantee;
    private String name;
    private String description;
    private String garantee;

    @FindBy(xpath = "//select[@class='form-control select']")
    private WebElement checkGuarantee;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.name = driver.findElement(productName).getText();
        this.description = driver.findElement(ProductDescription).getText();
        //this.garantee = driver.findElement(ProductGarantee).getText();

        /*this.priceProduct = parseToDouble(driver.findElement(productPrice).getText());
        this.priceProductGarantee = parseToDouble(driver.findElement(productPrice).getText());*/

    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public double getPriceProdductGarantee() {
        return priceProductGarantee;
    }

    public void savePrice() {
        priceProduct = parseToDouble(driver.findElement(productPrice).getText());
    }

    public void savePriceGarantee() {
        checkGuarantee.click();
        driver.findElement(chooseGarantee).click();
        priceProductGarantee = parseToDouble(driver.findElement(productPrice).getText());
    }

    public void addBusket() {
        //WebElement webElement = driver.findElement(By.xpath("//div[contains(text(), 'Доп. гарантия - ')]"));
        ////div[contains(text(), 'Доп. гарантия - ')]
        driver.findElement(ProductPurchase).click();
        //name = driver.findElement(productName).getText();
        if (priceProductGarantee != 0)
            ProductMap.put(this, this.priceProductGarantee);
        else
            ProductMap.put(this, this.priceProduct);
    }

    public double parseToDouble(String s) {
        s.trim();
        s = s.replaceAll("\\s", ".");
        double price = Double.parseDouble(s);
        return price;
    }
}

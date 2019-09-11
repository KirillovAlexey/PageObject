package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.applet.Main;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class ProductPage extends BasePage {
    private WebDriver driver;
    private double priceProduct;
    private double priceProductGarantee;
    private String name;
    final By productPrice = By.xpath("//div[@class='clearfix']//span[@class='current-price-value']");
    final By productName = By.xpath("//h1[@class='page-title price-item-title']");
    final By ProductDescription = By.xpath("//p[ancestor::div[@itemprop='description']]");
    final By ProductPurchase = By.xpath("//button[@class='btn btn-cart btn-lg']");
    final By chooseGarantee = By.xpath("//select[@class='form-control select']//option[@value='2']");
    final By checkToBasket = By.xpath("//span[@data-content='label'][contains(text(),'В корзине')]");
    final By totalPriceBasket = By.xpath("//div[@class='buttons']//span[@data-of = 'totalPrice']");

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

    public void addBusket() {
        driver.findElement(ProductPurchase).click();
        if (priceProductGarantee != 0) {
            ProductMap.put(++count, this);
        } else
            ProductMap.put(++count, this);
    }

    public void checkPrice() {
        WebElement webElement = driver.findElement(checkToBasket);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String s = webElement.getText();
        wait.until(ExpectedConditions.visibilityOf(webElement));
        assertEquals((ProductMap.get(1).getPriceProdductGarantee() + ProductMap.get(2).getPriceProduct() ),
                parseToDouble(driver.findElement(totalPriceBasket).getText()));
    }

    public double parseToDouble(String s) {
        s.trim();
        s = s.replaceAll("\\ ", "");
        double price = Double.parseDouble(s);
        return price;
    }
}

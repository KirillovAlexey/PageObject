package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class ProductPage extends BasePage {
    private WebDriver driver;
    private double priceProduct;
    private double priceProductGarantee;
    private String name;
    private final By productPrice = By.xpath("//div[@class='clearfix']//span[@class='current-price-value']");
    private final By productName = By.xpath("//h1[@class='page-title price-item-title']");
    private final By ProductDescription = By.xpath("//p[ancestor::div[@itemprop='description']]");
    private final By ProductPurchase = By.xpath("//button[@class='btn btn-cart btn-lg']");
    private final By chooseGarantee = By.xpath("//select[@class='form-control select']//option[@value='2']");
    private final By checkToBasket = By.xpath("//span[@data-content='label'][contains(text(),'В корзине')]");
    private final By totalPriceBasket = By.xpath("//div[@class='buttons']//span[@data-of = 'totalPrice']");

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

    double getPriceProduct() {
        return priceProduct;
    }

    double getPriceProdductGarantee() {
        return priceProductGarantee;
    }

    public double savePrice() {
        priceProduct = parseToDouble(driver.findElement(productPrice).getText());
        return priceProduct;
    }

    public double savePriceGarantee() {
        driver.findElement(chooseGarantee).click();
        checkGuarantee.click();
        priceProductGarantee = parseToDouble(driver.findElement(productPrice).getText());
        return priceProductGarantee;
    }

    public void addBusket() {
        waitingChange(totalPriceBasket, ProductPurchase);
        if (priceProductGarantee == 0) {
            ProductMap.put(this.name, this.priceProduct);
            //ProductMap.put(ProductPage.name, String.valueOf(ProductPage.priceProduct));
        } else {
            ProductMap.put(this.name, this.priceProduct);
            ProductMap.put(this.name + " с гарантией", this.priceProductGarantee);
        }
    }

    public void checkPrice() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Double sum= 0.0;
        for (HashMap.Entry<String,Double> hash: ProductMap.map.entrySet()) {
            if(hash.getKey().contains("с гарантией") || hash.getKey().contains("Игра")){
                sum += hash.getValue();
            }
        }
        assertEquals(sum,
                parseToDouble(driver.findElement(totalPriceBasket).getText()));

        /*assertEquals((ProductMap.get("").getPrice() + ProductMap.get(2).getPriceProduct()),
                parseToDouble(driver.findElement(totalPriceBasket).getText()));*/
    }

    private double parseToDouble(String s) {
        s.trim();
        s = s.replaceAll("\\ ", "");
        double price = Double.parseDouble(s);
        return price;
    }
}

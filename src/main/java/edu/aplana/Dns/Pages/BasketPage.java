package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class BasketPage extends BasePage {
    private WebDriver driver;
    private String total;
    private Integer count = 1;

    private By checkCountPos = By.xpath("//nav[@id='header-search']//span[@class='btn-cart-link__badge']");
    private String inBasket = "//div[@class='buttons']//a[@class='btn-cart-link']";
    private By sumToBasketIn = By.xpath("//div[@class='total-amount']//div[@class='item-price']//span");

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void entrance() {
        driver.findElement(By.xpath(inBasket)).click();
    }

    public void checkGarantee() {
        WebElement webElement = driver.findElement(check);
        assertTrue("Неправда", webElement.isDisplayed());
    }

    public void checkPriceProductToBasket() {
        double sum = 0.0;
        String ps;
        String garantee;
        String game;
        garantee = driver.findElement(check).getText();
        garantee = garantee.substring(garantee.indexOf('(') + 1, garantee.indexOf(')'));
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='cart-list__product']//div[@class='item-price']//span//" +
                "ancestor::div[@class='cart-list__product-count-price']//preceding-sibling::div//div//a[@class='cart-list__product-name-link']"));
        List<WebElement> list1 = driver.findElements(By.xpath("//div[@class='cart-list__product-thumbnail']//div[@class='item-price']//span"));
        HashMap<WebElement, WebElement> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                hashMap.put(list.get(j), list1.get(j));
            }
        }
        assertEquals(hashMap.size(), ProductMap.map.size() - 1);
        for (HashMap.Entry<WebElement, WebElement> hash : hashMap.entrySet()) {
            for (HashMap.Entry<String, Double> map : ProductMap.map.entrySet()) {
                //Проверка цены игры.
                //Проверка цены игры с сохраненной ценой при покупке
                if (hash.getKey().getText().equals(map.getKey())) {
                    assertEquals(map.getValue(), parseToDouble(hash.getValue().getText()));
                }
                //Проверка цены игры с гарантией.
                if ((hash.getKey().getText().concat(" с гарантией")).equals(map.getKey())) {
                    sum += parseToDouble(garantee) + parseToDouble(hash.getValue().getText());
                    assertEquals(map.getValue(), parseToDouble(garantee) + parseToDouble(hash.getValue().getText()));
                }
            }
        }
        list.clear();
        list1.clear();
        hashMap.clear();
    }

    public void delete(String name) {
        By removeProd = By.xpath(String.format("//a[contains(text(),'%s')]//..//..//..//..//button[@class='remove-button']", name));
        waitingChange(sumToBasketIn, removeProd);
        //Проверка общей цены корзины без игры
        //assertEquals(ProductMap.get(1).getPriceProdductGarantee(), parseToDouble(driver.findElement(sumToBasketIn).getText()));
    }

    public void addProduct(String s) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement webElement = driver.findElement(checkCountPos);
        String name = String.format("//a[contains(text(),'%s')]//..//..//..//button[@class='count-buttons__button count-buttons__button_plus']", s);
        driver.findElement(By.xpath(name)).click();
        String atr = webElement.getText();
        count = Integer.parseInt(atr);
        ++count;
        wait.until(ExpectedConditions.textToBe(checkCountPos, count.toString()));
    }

    public void checkBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("")));
        total = driver.findElement(sumToBasketIn).getText();
        //assertEquals(ProductMap.get(1).getPriceProdductGarantee() * 3, parseToDouble(total));
    }

    public void returnProduct(String s) {
        driver.findElement(By.xpath("//a[@class='restore-last-removed']")).click();
        String gamePrice = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]//..//..//..//div[@class='item-price']//span", s))).getText();
        assertEquals(parseToDouble(total) + parseToDouble(gamePrice),
                parseToDouble(driver.findElement(sumToBasketIn).getText()));
    }

    private double parseToDouble(String s) {
        s.trim();
        s = s.replaceAll("\\ ", "");
        double price = Double.parseDouble(s);
        return price;
    }
}
package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BasketPage extends BasePage {
    WebDriver driver;
    static String total;
    Integer count = 1;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void entrance() {
        driver.findElement(By.xpath("//div[@class='buttons']//a[@class='btn-cart-link']")).click();
    }

    public void checkGarantee() {
        WebElement webElement = driver.findElement(check);
        assertTrue("Неправда", webElement.isDisplayed());
    }

    public void checkPriceProductToBasket() {
        String garantee = driver.findElement(By.xpath(string + "//ancestor-or-self::label [contains(text(),'+ 2 года')]")).getText();
        String ps = driver.findElement(By.xpath("//a[contains(text(),'PlayStation 4 Slim Black 1 TB')]//..//..//..//div[@class='item-price']//span")).getText();
        String game = driver.findElement(By.xpath("//a[contains(text(),'Detroit')]//..//..//..//div[@class='item-price']//span")).getText();

        garantee = garantee.substring(garantee.indexOf('(') + 1, garantee.indexOf(')') - 1);
        //Проверка цены приставки в магазине с сохраненной ценой при покупке
        assertEquals(ProductMap.get(1).getPriceProdductGarantee(), parseToDouble(garantee) + parseToDouble(ps));
        //Проверка цены игры с сохраненной ценой при покупке
        assertEquals(ProductMap.get(2).getPriceProduct(), parseToDouble(game));
        //Проверка общей цены корзины с ранее сохраненными позициями
        assertEquals(ProductMap.get(1).getPriceProdductGarantee() + ProductMap.get(2).getPriceProduct(),
                parseToDouble(garantee) + parseToDouble(ps) + parseToDouble(game));
    }

    public void delete(String name) {
        String xpath = String.format("//a[contains(text(),'%s')]//..//..//..//..//button[@class='remove-button']", name);
        driver.findElement(By.xpath(xpath)).click();
        WebElement webElement = driver.findElement(By.xpath("//a[contains(text(),'Detroit')]//..//..//..//div[@class='item-price']//span"));
        (new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(webElement))).booleanValue();
        total = driver.findElement(By.xpath("//div[@class='total-amount']//div[@class='item-price']//span")).getText();
        //Проверка общей цены корзины без игры
        assertEquals(ProductMap.get(1).getPriceProdductGarantee(), parseToDouble(total));
    }

    public void addProduct() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement webElement = driver.findElement(By.xpath("//nav[@id='header-search']//span[@class='btn-cart-link__badge']"));
        driver.findElement(By.xpath("//button[@class='count-buttons__button count-buttons__button_plus']")).click();
        String atr = webElement.getText();
        count = Integer.parseInt(atr);
        ++count;
        wait.until(ExpectedConditions.textToBe(By.xpath("//nav[@id='header-search']//span[@class='btn-cart-link__badge']"), count.toString()));
    }

    public void checkBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("")));
        total = driver.findElement(By.xpath("//div[@class='total-amount']//div[@class='item-price']//span")).getText();
        assertEquals(ProductMap.get(1).getPriceProdductGarantee() * 3, parseToDouble(total));
    }

    public void returnProduct(){
        driver.findElement(By.xpath("//a[@class='restore-last-removed']")).click();
        assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Detroit')]")).isDisplayed());
        String gamePrice = driver.findElement(By.xpath("//a[contains(text(),'Detroit')]//..//..//..//div[@class='item-price']//span")).getText();
        assertEquals(parseToDouble(total) + parseToDouble(gamePrice),
                parseToDouble(driver.findElement(By.xpath("//div[@class='total-amount']//div[@class='item-price']//span")).getText()));
    }

    public double parseToDouble(String s) {
        s.trim();
        s = s.replaceAll("\\ ", ".");
        Double price = Double.parseDouble(s);
        return price;
    }
}
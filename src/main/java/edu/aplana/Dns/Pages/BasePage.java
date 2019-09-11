package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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

    void waitingChange(By by, By byC){
        //String oldValue = driver.findElement(by).getText();
        WebElement oldValue = driver.findElement(by);
        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(by).getText().equals(oldValue.getText());
            }
        };
        //действие для изменения значения
        driver.findElement(byC).click();
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(valueChanged);
    }
}

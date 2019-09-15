package edu.aplana.Dns.Pages;

import edu.aplana.Dns.ProductMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

class BasePage {
    final WebDriver driver;
    By check = By.xpath("//div[@class ='cart-list__product-additional-warranty cart-list__product-additional-warranty_wide-screen']//div[@class='radio radio_checked']//label[contains(text(),'+ 2 года')]");

    BasePage() {
        driver = ProductMap.driver;
        PageFactory.initElements(driver, this);
    }

    void waitingChange(By by, By byC) {
        String oldValue = driver.findElement(by).getText();
        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !webDriver.findElement(by).getText().equals(oldValue);
            }
        };
        //действие для изменения значения
        driver.findElement(byC).click();
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(valueChanged);
    }
}

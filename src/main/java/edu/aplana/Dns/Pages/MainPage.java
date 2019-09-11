package edu.aplana.Dns.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[contains(@class,'ui-autocomplete')]")
    private WebElement searchTextField;

    @FindBy(xpath = "//nav//span[@class='ui-input-search__icon ui-input-search__icon_search']")
    private WebElement searchButton;

    public void search(String text) {
        searchTextField.sendKeys(text);
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}

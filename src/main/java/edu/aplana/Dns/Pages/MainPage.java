package edu.aplana.Dns.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

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

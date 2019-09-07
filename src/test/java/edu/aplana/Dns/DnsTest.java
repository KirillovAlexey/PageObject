package edu.aplana.Dns;

import edu.aplana.Dns.Pages.MainPage;
import edu.aplana.Dns.Pages.ProductPage;
import edu.aplana.Dns.Pages.SearchPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;


/*
1) открыть dns-shop
2) в поиске найти playstation
3) кликнуть по playstation 4 slim black
4) запомнить цену
5) Доп.гарантия - выбрать 2 года
6) дождаться изменения цены и запомнить цену с гарантией
7) Нажать Купить
8) выполнить поиск Detroit
9) запомнить цену
10) нажать купить
11) проверить что цена корзины стала равна сумме покупок
12) перейри в корзину
13) проверить, что для приставки выбрана гарантия на 2 года
14) проверить цену каждого из товаров и сумму
15) удалить из корзины Detroit
16) проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
18) нажать вернуть удаленный товар, проверитьчто Detroit появился в корзине и сумма увеличилась на его значение
 */
public class DnsTest {
    private static WebDriver driver;
    private static String url;

    @Before
    public void init() {
        //System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        url = "https://www.dns-shop.ru/";
        ProductMap.driver = driver;
    }

    @Test
    public void dnsShop() {

        driver.get(url);
        MainPage mainPage = new MainPage();
        mainPage.search("playstation");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.chooseProduct("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");

        ProductPage ps4 = new ProductPage(driver);
        ps4.savePrice();
        ps4.savePriceGarantee();
        ps4.addBusket();
        //productPage.add();

        mainPage.search("Detroit");
        ProductPage game = new ProductPage(driver);
        game.savePrice();
        game.addBusket();

        ps4.parseToDouble(driver.findElement(By.xpath("//div[@class='buttons']//span[@data-of = 'totalPrice']")).getText());

        assertEquals("Разница в цене между ожидаемым и текущим результатом", (ps4.getPriceProdductGarantee() + game.getPriceProduct()),
                ps4.parseToDouble(driver.findElement(By.xpath("//div[@class='buttons']//span[@data-of = 'totalPrice']")).getText()));
        //driver.findElement(By.xpath("//input[contains(@class,'ui-input-search__input main-search-form__input ui-autocomplete-input')]")).sendKeys("playstation");
        //driver.findElement(By.xpath("//input[contains(@class,'ui-input-search__input main-search-form__input ui-autocomplete-input ui-autocomplete-loading')]")).click();
        //driver.findElement(By.xpath("//input[contains(@class,'ui-input-search__input main-search-form__input ui-autocomplete-input')]")).sendKeys(Keys.ENTER);

        //PlayStationSearch psSearch = new PlayStationSearch(driver);
        //psSearch.chooseProduct("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//a[contains(text(),'Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры')]")).click();

        //PlayStation playStation = new PlayStation(driver);
        //playStation.addBusket();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //String pricePS = driver.findElement(By.xpath("//div[@class='clearfix']//div[@class='item-order-price']//div[@class='price_g']//span[@class='current-price-value']")).getText();
        //driver.findElement(By.xpath("//select[@class='form-control select']")).click();
        //driver.findElement(By.xpath("//select[@class='form-control select']//option[@value='2']")).click();
        //String pricePsSafe = driver.findElement(By.xpath("//div[@class='clearfix']//span[@class='current-price-value']")).getText();
        //driver.findElement(By.xpath("//button[@class='btn btn-cart btn-lg']")).click();

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//input[contains(@class,'ui-input-search__input main-search-form__input ui-autocomplete-input')]")).sendKeys("Detroit");
        //driver.findElement(By.xpath("//input[contains(@class,'ui-input-search__input main-search-form__input ui-autocomplete-input')]")).sendKeys(Keys.ENTER);
        //driver.findElement(By.xpath("//button[@class='btn btn-cart btn-lg']")).click();

        //String priceGame = driver.findElement(By.xpath("//div[@class='clearfix']//span[@class='current-price-value']")).getText();
        //String totalPrice = driver.findElement(By.xpath("//div[@class='clearfix']//span[@class='current-price-value']")).getText();
    }

    @After
    public void close() {
        driver.quit();
    }
}

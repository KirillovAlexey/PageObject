package edu.aplana.Dns;

import edu.aplana.Dns.Pages.BasketPage;
import edu.aplana.Dns.Pages.MainPage;
import edu.aplana.Dns.Pages.ProductPage;
import edu.aplana.Dns.Pages.SearchPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;


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
    static String name;
    /*public DnsTest(String name){
        this.name = name;
    }*/
    public static Collection<Object[]> data(){
        Object[][] data = new Object[][]{
                {"PlayStation 4 Slim Black 1 TB + 3 игры"},
                {"Detroit: Стать человеком"}
        };
        return Arrays.asList(data);
    }

    @Before
    public void init() {
        System.setProperty("webdriver."+ DevProperties.getInstance().getProperty("browser")+".driver", DevProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        url = DevProperties.getInstance().getProperty("url");
        ProductMap.driver = driver;
    }

    @Test
    public void dnsShop() {

        //1) открыть dns-shop
        driver.get(url);
        MainPage mainPage = new MainPage();
        //2) в поиске найти playstation
        mainPage.search("playstation");

        SearchPage searchPage = new SearchPage(driver);
        //3) кликнуть по playstation 4 slim black
        searchPage.chooseProduct("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        ProductPage ps4 = new ProductPage(driver);
        //4) запомнить цену
        ps4.savePrice();
        //5) Доп.гарантия - выбрать 2 года
        //6) дождаться изменения цены и запомнить цену с гарантией
        ps4.savePriceGarantee();
        //7) Нажать Купить
        ps4.addBusket();
        //8) выполнить поиск Detroit
        mainPage.search("Detroit");
        ProductPage game = new ProductPage(driver);
        //9) запомнить цену
        game.savePrice();
        //10) нажать купить
        game.addBusket();
        //11) проверить что цена корзины стала равна сумме покупок
        game.checkPrice();

        BasketPage basketPage = new BasketPage(driver);
        //12) перейри в корзину
        basketPage.entrance();
        //13) проверить, что для приставки выбрана гарантия на 2 года
        basketPage.checkGarantee();
        //14) проверить цену каждого из товаров и сумму
        basketPage.checkPriceProductToBasket();
        //15) удалить из корзины Detroit
        //16) проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
        //basketPage.delete("Detroit");
        //17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
        basketPage.addProduct(ProdProperties.getInstance().getProperty("playstation"));
        //basketPage.addProduct(driver.findElement(By.id(name)).getText());
        basketPage.addProduct(ProdProperties.getInstance().getProperty("game"));
        //17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
        basketPage.checkBasket();
        //18) нажать вернуть удаленный товар, проверить что Detroit появился в корзине и сумма увеличилась на его значение
        //basketPage.returnProduct();
    }

    @After
    public void close() {
        driver.quit();
    }
}

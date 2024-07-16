import asserts.Gui;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import pages.BasketPage;
import pages.CatalogPage;
import pages.GeoPage;
import pages.MainPage;

import java.util.Arrays;
import java.util.List;

import static asserts.Gui.shouldBeEquals;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class GuiTest {

    private MainPage mainPage = new MainPage();
    private CatalogPage catalogPage = new CatalogPage();

    private final String LINK = "https://www.wildberries.ru/";

    @BeforeAll
    public static void setUp() {

        WebDriverManager.chromedriver().setup();

        Configuration.timeout = 30000;

    }

    @BeforeEach
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    @Test
    @DisplayName("Работа с поисковой строкой")
    public void checkSearchField() {

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        mainPage.findProduct("Iphone 13");

        catalogPage.getTitleResults().shouldHave(text("По запросу Iphone 13 найдено"));

        catalogPage.getFirstFilter().shouldHave(text("iphone 13"));

        catalogPage.getSecondFilter().shouldHave(text("По популярности"));

        catalogPage.getBrandFirstProduct().shouldHave(text("Apple"));

        catalogPage.clearInputField();

        mainPage.getSearchField().shouldHave(value(""));

    }

    @Test
    @DisplayName("Смена города")
    public void changeCity() {

        GeoPage geoPage = new GeoPage();

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        mainPage.clickButtonChangeCity();

        geoPage.enterCity("Санкт-Петербург");

        geoPage.clickButtonFind();

        geoPage.waitingLoadAnyDeliveryPoint();

        String firstAddress = geoPage.getFirstAddress();

        geoPage.clickFirstAddress();

        geoPage.getInfoDeliveryPoint().shouldBe(visible);

        geoPage.getAddressDeliveryPoint().shouldHave(text(firstAddress));

        geoPage.clickButtonSelectDeliveryPoint();

        geoPage.getInfoDeliveryPoint().shouldNotBe(visible);

        mainPage.getButtonChangeCity().shouldHave(text(firstAddress));
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductBasket() {

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        mainPage.clickButtonFilters();

        mainPage.hoverToCategoryMenu();

        mainPage.hoverToHouseAppliances();

        mainPage.clickAppliancesForHouse();

        mainPage.clickHooversAndSteamCleaners();

        mainPage.clickRobotHoovers();

        catalogPage.getCatalogTitle().shouldHave(text(mainPage.getRobotHoovers().text()));

        List<String> actualFilters = catalogPage.getFilters();
        List<String> expectedFilters = Arrays.asList("Главная", "Бытовая техника", "Техника для дома",
                "Пылесосы и пароочистители", "Роботы-пылесосы");

        shouldBeEquals(expectedFilters, actualFilters);

        String firstProductName = catalogPage.getNameFirstProduct();
        String firstProductPrice = catalogPage.getPriceFirstProduct();

        catalogPage.clickButtonInBasket();

        catalogPage.getCountProductsBasket().shouldHave(text("1"));

        catalogPage.clickButtonBasket();

        Selenide.sleep(1000);

        BasketPage basketPage = new BasketPage();

        /*
        На некоторых товарах, проверка ниже не проходит, потому что в случае с товарами некоторых брендов,
        при добавлении товара в корзину, в название товара в корзине добавляется еще и название бренда, при условии,
        что в каталоге товаров, в названии, бренда нет. Не со всеми брендами это происходит, я пока
        только с Okami с таким столкнулся
         */
        basketPage.getNameProduct().shouldHave(text(firstProductName));
        basketPage.getPriceProduct().shouldHave(text(firstProductPrice));

        basketPage.getPriceTotal().shouldHave(text(firstProductPrice));

        basketPage.getButtonOrder().shouldBe(enabled);

    }

    @Test
    @DisplayName("Работа с фильтрами")
    public void checkFilters() {

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        mainPage.clickButtonFilters();

        mainPage.hoverToCategoryMenu();

        mainPage.hoverToElectronics();

        mainPage.clickLaptopsAndComputers();

        mainPage.clickLaptops();

        catalogPage.clickButtonAllFilters();

        String priceFrom = "100 000";
        String priceTo = "149 000";
        catalogPage.enterPriceFrom(priceFrom);
        catalogPage.enterPriceTo(priceTo);

        String brandText = catalogPage.getFilterBrandText();
        String diagonalText = catalogPage.getFilterDiagonalText();

        catalogPage.clickFilterBrand();
        catalogPage.clickFilterDiagonal();

        Selenide.sleep(2000);

        String countGoodsInFilters = catalogPage.getCountFilteredGoodsInFilters();

        catalogPage.clickButtonShowing();

        shouldBeEquals(countGoodsInFilters, catalogPage.getCountFilteredGoodsOnPage());
        catalogPage.getSelectedFilterBrand().shouldHave(text(brandText));
        catalogPage.getSelectedFilterPrice().shouldHave(text(String.format("от %s до %s", priceFrom, priceTo)));
        catalogPage.getSelectedFilterDiagonal().shouldHave(text(diagonalText));
        catalogPage.getButtonReset().shouldBe(enabled);

    }




}

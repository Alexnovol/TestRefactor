import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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

        CatalogPage catalogPage = mainPage.findProduct("Iphone 13");

        catalogPage.checkSearchResults("По запросу Iphone 13 найдено", "iphone 13",
                "По популярности", "Apple");

        catalogPage.clearSearchField();

        mainPage.getSearchField().shouldHave(value(""));

    }

    @Test
    @DisplayName("Смена города")
    public void changeCity() {

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        GeoPage geoPage = mainPage.clickButtonChangeCity();

        String city = "Санкт-Петербург";

        geoPage.enterCity(city);

        geoPage.waitingLoadAnyDeliveryPoint(city);

        String firstAddress = geoPage.getFirstAddress();

        geoPage.clickFirstAddress();

        geoPage.checkInfoDeliveryPoint(visible);
        geoPage.checkAddressDeliveryPoint(firstAddress);

        geoPage.clickButtonSelectDeliveryPoint();

        geoPage.checkInfoDeliveryPoint(disappear);
        mainPage.getButtonChangeCity().shouldHave(text(firstAddress));
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductBasket() {

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        mainPage.clickButtonFilters();

        mainPage.hoverToHouseAppliances();

        mainPage.clickAppliancesForHouse();

        mainPage.clickHooversAndSteamCleaners();

        CatalogPage catalogPage = mainPage.clickRobotHoovers();

        List<String> expectedFilters = Arrays.asList("Главная", "Бытовая техника", "Техника для дома",
                "Пылесосы и пароочистители", "Роботы-пылесосы");
        catalogPage.checkCatalogTitleAndFilters("Роботы-пылесосы", expectedFilters);

        String firstProductName = catalogPage.getNameFirstProduct();
        String firstProductPrice = catalogPage.getPriceFirstProduct();

        catalogPage.clickButtonInBasket();

        catalogPage.checkCountProductsBasket("1");

        BasketPage basketPage = catalogPage.clickButtonBasket();

        basketPage.checkDataInBasket(firstProductName, firstProductPrice);

    }

    @Test
    @DisplayName("Работа с фильтрами")
    public void checkFilters() {

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        mainPage.clickButtonFilters();

        mainPage.hoverToElectronics();

        mainPage.clickLaptopsAndComputers();

        CatalogPage catalogPage = mainPage.clickLaptops();

        catalogPage.clickButtonAllFilters();

        String priceFrom = "100 000";
        String priceTo = "149 000";
        catalogPage.enterPrice(priceFrom, priceTo);
        String brandText = catalogPage.getFilterBrandText();
        String diagonalText = catalogPage.getFilterDiagonalText();
        catalogPage.clickFilterBrandAndDiagonal();

        String countGoodsInFilters = catalogPage.getCountFilteredGoodsInFilters();

        catalogPage.clickButtonShowing();

        shouldBeEquals(countGoodsInFilters, catalogPage.getCountFilteredGoodsOnPage());
        catalogPage.checkSelectedFiltersAndButtonReset(brandText, diagonalText, priceFrom, priceTo);

    }




}

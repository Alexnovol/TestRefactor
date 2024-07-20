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

        catalogPage
                .checkSearchResults("По запросу Iphone 13 найдено", "iphone 13",
                "По популярности", "Apple")
                .clearSearchField();

        mainPage.getSearchField().shouldHave(value(""));

    }

    @Test
    @DisplayName("Смена города")
    public void changeCity() {

        String city = "Санкт-Петербург";

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        GeoPage geoPage = mainPage.clickButtonChangeCity()
                .enterCity(city)
                .waitingLoadAnyDeliveryPoint(city);

        String firstAddress = geoPage.getFirstAddress();

        geoPage.clickFirstAddress()
                .checkInfoDeliveryPoint(visible)
                .checkAddressDeliveryPoint(firstAddress)
                .clickButtonSelectDeliveryPoint();

        geoPage.checkInfoDeliveryPoint(disappear);
        mainPage.getButtonChangeCity().shouldHave(text(firstAddress));
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductBasket() {

        List<String> expectedFilters = Arrays.asList("Главная", "Бытовая техника", "Техника для дома",
                "Пылесосы и пароочистители", "Роботы-пылесосы");

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        CatalogPage catalogPage = mainPage
                .clickButtonFilters()
                .hoverToHouseAppliances()
                .clickAppliancesForHouse()
                .clickHooversAndSteamCleaners()
                .clickRobotHoovers();

        catalogPage
                .checkCatalogTitleAndFilters("Роботы-пылесосы", expectedFilters)
                .clickButtonInBasket()
                .checkCountProductsBasket("1");

        String firstProductName = catalogPage.getNameFirstProduct();
        String firstProductPrice = catalogPage.getPriceFirstProduct();

       BasketPage basketPage = catalogPage.clickButtonBasket();

       basketPage.checkDataInBasket(firstProductName, firstProductPrice);

    }

    @Test
    @DisplayName("Работа с фильтрами")
    public void checkFilters() {

        String priceFrom = "100 000";
        String priceTo = "149 000";

        open(LINK);

        mainPage.waitingLoadFirstProduct();

        CatalogPage catalogPage = mainPage
                .clickButtonFilters()
                .hoverToElectronics()
                .clickLaptopsAndComputers()
                .clickLaptops();

        catalogPage
                .clickButtonAllFilters()
                .enterPrice(priceFrom, priceTo);

        String brandText = catalogPage.getFilterBrandText();
        String diagonalText = catalogPage.getFilterDiagonalText();

        String countGoodsInFilters = catalogPage
                .clickFilterBrandAndDiagonal()
                .getCountFilteredGoodsInFilters();

        catalogPage
                .clickButtonShowing()
                .checkSelectedFiltersAndButtonReset(brandText, diagonalText, priceFrom, priceTo);

        shouldBeEquals(countGoodsInFilters, catalogPage.getCountFilteredGoodsOnPage());

    }

}

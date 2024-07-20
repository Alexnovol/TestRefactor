package pages;

import com.codeborne.selenide.SelenideElement;
import elements.CategoryMenu;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final static String XPATH_FIRST_PRODUCT = "//*[@class='main-page__content']//article[1]";
    private final static String ID_SEARCH_FIELD = "#searchInput";
    private final static String XPATH_BUTTON_CHANGE_CITY = "//span[@data-wba-header-name='DLV_Adress']";

    private final static String CSS_BUTTON_FILTERS = ".nav-element__burger.j-menu-burger-btn.j-wba-header-item";

    private final static String CSS_CATEGORY_MENU = ".menu-burger__main.j-menu-burger-main.j-menu-active";

    private CategoryMenu categoryMenu = new CategoryMenu();

    public MainPage waitingLoadFirstProduct() {

        $x(XPATH_FIRST_PRODUCT).shouldBe(enabled);

        return this;

    }

    public SelenideElement getSearchField() {

        return $(ID_SEARCH_FIELD);
    }

    public CatalogPage findProduct(String product) {

        SelenideElement searchInput = getSearchField();
        searchInput.click();
        searchInput.setValue(product);
        searchInput.sendKeys(Keys.ENTER);

        return new CatalogPage();
    }

    public SelenideElement getButtonChangeCity() {

        return $x(XPATH_BUTTON_CHANGE_CITY);

    }


    public GeoPage clickButtonChangeCity() {

        getButtonChangeCity().click();

        return new GeoPage();
    }

    public MainPage clickButtonFilters() {

        $(CSS_BUTTON_FILTERS).click();

        $(CSS_CATEGORY_MENU).hover();

        return this;

    }

    public MainPage hoverToHouseAppliances() {
        return categoryMenu.hoverToHouseAppliances();
    }

    public MainPage clickAppliancesForHouse() {
        categoryMenu.clickAppliancesForHouse();

        return this;
    }

    public MainPage clickHooversAndSteamCleaners() {
        return categoryMenu.clickHooversAndSteamCleaners();
    }

    public CatalogPage clickRobotHoovers() {
        return categoryMenu.clickRobotHoovers();
    }

    public MainPage hoverToElectronics() {
        return categoryMenu.hoverToElectronics();
    }

    public MainPage clickLaptopsAndComputers() {
        return categoryMenu.clickLaptopsAndComputers();
    }

    public CatalogPage clickLaptops() {
        return categoryMenu.clickLaptops();
    }


}

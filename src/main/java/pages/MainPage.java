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

    public void waitingLoadFirstProduct() {

        $x(XPATH_FIRST_PRODUCT).shouldBe(enabled);

    }

    public SelenideElement getSearchField() {

        return $(ID_SEARCH_FIELD);
    }

    public void findProduct(String product) {

        SelenideElement searchInput = getSearchField();
        searchInput.click();
        searchInput.setValue(product);
        searchInput.sendKeys(Keys.ENTER);
    }

    public SelenideElement getButtonChangeCity() {

        return $x(XPATH_BUTTON_CHANGE_CITY);

    }


    public void clickButtonChangeCity() {

        getButtonChangeCity().click();

    }

    public void clickButtonFilters() {

        $(CSS_BUTTON_FILTERS).click();

    }

    public void hoverToCategoryMenu() {

        $(CSS_CATEGORY_MENU).hover();

    }

    public void hoverToHouseAppliances() {
        categoryMenu.hoverToHouseAppliances();
    }

    public void clickAppliancesForHouse() {
        categoryMenu.clickAppliancesForHouse();
    }

    public void clickHooversAndSteamCleaners() {
        categoryMenu.clickHooversAndSteamCleaners();
    }

    public SelenideElement getRobotHoovers() {

        return categoryMenu.getRobotHoovers();

    }

    public void clickRobotHoovers() {
        categoryMenu.clickRobotHoovers();
    }

    public void hoverToElectronics() {
        categoryMenu.hoverToElectronics();
    }

    public void clickLaptopsAndComputers() {
        categoryMenu.clickLaptopsAndComputers();
    }

    public void clickLaptops() {
        categoryMenu.clickLaptops();
    }


}

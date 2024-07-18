package elements;

import com.codeborne.selenide.SelenideElement;
import pages.CatalogPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CategoryMenu {

    private final static String CSS_HOUSE_APPLIANCES = ".menu-burger__main-list-link.menu-burger__main-list-link--16107";

    private final static String XPATH_APPLIANCES_FOR_HOUSE = "//div[@data-menu-id='16107']/div/div/ul/li[4]/span";

    private final static String XPATH_CATEGORY_HOOVERS_AND_STEAM_CLEANERS = "//div[@data-menu-id='16107']/div/div[2]/ul/li[5]/span";

    private final static String XPATH_CATEGORY_ROBOT_HOOVERS = "//div[@data-menu-id='16107']//*[text()='Роботы-пылесосы']";

    private final static String XPATH_ELECTRONICS_CATEGORY = "//li[@data-menu-id='4830']/span";

    private final static String XPATH_LAPTOPS_AND_COMPUTERS_CATEGORY = "//div[@data-menu-id='4830']//ul/li[7]/span";

    public final static String XPATH_LAPTOPS_CATEGORY = "//div[@data-menu-id='4830']/div/div[2]/ul/li[1]/a";

    public void hoverToHouseAppliances() {
        SelenideElement houseAppliances = $(CSS_HOUSE_APPLIANCES);
        houseAppliances.scrollTo();
        houseAppliances.shouldBe(enabled).hover();
    }

    public void clickAppliancesForHouse() {
        $x(XPATH_APPLIANCES_FOR_HOUSE).click();
    }

    public void clickHooversAndSteamCleaners() {
        $x(XPATH_CATEGORY_HOOVERS_AND_STEAM_CLEANERS).click();
    }

    public CatalogPage clickRobotHoovers() {
        $x(XPATH_CATEGORY_ROBOT_HOOVERS).click();
        return new CatalogPage();
    }

    public void hoverToElectronics() {
        $x(XPATH_ELECTRONICS_CATEGORY).shouldBe(enabled).scrollTo().hover();
    }

    public void clickLaptopsAndComputers() {
        $x(XPATH_LAPTOPS_AND_COMPUTERS_CATEGORY).click();
    }

    public CatalogPage clickLaptops() {
        $x(XPATH_LAPTOPS_CATEGORY).click();

        return new CatalogPage();
    }




}

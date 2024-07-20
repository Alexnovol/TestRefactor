package elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.CatalogPage;

import static com.codeborne.selenide.Selenide.*;

public class Filters {

    private final static String XPATH_PRICE_FROM = "//*[@class='filter__price']/div[1]//input";

    private final static String XPATH_PRICE_TO = "//*[@class='filter__price']/div[2]//input";

    private final static String XPATH_BRAND_TEXT = "//*[@class='filters-desktop__item j-filter-container filters-desktop__item--type-1 filters-desktop__item--fbrand open show']//*[text()='Apple']";

    private final static String XPATH_DIAGONAL_TEXT = "//*[@class='filters-desktop__item j-filter-container filters-desktop__item--type-1 filters-desktop__item--f4474 open show']//*[text()='13.3\"']";

    private final static String XPATH_BRAND = "//*[@class='filters-desktop__item j-filter-container filters-desktop__item--type-1 filters-desktop__item--fbrand open show']//*[text()='Apple']/preceding-sibling::span";

    private final static String XPATH_DIAGONAL = "//*[@class='filters-desktop__item j-filter-container filters-desktop__item--type-1 filters-desktop__item--f4474 open show']//*[text()='13.3\"']/preceding-sibling::span";

    public final static String CLASS_COUNT_FILTERED_GOODS = ".filters-desktop__count-goods";

    private final static String CSS_BUTTON_SHOWING = ".filters-desktop__btn-main.btn-main";

    public CatalogPage enterPrice(String priceFrom, String priceTo) {
        SelenideElement elemPriceFrom = $x(XPATH_PRICE_FROM);
        elemPriceFrom.clear();
        elemPriceFrom.setValue(priceFrom);

        SelenideElement elemPriceTo = $x(XPATH_PRICE_TO);
        elemPriceTo.clear();
        elemPriceTo.setValue(priceTo);

        return new CatalogPage();
    }

    public String getBrandText() {

        return $x(XPATH_BRAND_TEXT).getOwnText().replaceAll("\\W", "");

    }

    public String getDiagonalText() {
        String diagonalText = $x(XPATH_DIAGONAL_TEXT).getOwnText();
        diagonalText = diagonalText.substring(0, diagonalText.indexOf("\"") + 1);

        return diagonalText;

    }

    public CatalogPage clickFilterBrandAndDiagonal() {
        $x(XPATH_BRAND).click();
        $x(XPATH_DIAGONAL).click();
        Selenide.sleep(2000);

        return new CatalogPage();
    }

    public String getCountFilteredGoods() {

        return $(CLASS_COUNT_FILTERED_GOODS).text().replaceAll("\\D+", "");

    }

    public CatalogPage clickButtonShowing() {
        $(CSS_BUTTON_SHOWING).click();

        return new CatalogPage();
    }
}

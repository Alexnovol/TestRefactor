package elements;

import com.codeborne.selenide.SelenideElement;
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

    public void enterPriceFrom(String price) {
        SelenideElement priceFrom = $x(XPATH_PRICE_FROM);
        priceFrom.clear();
        priceFrom.setValue(price);
    }

    public void enterPriceTo(String price) {
        SelenideElement priceTo = $x(XPATH_PRICE_TO);
        priceTo.clear();
        priceTo.setValue(price);
    }

    public String getBrandText() {

        return $x(XPATH_BRAND_TEXT).getOwnText().replaceAll("\\W", "");

    }

    public String getDiagonalText() {
        String diagonalText = $x(XPATH_DIAGONAL_TEXT).getOwnText();
        diagonalText = diagonalText.substring(0, diagonalText.indexOf("\"") + 1);

        return diagonalText;

    }

    public void clickBrand() {
        $x(XPATH_BRAND).click();
    }

    public void clickDiagonal() {
        $x(XPATH_DIAGONAL).click();
    }

    public String getCountFilteredGoods() {

        return $(CLASS_COUNT_FILTERED_GOODS).text().replaceAll("\\D+", "");

    }

    public void clickButtonShowing() {
        $(CSS_BUTTON_SHOWING).click();
    }
}

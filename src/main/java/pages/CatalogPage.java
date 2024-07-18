package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Filters;

import java.util.ArrayList;
import java.util.List;

import static asserts.Gui.shouldBeEquals;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    private final static String CLASS_TITLE_RESULTS = ".searching-results__title";

    private final static String XPATH_FIRST_FILTER = "//*[@class='filters-block__dropdown j-filtres-container']/div[@class='dropdown-filter'][1]/button";

    private final static String XPATH_SECOND_FILTER = "//*[@class='filters-block__dropdown j-filtres-container']/div[@class='dropdown-filter'][2]/button";

    private final static String XPATH_FIRST_PRODUCT = "//*[@class='product-card-list']/article[1]";

    private final static String XPATH_BRAND_FIRST_PRODUCT = XPATH_FIRST_PRODUCT + "//span[@class='product-card__brand']";

    private final static String XPATH_NAME_FIRST_PRODUCT = XPATH_FIRST_PRODUCT + "//*[@class='product-card__name']";

    private final static String XPATH_PRICE_FIRST_PRODUCT = XPATH_FIRST_PRODUCT + "//*[@class='price__wrap']/ins";

    private final static String XPATH_IN_BASKET_BUTTON_FIRST_PRODUCT =  XPATH_FIRST_PRODUCT + "//*[@href='/lk/basket']";

    private final static String CSS_BUTTON_CLEAR_SEARCH_FIELD = ".search-catalog__btn.search-catalog__btn--clear.search-catalog__btn--active";

    private final static String CLASS_CATALOG_TITLE = ".catalog-title";

    private final static String CLASS_FILTERS_ON_PAGE = ".breadcrumbs__item";

    private final static String CLASS_COUNT_PRODUCTS_BASKET = ".navbar-pc__notify";

    private final static String XPATH_BASKET_BUTTON = "//*[@class='navbar-pc__item j-item-basket']/a";

    private final static String CSS_BUTTON_ALL_FILTERS = ".dropdown-filter.j-show-all-filtres";

    private final static String XPATH_COUNT_FILTERED_GOODS = "//*[@class='goods-count']/span[1]";

    private final static String XPATH_SELECTED_FILTER_BRAND = "//*[@class='your-choice__list']/li[1]/span";

    private final static String XPATH_SELECTED_FILTER_PRICE = "//*[@class='your-choice__list']/li[2]/span";

    private final static String XPATH_SELECTED_FILTER_DIAGONAL = "//*[@class='your-choice__list']/li[3]/span";

    private final static String XPATH_BUTTON_RESET = "//*[@class='your-choice__list']/li[4]/button";

    private Filters filters = new Filters();

    public void clearSearchField() {
        $(CSS_BUTTON_CLEAR_SEARCH_FIELD).click();
    }

    public void checkCatalogTitleAndFilters(String expCatalogTitle, List<String> expFilters) {
        SelenideElement catalogTitle = $(CLASS_CATALOG_TITLE);
        catalogTitle.shouldHave(text(expCatalogTitle));

        List<String> filters = new ArrayList<>();
        $$(CLASS_FILTERS_ON_PAGE).stream().forEach(filter -> filters.add(filter.$("span").text()));
        shouldBeEquals(expFilters, filters);
    }

    public String getNameFirstProduct() {

        return $x(XPATH_NAME_FIRST_PRODUCT).text().replace("/ ", "");

    }

    public String getPriceFirstProduct() {

        return $x(XPATH_PRICE_FIRST_PRODUCT).text();
    }

    public void clickButtonInBasket() {
        $x(XPATH_IN_BASKET_BUTTON_FIRST_PRODUCT).scrollTo().click();
    }

    public void checkCountProductsBasket(String expCount) {
        $(CLASS_COUNT_PRODUCTS_BASKET).shouldHave(text(expCount));
    }

    public BasketPage clickButtonBasket() {
        $x(XPATH_BASKET_BUTTON).click();
        Selenide.sleep(1000);

        return new BasketPage();
    }

    public void clickButtonAllFilters() {
        $(CSS_BUTTON_ALL_FILTERS).click();
    }

    public String getCountFilteredGoodsOnPage() {

        return $x(XPATH_COUNT_FILTERED_GOODS).text().replaceAll(" ", "");

    }

    public void checkSelectedFiltersAndButtonReset(String expBrand, String expDiagonal, String expPriceFrom, String expPriceTo) {
        $x(XPATH_SELECTED_FILTER_BRAND).shouldHave(text(expBrand));
        $x(XPATH_SELECTED_FILTER_DIAGONAL).shouldHave(text(expDiagonal));
        $x(XPATH_SELECTED_FILTER_PRICE).shouldHave(text(String.format("от %s до %s", expPriceFrom, expPriceTo)));
        $x(XPATH_BUTTON_RESET).shouldBe(enabled);
    }

    public void enterPrice(String priceFrom, String priceTo) {
        filters.enterPrice(priceFrom, priceTo);
    }

    public String getFilterBrandText() {

        return filters.getBrandText();

    }

    public String getFilterDiagonalText() {

        return filters.getDiagonalText();

    }

    public void clickFilterBrandAndDiagonal() {
        filters.clickFilterBrandAndDiagonal();
    }

    public String getCountFilteredGoodsInFilters() {

        return filters.getCountFilteredGoods();

    }

    public void clickButtonShowing() {
        filters.clickButtonShowing();
    }

    public void checkSearchResults(String expTitleRes, String expFirstFilter, String expSecondFilter, String expBrandFirstProduct) {
        $(CLASS_TITLE_RESULTS).shouldHave(text(expTitleRes));
        $x(XPATH_FIRST_FILTER).shouldHave(text(expFirstFilter));
        $x(XPATH_SECOND_FILTER).shouldHave(text(expSecondFilter));
        $x(XPATH_BRAND_FIRST_PRODUCT).shouldHave(text(expBrandFirstProduct));
    }

}

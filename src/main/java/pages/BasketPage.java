package pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BasketPage {

    public final static String CLASS_NAME_PRODUCT = ".good-info__good-name";

    public final static String CLASS_PRICE_PRODUCT = ".list-item__price-new";

    public final static String XPATH_PRICE_TOTAL = "//*[@class='b-top__total line']/span[2]";

    public final static String CSS_ORDER_BUTTON = ".b-btn-do-order.btn-main.j-btn-confirm-order";

    public BasketPage checkDataInBasket(String expNameProduct, String expPriceProduct) {

        $(CLASS_NAME_PRODUCT).shouldHave(text(expNameProduct));
        $(CLASS_PRICE_PRODUCT).shouldHave(text(expPriceProduct));
        $x(XPATH_PRICE_TOTAL).shouldHave(text(expPriceProduct));
        $(CSS_ORDER_BUTTON).shouldBe(enabled);

        return this;
    }


}

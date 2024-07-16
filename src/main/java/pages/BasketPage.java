package pages;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;

public class BasketPage {

    public final static String CLASS_NAME_PRODUCT = ".good-info__good-name";

    public final static String CLASS_PRICE_PRODUCT = ".list-item__price-new";

    public final static String XPATH_PRICE_TOTAL = "//*[@class='b-top__total line']/span[2]";

    public final static String CSS_ORDER_BUTTON = ".b-btn-do-order.btn-main.j-btn-confirm-order";


    public SelenideElement getNameProduct() {

        return $(CLASS_NAME_PRODUCT);

    }

    public SelenideElement getPriceProduct() {

        return $(CLASS_PRICE_PRODUCT);

    }

    public SelenideElement getPriceTotal() {

        return $x(XPATH_PRICE_TOTAL);

    }

    public SelenideElement getButtonOrder() {

        return $(CSS_ORDER_BUTTON);
    }


}

package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class GeoPage {

    private final static String XPATH_ADDRESS_ENTRY = "//input[@placeholder='Введите адрес' and @autocomplete]";

    private final static String XPATH_BUTTON_FIND = "//*[@class='ymaps-2-1-79-searchbox-button ymaps-2-1-79-_pin_right ymaps-2-1-79-user-selection-none']/ymaps";

    private final static String XPATH_ANY_DELIVERY_POINT = "//*[@id='pooList']//*[contains(text(), 'city')]";

    private final static String XPATH_FIRST_DELIVERY_POINT = "//*[@id='pooList']/div[1]";

    private final static String XPATH_ADDRESS_FIRST_DELIVERY_POINT = ".//span[@class='address-item__name-text']/span";

    private final static String CLASS_INFO_DELIVERY_POINT = ".details-self__title";

    private final static String CSS_BUTTON_SELECT_DELIVERY_POINT = ".details-self__btn.btn-main";

    private final static String CLASS_ADDRESS_DELIVERY_POINT = ".details-self__name-text";

    public void enterCity(String city) {

        SelenideElement searchField = $x(XPATH_ADDRESS_ENTRY);
        searchField.click();
        searchField.setValue(city);
        $x(XPATH_BUTTON_FIND).click();

    }

    public void waitingLoadAnyDeliveryPoint(String city) {

        $x(XPATH_ANY_DELIVERY_POINT.replace("city", city)).shouldBe(visible);

    }

    public String getFirstAddress() {

        return $x(XPATH_FIRST_DELIVERY_POINT).$x(XPATH_ADDRESS_FIRST_DELIVERY_POINT).text();

    }

    public void clickFirstAddress() {

        $x(XPATH_FIRST_DELIVERY_POINT).click();

    }

    public void clickButtonSelectDeliveryPoint() {

        $(CSS_BUTTON_SELECT_DELIVERY_POINT).click();

    }

    public void checkInfoDeliveryPoint(WebElementCondition condition) {
        $(CLASS_INFO_DELIVERY_POINT).shouldBe(condition);
    }

    public void checkAddressDeliveryPoint(String expAddress) {
        $(CLASS_ADDRESS_DELIVERY_POINT).shouldHave(text(expAddress));
    }
}

package pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class FeedPage extends Header {
    public static final String URL = "https://stellarburgers.nomoreparties.site/feed";

    @FindBy(how = How.XPATH, using = ".//h1[text()='Лента заказов']")
    private SelenideElement titleMainFeed;

    @Step("Ожидание загрузки страницы Список Заказов (проверяем, что появился заголовок страницы)")
    public FeedPage waitForLoadFeedPage() {
        titleMainFeed.shouldBe(visible);
        return Selenide.page(FeedPage.class);
    }
}

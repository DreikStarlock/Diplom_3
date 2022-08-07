package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class ProfilePage extends Header {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement buttonExit;

    @Step("Ожидание загрузки страницы Личный Кабинет (проверяем, что появилась кнопка Выход)")
    public ProfilePage waitForLoadProfilePage() {
        buttonExit.shouldBe(visible);
        return Selenide.page(ProfilePage.class);
    }

    @Step("Получаем название кнопки Выход")
    public String getExitText() {
        return buttonExit.shouldBe(Condition.appear).getText();
    }

    @Step("Нажимаем на кнопку Выход")
    public LoginPage logout() {
        buttonExit.shouldBe(visible).click();
        return Selenide.page(LoginPage.class);
    }
}

package pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage extends Header {
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    @Step("Нажимаем на кнопку Войти на странице Восстановление Пароля")
    public LoginPage clickLoginButton() {
        buttonLogin.click();
        return Selenide.page(LoginPage.class);
    }
}

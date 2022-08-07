package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class LoginPage extends Header {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement titleEnter;

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement buttonEnter;

    @Step("Ожидание загрузки страницы Вход (проверяем, что появился заголовок страницы)")
    public void waitForLoadLoginPage() {
        titleEnter.shouldBe(visible);
    }

    @Step("Получаем заголовок Вход")
    public String getPageTitle() {
        return titleEnter.shouldBe(Condition.appear).getText();
    }

    @Step("Заполняем поле email")
    public void setEmail(String email) {
        inputEmail.setValue(email);
    }

    @Step("Заполняем поле password")
    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    @Step("Нажимаем на кнопку Войти")
    public void clickRegisterButton() {
        buttonEnter.click();
    }

    public ConstructorPage login(String email, String password) {
        waitForLoadLoginPage();
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
        return Selenide.page(ConstructorPage.class);
    }
}

package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class RegisterPage extends Header {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = ".//h2[text()='Регистрация']")
    private SelenideElement titleRegister;

    //невозможно сократить путь до элемента в дереве, Т.К. фронтендом/верстальщиком не было установлена особенность данного элемента
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement inputName;

    //невозможно сократить путь до элемента в дереве, Т.К. фронтендом/верстальщиком не было установлена особенность данного элемента
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement inputPasswordError;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegister;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    @Step("Ожидание загрузки страницы Register (проверяем, что появился заголовок страницы)")
    public void waitForLoadRegisterPage() {
        titleRegister.shouldBe(visible);
    }

    @Step("Заполняем поле name")
    public void setName(String name) {
        inputName.setValue(name);
    }

    @Step("Заполняем поле email")
    public void setEmail(String email) {
        inputEmail.setValue(email);
    }

    @Step("Заполняем поле password")
    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    @Step("Нажимаем на кнопку Регистрация")
    public void clickRegisterButton() {
        buttonRegister.click();
    }

    @Step("Получаем сообщение ошибки поля Password")
    public String getInputPasswordError() {
        return inputPasswordError.shouldBe(Condition.appear).getText();
    }

    public LoginPage register(String name, String email, String password) {
        waitForLoadRegisterPage();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
        return Selenide.page(LoginPage.class);
    }

    public String registerWithIncorrectPassword(String name, String email, String password) {
        waitForLoadRegisterPage();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
        return getInputPasswordError();
    }

    public LoginPage clickLoginButton() {
        buttonLogin.click();
        return Selenide.page(LoginPage.class);
    }
}

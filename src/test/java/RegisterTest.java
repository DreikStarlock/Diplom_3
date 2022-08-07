import api.UserApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegisterPage;
import pojo.UserCredentials;
import pojo.UserPojo;

public class RegisterTest {
    private UserPojo user;
    private UserApi userApi;
    private boolean loginSuccess;
    private Response loginResponse;
    private String accessToken;

    @Before
    public void setUp() {
        Configuration.browserSize = "1600x900";
        Configuration.browserPosition = "0x0";
        userApi = new UserApi();
    }

    @After
    public void teardown() {
        Selenide.closeWindow();
        if (loginSuccess) {
            Response deleteResponse = userApi.sendDeleteUser(accessToken);
            boolean deleted = userApi.userDeletedSuccess(deleteResponse);
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void checkRegisterSuccessTest() {
        //Тест
        String expectedResult = "Оформить заказ";
        String name = "Daniil";
        String email = "d.b@scs.com";
        String password = "12345678";
        String actualResult = Selenide.open(RegisterPage.URL, RegisterPage.class)
                .register(name, email, password)
                .login(email, password)
                .getButtonMakeOrderText();
        //Для удаления пользователя с помощью API
        UserCredentials credentials = new UserCredentials(email, password);
        loginResponse = userApi.sendPostLoginUser(credentials);
        accessToken = userApi.userAccessToken(loginResponse);
        loginSuccess = userApi.userLoginSuccess(loginResponse);
        //Проверка результатов
        Assert.assertEquals("Ожидается, что после регистрации и логина откроется страница Конструктор и появится кнопка Оформить заказ", expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка валидации пароля")
    public void checkRegisterIncorrectPasswordTest() {
        String expectedResult = "Некорректный пароль";
        String name = "Daniil";
        String email = "d.b@scs.com";
        String password = "12345";
        String actualResult = Selenide.open(RegisterPage.URL, RegisterPage.class)
                .registerWithIncorrectPassword(name, email, password);
        Assert.assertEquals("Текст ошибки о некорректном пароле не совпадает с ожидаемым", expectedResult, actualResult);
    }
}

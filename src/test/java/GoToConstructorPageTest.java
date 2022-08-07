import api.UserApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pojo.UserCredentials;
import pojo.UserPojo;

public class GoToConstructorPageTest {
    private UserPojo user;
    private UserApi userApi;
    private Response response;
    private boolean created;
    private String accessToken;
    private UserCredentials credentials;

    @Before
    public void setup() {
        Configuration.browserSize = "1600x900";
        Configuration.browserPosition = "0x0";
        String name = "Daniil";
        String email = "d.b@scs.com";
        String password = "12345678";
        userApi = new UserApi();
        user = new UserPojo(email, password, name);
        response = userApi.sendPostRequestRegisterUser(user);
        created = userApi.userCreatedSuccess(response);
        accessToken = userApi.userAccessToken(response);
        credentials = UserCredentials.from(user);
    }

    @After
    public void teardown() {
        Selenide.closeWindow();
        if (created) {
            Response deleteResponse = userApi.sendDeleteUser(accessToken);
            boolean deleted = userApi.userDeletedSuccess(deleteResponse);
        }
    }

    @Test
    @DisplayName("Проверка перехода на страницу Конструктор со страницы Личный Кабинет")
    public void goToBurgerConstructorPageFromProfilePageByNav() {
        String expectedResult = "Оформить заказ";
        String actualResult = Selenide.open(LoginPage.URL, LoginPage.class)
                .login(credentials.getEmail(), credentials.getPassword())
                .goToProfilePage()
                .waitForLoadProfilePage()
                .goToConstructorPageByNav()
                .getButtonMakeOrderText();
        Assert.assertEquals("Ожидается, что при переходе из страницы Личный Кабинет откроется страница Консруктор и появится кнопка Оформить заказ", expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка перехода на страницу Конструктор со страницы Личный Кабинет")
    public void goToBurgerConstructorPageFromProfilePageByLogo() {
        String expectedResult = "Оформить заказ";
        String actualResult = Selenide.open(LoginPage.URL, LoginPage.class)
                .login(credentials.getEmail(), credentials.getPassword())
                .goToProfilePage()
                .waitForLoadProfilePage()
                .goToConstructorPageByLogo()
                .getButtonMakeOrderText();
        Assert.assertEquals("Ожидается, что при переходе из страницы Личный Кабинет откроется страница Консруктор и появится кнопка Оформить заказ", expectedResult, actualResult);
    }
}

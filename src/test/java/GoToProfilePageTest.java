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

public class GoToProfilePageTest {
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
    @DisplayName("Проверка перехода на страницу Личный кабинет со страницы Конструктор")
    public void goToProfilePageFromBurgerConstructorPage() {
        String expectedResult = "Выход";
        String actualResult = Selenide.open(LoginPage.URL, LoginPage.class)
                .login(credentials.getEmail(), credentials.getPassword())
                .goToProfilePage()
                .getExitText();
        Assert.assertEquals("Ожидается, что откроется страница Profile и появится кнопка Выход", expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка перехода на страницу Личный кабинет со страницы Лента Заказов")
    public void goToProfilePageFromFeedPage() {
        String expectedResult = "Выход";
        String actualResult = Selenide.open(LoginPage.URL, LoginPage.class)
                .login(credentials.getEmail(), credentials.getPassword())
                .goToFeedPage()
                .waitForLoadFeedPage()
                .goToProfilePage()
                .getExitText();
        Assert.assertEquals("Ожидается, что откроется страница Profile и появится кнопка Выход", expectedResult, actualResult);
    }
}

package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.UserCredentials;
import pojo.UserPojo;

public class UserApi extends MainApi {
    @Step("Послать POST запрос на ручку /auth/register")
    public Response sendPostRequestRegisterUser(UserPojo userPojo) {
        return reqSpec.body(userPojo)
                .when()
                .post("/auth/register");
    }

    @Step("Послать POST запрос на ручку /auth/login")
    public Response sendPostLoginUser(UserCredentials credentials) {
        return reqSpec.body(credentials)
                .when()
                .post("/auth/login");
    }

    @Step("Получить статус об успешном создании пользователя - 200")
    public boolean userCreatedSuccess(Response response) {
        return response.then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("success");
    }

    @Step("Послать DELETE запрос на ручку /auth/user c accessToken")
    public Response sendDeleteUser(String token) {
        String pureToken = token.substring(7);
        return reqSpec.auth().oauth2(pureToken)
                .when()
                .delete("/auth/user");
    }

    @Step("Получить accessToken")
    public String userAccessToken(Response response) {
        return response.then()
                .extract()
                .path("accessToken");
    }

    @Step("Получить статус об успешном логине пользователя - 200")
    public boolean userLoginSuccess(Response response) {
        return response.then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("success");
    }

    @Step("Получить статус об успешном удалении пользователя - 202")
    public boolean userDeletedSuccess(Response response) {
        return response.then()
                .assertThat()
                .statusCode(202)
                .extract()
                .path("success");
    }

}

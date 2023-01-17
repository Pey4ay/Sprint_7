import courier.CourierClientResponse;
import courier.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import courier.json.CourierCreateJson;
import courier.json.CourierLoginJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginCourierTest {
    private CourierCreateJson courier;
    private CourierClientResponse courierClient;
    private int id;

    @Before
    public void setUp(){
        courierClient = new CourierClientResponse();
        courier = CourierGenerator.getDefault();
        ValidatableResponse response = courierClient.create(courier);
    }

    @After
    public void cleanUp(){
        courierClient.delete(id);
    }

    //Тест с положительным результатом авторизации (пользователь существует и все поля передаются)
    @Test
    @DisplayName("Login courier with full information")
    public void loginNewCourierCorrect(){
        ValidatableResponse loginResponse = courierClient.login(CourierLoginJson.from(courier));
        assertNotNull(loginResponse.extract().path("id"));
        this.id = loginResponse.extract().path("id");
    }

    //Тест с проверкой передачи при авторизации только логина (без пароля)
    @Test
    @DisplayName("Login courier with only loginName")
    public void loginNewCourierWithOnlyLogin(){
        ValidatableResponse loginResponse = courierClient.login(CourierLoginJson.from(CourierGenerator.getBadDefault()));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals(loginResponse.extract().path("message"),SC_BAD_REQUEST,statusCode);

        //подключаемся к  пользователю с корректными данными, так как необходимо его удалить
        ValidatableResponse loginResponse2 = courierClient.login(CourierLoginJson.from(courier));
        this.id = loginResponse2.extract().path("id");
    }

    //Тест с авторизацией несуществующего пользователя
    @Test
    @DisplayName("Login not created courier")
    public void loginWithNotCreatedCourier(){
        ValidatableResponse loginResponse = courierClient.login(CourierLoginJson.from(CourierGenerator.getNotCreatedCourier()));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);

        //подключаемся к  пользователю с корректными данными, так как необходимо его удалить
        ValidatableResponse loginResponse2 = courierClient.login(CourierLoginJson.from(courier));
        this.id = loginResponse2.extract().path("id");
    }

}

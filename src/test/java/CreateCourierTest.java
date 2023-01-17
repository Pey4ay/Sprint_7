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

public class CreateCourierTest {

    private CourierCreateJson courier;
    private CourierClientResponse courierClient;
    private int id;

    @Before
    public void setUp(){
        courierClient = new CourierClientResponse();
    }

    @After
    public void cleanUp(){
        courierClient.delete(id);
    }

    //Тест на создание курьера со всеми полями и без повоторения
    @Test
    @DisplayName("Create courier with full information")
    public void createNewCourierWithFullInformationAndNotRepeat(){
        courier = CourierGenerator.getDefault();
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierLoginJson.from(courier));

        int statusCode = response.extract().statusCode();
        assertEquals(response.extract().path("message"), SC_CREATED, statusCode);

        this.id = loginResponse.extract().path("id");
    }

    //Тест на проверку повторного создания курьера
    @Test
    @DisplayName("Double create courier and check status code in second created")
    public void createNewCourierWithRepeat(){
        courier = CourierGenerator.getDefault();
        ValidatableResponse response = courierClient.create(courier);//Создание первого курьера
        ValidatableResponse response2 = courierClient.create(courier);//Создание второго курьера со схожими данными
        ValidatableResponse loginResponse = courierClient.login(CourierLoginJson.from(courier));

        int statusCode = response2.extract().statusCode();
        assertEquals(response2.extract().path("message"),SC_CONFLICT, statusCode);
        this.id = loginResponse.extract().path("id");
    }

    //Тест на проверку создания курьера не совсеми полями
    @Test
    @DisplayName("Create courier with only login")
    public void createNewCourierWithNotFullInformation(){
        courier = CourierGenerator.getBadDefault();
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertEquals(response.extract().path("message"), SC_BAD_REQUEST, statusCode);
    }
}

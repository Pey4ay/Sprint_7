package courier;

import constants.Client;
import io.restassured.response.ValidatableResponse;
import courier.json.CourierCreateJson;
import courier.json.CourierLoginJson;

import static io.restassured.RestAssured.given;


public class CourierClientResponse extends Client {

    public ValidatableResponse create (CourierCreateJson courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(API_CREATE_COURIER)
                .then();
    }

    public ValidatableResponse login(CourierLoginJson courierLogin){
        return given()
                .spec(getSpec())
                .body(courierLogin)
                .when()
                .post(API_LOGIN_COURIER)
                .then();
    }

    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())
                .when()
                .delete(String.format(API_DELETE_COURIER, id))
                .then();
    }

}

package order;

import constants.Client;
import io.restassured.response.ValidatableResponse;
import order.json.OrderCreateJson;
import static io.restassured.RestAssured.given;

public class OrderResponse extends Client {
    public ValidatableResponse create(OrderCreateJson order){
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(API_CREATE_ORDER)
                .then();
    }

    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())
                .when()
                .put(API_DELETE_ORDER)
                .then();
    }

    public ValidatableResponse getListOrders(){
        return given()
                .spec(getSpec())
                .when()
                .get(API_GET_LIST_ORDER)
                .then();
    }
}

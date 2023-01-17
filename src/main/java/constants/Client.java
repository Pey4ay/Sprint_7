package constants;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {
    public static final String API_LOGIN_COURIER = "api/v1/courier/login";
    public static final String API_CREATE_COURIER = "api/v1/courier";
    public static final String API_DELETE_COURIER = "api/v1/courier/%s";
    public static final String URL_HOMEPAGE = "http://qa-scooter.praktikum-services.ru/";
    public static final String API_CREATE_ORDER = "/api/v1/orders";
    public static final String API_DELETE_ORDER = "/api/v1/orders/cancel";
    public static final String API_GET_LIST_ORDER = "/api/v1/orders";

    protected RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(URL_HOMEPAGE)
                .build();
    }

}

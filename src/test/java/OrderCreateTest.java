import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import order.OrderResponse;
import order.json.OrderCreateJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constants.Client.URL_HOMEPAGE;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private static final String[] colorBlack = {"BLACK"};
    private static final String[] colorNot = {""};
    private static final String[] colorAll = {"BLACK", "GREY"};
    private OrderResponse orderResponse;
    private int id;

    @Before
    public void setUp(){
        orderResponse = new OrderResponse();
    }

    @After
    public void cleanUp(){
        orderResponse.delete(id);
    }

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData(){
        return new Object[][]{
                {"Naruto", "Uzumaki", "Konoha, 141 apt.", "5", "8 800 555 35 35", 5, "2023-01-01", "Saske, come back to Konoha", colorBlack}     ,
                {"Saski", "Uchiha", "Konoha, 142 apt.", "4", "4242", 5, "2023-01-02", "OROCHIMARUUU", colorNot}     ,
                {"Kakashi", "Hatage", "Konoha, 143 apt.", "3", "+79998887766", 2, "2023-01-03", "Obitooooo", colorAll}     ,
        };
    }

    @Test
    @DisplayName("Create order with parametrized data")
    public void orderCreateTestCorrect(){
        OrderCreateJson orderCreateJson = new OrderCreateJson(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = orderResponse.create(orderCreateJson);
        assertNotNull(response.extract().path("track"));

        this.id = response.extract().path("track");
    }
}

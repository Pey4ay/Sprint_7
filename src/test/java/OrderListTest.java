import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.OrderResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class OrderListTest {
    private OrderResponse orderResponse;
    @Before
    public void setUp(){
        orderResponse = new OrderResponse();
    }

    @Test
    @DisplayName("Check full orders list")
    public void getListOrders(){
        ValidatableResponse response = orderResponse.getListOrders();
        assertNotNull(response);
    }
}

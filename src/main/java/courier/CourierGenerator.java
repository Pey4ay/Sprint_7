package courier;

import courier.json.CourierCreateJson;

public class CourierGenerator {
    public static CourierCreateJson getDefault() {
        return new CourierCreateJson("Dima123", "12345", "Madara");
    }

    public static CourierCreateJson getBadDefault() {
        return new CourierCreateJson("Dima123", "", "Madara");
    }

    public static CourierCreateJson getNotCreatedCourier(){
        return  new CourierCreateJson("Dima321", "54321", "Madara");
    }
}
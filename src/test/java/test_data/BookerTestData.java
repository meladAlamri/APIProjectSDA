package test_data;

import java.util.HashMap;
import java.util.Map;

public class BookerTestData {

    public static Map<String,String> bookingDataMethod(String checkin,String checkout ) {

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkin);
        bookingDatesMap.put("checkout", checkout);
        return bookingDatesMap;
    }

    public static Map<String,Object> expectedDataMethod
            (String firstname, String lastname, Integer totalprice, Boolean depositpaid,
             Map<String,String> bookingDatesMap, String additionalNeeds) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingDatesMap);
        expectedData.put("additionalneeds", additionalNeeds);

        return expectedData;
    }
}


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class APITesting {

        @Test
        public void testAPI() {
            Response response = RestAssured.get("https://reqres.in/api/users/2");
            int statusCode = response.getStatusCode();
            System.out.println("Status Code: " + statusCode);

            JsonPath jsonPath = response.jsonPath();
            String firstName = jsonPath.getString("data.first_name");
            System.out.println("First Name: " + firstName);

            String url = jsonPath.getString("support.url");
            System.out.println("Support URL: " + url);

            JSONObject updatedData = new JSONObject(response.asString());
            updatedData.getJSONObject("data").put("email", "updated.email@reqres.in");
            String updatedEmail = updatedData.getJSONObject("data").getString("email");
            System.out.println("Updated Email: " + updatedEmail);

            updatedData.getJSONObject("data").remove("last_name");
            String updatedLastName = updatedData.getJSONObject("data").optString("last_name", "null");
            System.out.println("Updated Last Name: " + updatedLastName);
        }


}

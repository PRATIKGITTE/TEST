
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class UserApiTest {

    private static final String BASE_URL = "https://bfhldevapigw.healthrx.co.in/automation-campus/create/user";

    @Test
    public void testValidUserCreation() {
        given()
            .header("roll-number", "1")
            .contentType(ContentType.JSON)
            .body("{ \"firstName\": \"Test\", \"lastName\": \"User\", \"phoneNumber\": 9999999999, \"emailId\": \"test.user@test.com\" }")
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(201); 
    }

    @Test
    public void testDuplicatePhoneNumber() {
        given()
            .header("roll-number", "1")
            .contentType(ContentType.JSON)
            .body("{ \"firstName\": \"Test\", \"lastName\": \"User\", \"phoneNumber\": 9999999999, \"emailId\": \"test.user1@test.com\" }")
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(400); 
    }

    @Test
    public void testDuplicateEmailId() {
        given()
            .header("roll-number", "1")
            .contentType(ContentType.JSON)
            .body("{ \"firstName\": \"Test\", \"lastName\": \"User\", \"phoneNumber\": 8888888888, \"emailId\": \"test.user@test.com\" }")
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(400); 
    }

    @Test
    public void testMissingRollNumberHeader() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"firstName\": \"Test\", \"lastName\": \"User\", \"phoneNumber\": 8888888888, \"emailId\": \"test.user2@test.com\" }")
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(401); 
    }
    
}

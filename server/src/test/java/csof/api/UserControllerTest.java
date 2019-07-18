package csof.api;

import csof.AbstractIntegrationTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    public void users() {
        given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(SC_OK)
                .body("size()", is(2))
                .body("name", hasItems("John Doe", "Mary Doe"));
    }

    @Test
    public void search() {
        given()
                .when()
                .param("name", "john doe")
                .get("/api/users/search")
                .then()
                .statusCode(SC_OK)
                .body("name", equalTo("John Doe"));
    }

    @Test
    public void searchNotFound() {
        given()
                .when()
                .param("name", "nope")
                .get("/api/users/search")
                .then()
                .statusCode(SC_INTERNAL_SERVER_ERROR);

    }
}
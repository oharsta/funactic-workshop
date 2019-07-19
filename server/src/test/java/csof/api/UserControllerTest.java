package csof.api;

import csof.AbstractIntegrationTest;
import csof.model.User;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    public void users() {
        given()
                .when()
                .filter(cookieFilter())
                .get("/api/users")
                .then()
                .statusCode(SC_OK)
                .body("size()", is(2))
                .body("name", hasItems("John Doe", "Mary Doe"));
    }

    @Test
    public void user() {
        given()
                .when()
                .filter(cookieFilter())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(new User("David Doe"))
                .post("/api/users")
                .then()
                .statusCode(SC_OK)
                .body("id", notNullValue());
        assertEquals(3, mongoTemplate.count(new Query(), User.class));
    }

    @Test
    public void search() {
        given()
                .when()
                .filter(cookieFilter())
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
                .filter(cookieFilter())
                .param("name", "nope")
                .get("/api/users/search")
                .then()
                .statusCode(SC_INTERNAL_SERVER_ERROR);

    }
}
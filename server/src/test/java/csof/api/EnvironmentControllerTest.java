package csof.api;

import csof.AbstractIntegrationTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class EnvironmentControllerTest extends AbstractIntegrationTest {

    @Test
    public void disclaimer() {
        given()
                .when()
                .get("/disclaimer")
                .then()
                .statusCode(SC_OK);
    }
}
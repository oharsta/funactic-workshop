package csof.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import csof.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;


public class MagnoliaControllerTest extends AbstractIntegrationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8091);


    @Test
    public void categories() throws JsonProcessingException {
        stubFor(get(urlEqualTo("/categories")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-type", "application/json")
                .withBody(objectMapper.writeValueAsString(Arrays.asList("wiremock", "stubs")))));

        given()
                .when()
                .filter(cookieFilter())
                .get("/api/categories")
                .then()
                .statusCode(SC_OK)
                .body("size()", is(2))
                .body(".", hasItems("wiremock", "stubs"));
    }
}

package csof;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import csof.model.User;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.data.mongodb.uri=mongodb://localhost:27017/csof_test",
                "magnolia.url=http://localhost:8091",
                "magnolia.feature=true"})
public abstract class AbstractIntegrationTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @LocalServerPort
    protected int port;

    private static List<User> users;

    @Before
    public void before() throws Exception {
        RestAssured.port = port;
        if (users == null) {
            users = objectMapper.readValue(new ClassPathResource("users.json").getInputStream(), new
                    TypeReference<List<User>>() {
                    });
        }
        mongoTemplate.remove(new Query(), "users");
        mongoTemplate.insertAll(users);
        await().until(() -> mongoTemplate.count(new Query(), "users") == users.size());
    }

    protected CookieFilter cookieFilter() {
        CookieFilter cookieFilter = new CookieFilter();
        given()
                .when()
                .filter(cookieFilter)
                .formParams("username", "admin", "password", "secret")
                .post("/api/login")
                .then()
                .statusCode(SC_OK);
        return cookieFilter;
    }

}

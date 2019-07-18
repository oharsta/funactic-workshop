package csof;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import csof.model.User;
import io.restassured.RestAssured;
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

import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.data.mongodb.uri=mongodb://localhost:27017/csof_test"})
public class AbstractIntegrationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;

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


}

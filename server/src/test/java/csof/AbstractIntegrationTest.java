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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.data.mongodb.uri=mongodb://localhost:27017/csof_test",
                "magnolia.url=http://localhost:8091"})
public abstract class AbstractIntegrationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @LocalServerPort
    protected int port;

    private static Map<String, List<Object>> seed;

    private static Map<String, Class> mapping = new HashMap<>();

    static {
        mapping.put("users", User.class);
    }

    @Before
    public void before() throws Exception {
        RestAssured.port = port;
        if (seed == null) {
            seed = objectMapper.readValue(new ClassPathResource("seed.json").getInputStream(), new
                    TypeReference<Map<String, List<Object>>>() {
                    });
        }
        seed.forEach((collection, data) -> {
            mongoTemplate.remove(new Query(), collection);
            List convertedData = List.class.cast(data
                    .stream().map(o -> objectMapper.convertValue(o, mapping.get(collection)))
                    .collect(toList()));
            mongoTemplate.insertAll(convertedData);
            await().until(() -> mongoTemplate.count(new Query(), collection) == data.size());
        });


    }


}

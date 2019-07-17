package csof.api;

import csof.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MockUserController {

    private static final Logger LOG = LoggerFactory.getLogger(MockUserController.class);

    @GetMapping("/api/users")
    public List<User> users() {
        LOG.info("Returning all users");
        return Arrays.asList(new User("John Doe"), new User("Mary Doe"));
    }

}

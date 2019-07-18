package csof.api;

import csof.model.User;
import csof.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public List<User> users() {
        return Arrays.asList(new User("John Doe"), new User("Mary Doe"));
    }
}

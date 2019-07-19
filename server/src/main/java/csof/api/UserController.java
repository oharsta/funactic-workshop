package csof.api;

import csof.model.User;
import csof.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users/search")
    public User search(@RequestParam String name) {
        return userRepository.findOneByNameIgnoreCase(name);
    }

    @PostMapping("/api/users")
    public User user(@RequestBody() User user) {
        return userRepository.insert(user);
    }

    @PostMapping("/api/login")
    public Authentication login(Authentication authentication) {
        return authentication;
    }

}

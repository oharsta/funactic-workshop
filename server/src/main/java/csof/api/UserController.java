package csof.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import csof.model.User;
import csof.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController implements OrderedMap {

    private ObjectMapper objectMapper;
    private UserRepository userRepository;

    public UserController(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
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
    public User user(@RequestBody User user) {
        return userRepository.insert(user);
    }

    @PostMapping("/api/login")
    public Map login(Authentication authentication) {
        return sortMap(objectMapper.convertValue(authentication, Map.class));
    }

}

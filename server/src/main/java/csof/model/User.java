package csof.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;


    public User(String name) {
        this.name = name;
    }

}

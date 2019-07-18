package csof.api;

import csof.cms.Magnolia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MagnoliaController {

    private Magnolia magnolia;

    public MagnoliaController(Magnolia magnolia) {
        this.magnolia = magnolia;
    }

    @GetMapping("/api/categories")
    public List<String> categories() {
        return magnolia.categories();
    }
}

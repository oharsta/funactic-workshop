package csof.cms;

import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.List;

public class MagnoliaMock implements Magnolia {

    @Override
    public List<String> categories() {
        return Arrays.asList("blogs", "news");
    }
}

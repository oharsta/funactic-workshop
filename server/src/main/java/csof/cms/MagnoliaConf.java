package csof.cms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagnoliaConf {

    @Bean
    public Magnolia magnolia(@Value("${magnolia.url}") String url, @Value("${magnolia.feature}") boolean enabled) {
        return enabled ? new MagnoliaService(url) : new MagnoliaMock();
    }

}

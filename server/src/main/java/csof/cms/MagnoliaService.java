package csof.cms;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MagnoliaService implements Magnolia {

    private RestTemplate restTemplate = new RestTemplate();
    private String url;

    public MagnoliaService(String url) {
        this.url = url;
    }

    @Override
    public List<String> categories() {
        return restTemplate.getForEntity(url + "/categories", List.class).getBody();
    }
}

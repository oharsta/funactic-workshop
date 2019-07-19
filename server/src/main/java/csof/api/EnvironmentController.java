package csof.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {

    @Value("${gui.disclaimer.background-color}")
    private String disclaimerBackgroundColor;

    @Value("${gui.disclaimer.content}")
    private String disclaimerContent;

    @GetMapping(path = "/api/disclaimer", produces = "text/css")
    public String disclaimer() {
        return "body::after {background: " + disclaimerBackgroundColor + ";content: \"" + disclaimerContent + "\";}";
    }

}

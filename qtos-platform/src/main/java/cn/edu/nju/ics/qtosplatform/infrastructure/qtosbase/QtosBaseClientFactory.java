package cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase;

import cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase.impl.QtosBaseClientImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QtosBaseClientFactory {
    private final RestTemplate restTemplate;

    public QtosBaseClientFactory(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public QtosBaseClient create(String baseUrl, int port) {
        return new QtosBaseClientImpl(restTemplate, baseUrl, port);
    }
}

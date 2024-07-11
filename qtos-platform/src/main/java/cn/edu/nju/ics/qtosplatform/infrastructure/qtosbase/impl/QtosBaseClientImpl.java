package cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase.impl;

import cn.edu.nju.ics.qtosplatform.infrastructure.qtosbase.QtosBaseClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class QtosBaseClientImpl implements QtosBaseClient {
    private final RestTemplate restTemplate;

    private final String baseUrl;

    private final int port;

    public QtosBaseClientImpl(RestTemplate restTemplate, String baseUrl, int port) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.port = port;
    }

    @Override
    public String upload(MultipartFile archived) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(archived.getOriginalFilename())
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<Resource> fileEntity = new HttpEntity<>(new ByteArrayResource(archived.getBytes()), fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(String.format("http://%s:%d/deploy/upload", baseUrl, port), requestEntity, String.class);
    }

    @Override
    public void install(Map<String, Object> params) {
        restTemplate.postForObject(String.format("http://%s:%d/deploy/install", baseUrl, port), params, Void.class);
    }

    @Override
    public void uninstall(Map<String, Object> params) {
        restTemplate.postForObject(String.format("http://%s:%d/deploy/uninstall", baseUrl, port), params, Void.class);
    }
}

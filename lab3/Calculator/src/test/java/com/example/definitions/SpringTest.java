package com.example.definitions;

import com.example.App;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringTest {
    static HttpEntity<?> latestResponse = new HttpEntity<>(new HttpHeaders());

    @Autowired
    protected RestTemplate restTemplate = new RestTemplate();
    static int status;

    void executeGet(String url, String num1, String num2, String base1, String base2, String operation) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .path("/calculate/{num1}/{base1}/{num2}/{base2}/{operation}")
                .buildAndExpand(num1, base1, num2, base2, operation)
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("num1", num1);
        params.put("base1", base1);
        params.put("num2", num2);
        params.put("base2", base2);
        params.put("operation", operation);

        ResponseEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        status = response.getStatusCodeValue();
        latestResponse = response;
    }

    void executeGetOperationsByType(String url, String operation) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .path("/getoperations/{operation}")
                .buildAndExpand(operation)
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("operation", operation);

        ResponseEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        status = response.getStatusCodeValue();
        latestResponse = response;
    }
}

package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    // http://localhost/api/server/hello

    public UserResponse hello(){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "park")
                .queryParam("age", 34)
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        //ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        log.info(result.getStatusCode().toString());
        log.info(result.getBody().toString());

        return result.getBody();

    }

    public UserResponse post() {
        // http://localhost:9090/api/server/user

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100", "park")
                .toUri();
        log.info(uri.toString());

        // http body -> object -> object mapper -> json -> rest template -> http
        UserRequest request = new UserRequest();
        request.setName("park");
        request.setAge(100);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> responseEntity = restTemplate.postForEntity(uri, request, UserResponse.class);

        log.info(responseEntity.getStatusCode().toString());
        log.info(responseEntity.getHeaders().toString());
        log.info(responseEntity.getBody().toString());

        return responseEntity.getBody();
    }

    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100", "park")
                .toUri();
        log.info(uri.toString());

        // http body -> object -> object mapper -> json -> rest template -> http
        UserRequest request = new UserRequest();
        request.setName("park");
        request.setAge(100);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(request);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(requestEntity, UserResponse.class);
        return responseEntity.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100", "park")
                .toUri();
        log.info(uri.toString());

        // http body -> object -> object mapper -> json -> rest template -> http


        UserRequest request = new UserRequest();
        request.setName("park");
        request.setAge(100);
        Req<UserRequest> req = new Req();
        Req.Header header = new Req.Header();
        header.setResponseCode("jonghoon");
        req.setHeader(
                header
        );

        req.setResponseBody(
            request
        );
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff").body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        return response.getBody();
    }
}

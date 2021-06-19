package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.Search;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@RestController
@Slf4j
@RequestMapping("/api/server")
public class ServerApiController {

    // https://openapi.naver.com/v1/search/local.json
    // ?query=%EC%A3%BC%EC%8B%9D
    // &display=10
    // &start=1
    // &sort=random

    @Value("${naver.client.id}")
    String NAVER_CLIENT_ID;

    @Value("${naver.client.secret}")
    String NAVER_CLIENT_SECRET;

    @GetMapping("/naver")
    public String naver(Search search) {
        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", search.getQuery())
                .queryParam("display", search.getDisplay())
                .queryParam("start", search.getStart())
                .queryParam("sort", search.getSort())
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        log.info("uri: " + uri);
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(@RequestBody Req<User> user,
                          @PathVariable int userId, @PathVariable String userName,
                          @RequestHeader("x-authorization") String authorization,
                          @RequestHeader("custom-header") String customHeaer){
        log.info("userId : {}, useName: {}", userId, userName);
        log.info("authorization : {}, custom : {}", authorization, customHeaer);
        log.info("client req: {}", user );

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setHeader(user.getHeader());
        response.setResponseBody(user.getResponseBody());
        return response;
    }
}

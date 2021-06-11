package com.example.async.controller;

import com.example.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final AsyncService asyncService;

    // CompletableFuture는 다른 쓰레드에서 실행을 시키고 결과를 반환받는 형태임
    @GetMapping("/hello")
    public String hello(){
        log.info("completable future init");
        asyncService.run();
        return "hello";
    }
}

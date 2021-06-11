package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {


    // @Async Proxy패턴을 타며 public 메소드에서만 가능하다
    // Spring Web MVC를 추천 async보단
    // DB 처리시 몽고DB, NoSQL 사용시 Spring Webflux 사용을 추천
    // RDBMS에서 사용시 RDBMS 자체가 트랜잭션이 동기방식이기 때문에 Async가 먹히지 않는다.
    @Async("async-thread")
    public CompletableFuture run(){
        return new AsyncResult(hello()).completable();
    }

    public String hello(){
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                log.info("thread sleep...");
                //System.out.println("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();;
            }
        }
        return "async hello";
    }
}

package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {

    /**
     *
     * 처음 쓰레드 풀이 10개 생성된다
     * 그런데 쓰레드 풀이 10개를 다 사용하게 되면
     * Queue의 10개를 사용하게 된다. 그럼에도 Queue 10개의 쓰레드를 다 사용하게 되면
     * 쓰레드 10개를 더 만든다. => 20개가 됨
     * 그런데 20개를 쓰다가 더 쓰레드가 필요하게 되면 Queue를 사용하게 되고
     * Queue가 차게 되면 10개의 쓰레드가 더 발행됨 => 30개... 이런식으로
     * 늘어남
     *
     * 그런데 물리적인 장비의 스펙도 영향도 미친다.
     * 환경에 따라서 다르고 request 양에 따라서 다르다.
     * 대신 풀이 어떻게 동작하는지 알고 있어야 설정을 할 수 있다.
     * @return
     */

    @Bean("async-thread")
    public Executor asyncThread() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100); //
        threadPoolTaskExecutor.setCorePoolSize(10); //
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        return threadPoolTaskExecutor;
    }
}

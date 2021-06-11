# ASYNC

## ASYNC 적용순서
1) Spring 실행 클래스에 @EnableAsync 적용
2) CompletableFuture 메소드 생성
3) 실행시킬 business Logic 메소드 구현
4) CompletableFuture 메소드에 business Logic 메소드 주입한다.
5) @Configuration 클래스 생성
6) @Configuration 클래스에서 ThreadPoolTaskExecutor 생성 및 환경설정
7) 컨트럴러에서 CompletableFuture 메소드 실행
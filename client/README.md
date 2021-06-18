# Spring Client

## 동적 Request
Request의 정적 영역과 동적 영역을 정의한다.


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Req<T> {
    
        private Header header;  <- 정적인 영역: DTO로 넣으면 된다.
        private T responseBody; <- 동적인 영역: GenericType으로 진행
    
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Header{
            private String responseCode;
        }
    }
    
## 서버에게 값 전달:RestTemplate => Req<T> T는 동적Type

### URI 정의 영역

    URI uri = UriComponentsBuilder
    .fromUriString("http://localhost:9090")
    .path("/api/server/user/{userId}/name/{userName}")
    .encode()
    .build()
    .expand("100", "park")
    .toUri();
                    
### 사용할 DTO 영역 객체 생성 및 설정

    UserRequest request = new UserRequest(); <= 동적영역 객체 생성
    request.setName("park");
    request.setAge(100);
            
            
### 동적 Request Template 생성 및 설정

    Req<UserRequest> req = new Req();
    Req.Header header = new Req.Header(); <= 정적영역 객체 생성
    header.setResponseCode("jonghoon");   <= 정적영역 객체 설정
    req.setHeader(
            header     <= 정적영역 세팅
    );

    req.setResponseBody(
        request         <= 동적영역 세팅
    );
    
### RequestEntity 동적 Request Template 타입으로 RequestEntity 객체 생성

    RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                    .post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("x-authorization", "abcd")
                    .header("custom-header", "fffff")
                    .body(req); <= 동적 Request Template 세팅   
                    
                    
### Server와 통신하는 RestTemplte 객체 생성

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Req<UserResponse>> response
            = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});
            
    exchange는 HTTP 메서드 제한되지 않고 사용가능하며 Header를 새로 만들 수 있다.
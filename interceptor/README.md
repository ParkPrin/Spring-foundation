# Filter와 Interceptor

## Interceptor
- Filter와 매우 유사함
- Spring Context에 등록되는 점이 Filter는 Web Application에 등록되는 차이점
- Spring Context에 등록되면 Spring의 기능을 활용할 수 있다.
- 주로 인증 단계를 처리, Logging를 하는 데 사용
- 순수 데이터를 출력할 때는 Filter에서 사용하는 것이 좋고, 인증을 할 때는 Interceptor에서 하는 것이 좋음

## Interceptor 처리


1) Annotation 만들기
2) Annotation을 메소드 위에 선언하기
3) HandlerInterceptor 인터페이스를 구현체로 하여 Interceptor를 만든다.
4) 생성한 Interceptor내에서 Annotation 체크로직을 만들어서 존재여부를 반환하는 코드를 넣는다.
5) Annotation이 존재시 인증처리를 넣는다.
6) 존재하지 않을 시 Exception 처리하는 부분을 넣는다.
7) Interceptor를 동작시키기 위해서 Spring config 클래스를 생성하고, 생성된 클래스에서 Interceptor를 등록한다.
<br> 7-1) Interceptor의 동작하는 범위를 결정할 수 있다. .addPathPatterns("/api/private/*") 을 넣는다.
8) Exception 관리시 @RestControllerAdvice가 선언된 클래스를 가지고 동작범위를 규정한다.
<br> 8-1) @RestControllerAdvice 생성전 동작할 Exception을 생성한다. 예) AuthException

### Annotation 
        @Retention(RetentionPolicy.RUNTIME) // 컴파일 이후에도 JVM에 의해서 참조가 가능합니다.
        @Retention(RetentionPolicy.CLASS) // 컴파일러가 클래스를 참조할 때까지 유효합니다.
        @Retention(RetentionPolicy.SOURCE) // 어노테이션 정보는 컴파일 이후 없어집니다.


        ElementType.PACKAGE, // 패키지 선언시
        ElementType.TYPE, // 타입 선언시
        ElementType.CONSTRUCTOR, // 생성자 선언시
        ElementType.FIELD, // 멤버 변수 선언시
        ElementType.METHOD, // 메소드 선언시
        ElementType.ANNOTATION_TYPE, // 어노테이션 타입 선언시
        ElementType.LOCAL_VARIABLE, // 지역 변수 선언시
        ElementType.PARAMETER, // 매개 변수 선언시
        ElementType.TYPE_PARAMETER, // 매개 변수 타입 선언시
        ElementType.TYPE_USE // 타입 사용시
        
        
- @Retention(RetentionPolicy.RUNTIME) : Runtime 시 사용할 수 있음 
- @Target ElementType.TYPE, // 타입 선언시, ElementType.METHOD, // 메소드 선언시

        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.TYPE, ElementType.METHOD})
        public @interface Auth {}


### HandlerInterceptor

Interceptor 클래스

    @Component <- Spring Container에 등록
    @Slf4j <- log4j 사용을 위해서 선언된 Annotation
    // HandlerInterceptor을 상속받아야 Interceptor로 사용할 수 있다.
    public class AuthIntercepteor implements HandlerInterceptor {
    
    
        // preHandle를 오버라이드 후 해당 내용에서 어떻게 Interceptor를 동작시킬지 구현을 넣을 수 있다.
        // 파라미터로 HttpServletRequest request, HttpServletResponse response, Object handler 로 제공이되며
        // 이 중 Object handler를 이용하여 Annotation을 선언된 부분을 캐치할 수 있다.
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String url = request.getRequestURI();
            URI uri =  UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString()).build().toUri();
            log.info("request url : {}", url);
    
            boolean hasAnnotation = checkAnnotation(handler, Auth.class);
            log.info("has annotation : {}", hasAnnotation);
    
            // 나의 서버는 모두 public으로 동작을 하는데
            // 단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키,
    
            if (hasAnnotation) {
                //권한체크
                String query = uri.getQuery();
                log.info("query: {}", query);
                if (query.equals("name=park")){
                    return true;
                }
    
                throw new AuthException();
            }
    
            return true;
        }
    
        // 명시 어노테이션을 존재 여부를 확인하는 메소드
        private boolean checkAnnotation(Object handler, Class clazz) {
    
            // resource javascrit, html, 정적자
            if (handler instanceof ResourceHttpRequestHandler) {
                return true;
            }
    
            // annotation check
            HandlerMethod handlerMethod = (HandlerMethod) handler;
    
            if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
                // Auth annotation이 있을 때는 true
                return true;
            }
    
            return false;
    
        }
    }
    
Config 클래스

    @Configuration <- Spring Copfig 설정 Annotation
    @RequiredArgsConstructor <- 생성자 자동 객체주입 Annotation
    // WebMvcConfigurer 구현해야 addInterceptors을 통해서 Interceptor를 등록할 수 있다.
    // 필요시 addPathPatterns를 이용하여 Interceptor의 적용범위를 설정할 수 있다.
    public class MvcConfig implements WebMvcConfigurer {
    
        private final AuthIntercepteor authIntercepteor;
    
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            //registry.addInterceptor(authIntercepteor).addPathPatterns("/api/private/*");
            registry.addInterceptor(authIntercepteor);
        }
    }

    
RestControllerAdvice 클래스

    @RestControllerAdvice <- Exception 관리하는 영역을 Annotation으로 선언함
    public class GlobalExceptionHandler {
    
        @ExceptionHandler(AuthException.class) <- 적용할 Exception 
        public ResponseEntity authException() {
            // 결과값을 명시할 수 있음
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("해당 계정은 권한이 없습니다.");
        }
    }
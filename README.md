# Spring-foundation
spring 기초과정


## Spring 핵심

### 스프링 프레임워크의구성은 20여가지로 구성
이러한 모듈들은 스프링의 핵심 기능(DI, AOP, etc)

### 여러 가지 모듈이 있지만, 그 중에서 단연
스프링 부트, 스프링 클라우드(마이크로서비스), 스프링 데이터, 스프링 배치(대용량 처리), 스프링 시큐리티(권한관련) 중점


### 스프링이 나온 배경
테스트가 굉장히 어려움, 느슨한 결합이 된 애플리케이션의 경우 테스트가 어려움

### IoC 등장
#### 제어의 역전
스프링의 경우 New를 이용한 객체 생성이 아닌 스프링 컨테이너가 객체생성을 관리한다.
싱글톤의 형태로 관리한다. 
개발자에서 프레임워크로 제어의 객체 관리의 권한이 넘어감

DI(Dependency Injection)
왜 사용한가?
의존성으로 부터 격리시켜 코드 테스트에 용의하다
DI를 통하여 불가능한 상황을 Mock와 같은 기술을 통하여, 안정적으로 테스트 가능함
코드를 확장하거나 변경 할 때 영향을 최소화한다.(추상화)
순환참조을 막을 수 있다.

### AOP(Aspect Oriented Programming)
관점지향 프로그램
스프링 어플리케이션은 대부분 특별한 경우를 제외 하고는 MVC 웹 어플리케이션에서는
Web Layer, Business Layer, Data Layer로 정의
- Web Layer: REST API를 제공하며, Client 중심의 로직 적용
- Business Layer: 내부 정책에 따른 logic를 개발하며, 주로 해당 부분을 개발
- Data Layer: 데이터 베이스 및 외부와의 연동을 처리

@Aspect: AOP를 정의하는 Class에 할당 <br>
@Pointcut: 기능을 어디에 적용시킬지 지점설정 <br>
@Before: 메소드 실행하기 이전 <br>
@After 메소드가 성공적으로 실행 후, 예외가 발생 되더라도 실행 <br>
@AfterReturing 메소드 호출 성공 실행 시 <br>
@AfterThrowing 메소드 호출 실패 예외 발생 <br>
@Afound Before / after 모두 제어 <br>

### PSA(이식가능한 추상화)


### Spring Boot Annotations
@SpringBootApplication: Spring boot application으로 설정 <br>
@Controller: View를 제공하는 controller로 설정 <br>
@RestController REST API를 제공하는 controller로 설정 <br>    
@RequestMapping: URL 주소를 맵핑 <br>
@GetMapping: Http GetMethod URL 주소 맵핑 <br>
@PostMapping: Http PostMethod URL 주소 맵핑 <br>
@PutMapping: Http PutMethod URL 주소 맵핑 <br>
@DeleteMapping: Http DeleteMethod URL 주소 맵핑 <br>
@RequestParam: URL Query Parameter 맵핑 <br>
@RequestBody: Http Body를 Parsing 맵핑 <br>
@Valid: POJO Java class의 검증 <br>
@Configration: 1개 이상의 bean을 등록 할 때 설정<br>
@Component: 1개의 Class 단위로 등록 할 때 사용<br>
@Bean: 1개의 외부 library로부터 생성한 객체를 등록 시 사용<br>
@Autowired: DI를 위한 곳에 사용<br>
@Qualifier: @Autowired 사용시 bean이 2개 이상 일때 명시적 사용<br>
@Resource: @Autowired + @Qualifier의 개념으로 이해<br>
@Aspect: AOP 적용시 사용<br>
@Before: AOP 메소드 이전 호출 지정<br>
@After: AOP 메소드 호출 이후 지정 예외 발생 포함<br>
@Around: AOP 이전/이후 모두 포함 예외 발생 포함<br>
@AfterReturning: AOP 메소드의 호출이 정상일 때 실행<br>
@AfterThrowing: AOP시 해당 메소드가 예외 발생시 지정<br>

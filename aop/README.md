# AOP 학습

## AOP 필요한 요소들
1) Pointcut: AOP 동작범위 지정
2) Before(선택): AOP Tarket method가 동작하기 전 실행
3) After(선택): AOP Tarket method가 동작후 실행
4) Anotation: Anotation type으로 만들고 AOP 클래스에서 Pointcut으로 Anotation의 경로를 입력해 주어야한다.<br>
해당 설정을 한 후에 생성한 Anotation을 AOP에 걸고 싶은 메소드 상단에 정의 하면 AOP target Method가 된다.
   
## 참조클래스
API: RestApiController
AOP: 
- 클래스 범위지정: ParameterAop
- 어노테이션 범위지정: TimerAop, DecodeAop
Annotation: Decode, Timer
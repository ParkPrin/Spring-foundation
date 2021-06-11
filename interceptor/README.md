# Filter와 Interceptor

## Interceptor
- Filter와 매우 유사함
- Spring Context에 등록되는 점이 Filter는 Web Application에 등록되는 차이점
- Spring Context에 등록되면 Spring의 기능을 활용할 수 있다.
- 주로 인증 단계를 처리, Logging를 하는 데 사용
- 순수 데이터를 출력할 때는 Filter에서 사용하는 것이 좋고, 인증을 할 때는 Interceptor에서 하는 것이 좋
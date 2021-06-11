# Filter와 Interceptor

## Filter
- Web Application에서 관리되는 영역으로써 최초/최종 단계의 위치에 존재
- 요청/응답의 정보를 변경하거나, Spring에 의해서 데이터 변환되기 전의 순수한 Client의 요청/응답 값을 확인
- 유일하게 ServletRequest, ServletResponse의 객체를 변환
- Logging용도로 활용
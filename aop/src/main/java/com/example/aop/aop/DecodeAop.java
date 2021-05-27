package com.example.aop.aop;

import com.example.aop.dto.User;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DecodeAop {

	/**
	 * Pointcut은 AOP가 실행될 범위를 지정할 수 있다.
	 * @Pointcut("@annotation(어노테이션경로)) 지정된 어노테이션이 사용될 때 AOP를 실행시킬 수 있다.
	 * Before(Pointcut 메소드) : AOP Tarket 메소드가 진행되기전 실행되며, Before 안에 실행시킬 시점을 가진 Pointcut을 지정할 수 있다.
	 * AfterReturning은 AOP Target 메소드가 return 하는 시점에서 동작한다. 실행시킬 시점을 가진 Pointcut을 지정할 수 있다.
	 */
	@Pointcut("execution(* com.example.aop.controller..*.*(..))")
	private void cut(){}

	@Pointcut("@annotation(com.example.aop.annotation.Decode)")
	private void enableDecode(){}

	@Before("cut() && enableDecode()")
	public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			if(arg instanceof User){
				User user = User.class.cast(arg);
				String base63Email = user.getEmail();
				String email = new String(Base64.getDecoder().decode(base63Email), "UTF-8");
				user.setEmail(email);

			}
		}
	}

	@AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
	public void afterReturn(JoinPoint joinPoint, Object returnObj) throws UnsupportedEncodingException {
		if(returnObj instanceof User){
			User user = User.class.cast(returnObj);
			String email1 = user.getEmail();
			String base64Email = Base64.getEncoder().encodeToString(email1.getBytes());
			user.setEmail(base64Email);

		}
	}
}

package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component // bean은 class에 등록할 수 없음, 메소드만 등록할 수 있음
public class TimerAop {

	@Pointcut("execution(* com.example.aop.controller..*.*(..))")
	private void cut(){}

	@Pointcut("@annotation(com.example.aop.annotation.Timer)")
	private void enableTimer(){}

	@Around("cut() && enableTimer()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = joinPoint.proceed();
		stopWatch.stop();;
		System.out.println("total time: " + stopWatch.getTotalTimeSeconds());
	}
}

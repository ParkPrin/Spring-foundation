package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    /**
     * Pointcut은 AOP가 실행될 범위를 지정할 수 있다.
     * Before은 AOP Target 메소드가 진행 전 시점에서 실행될 메소드를 지정할 수 있다.
     * AfterReturning은 AOP Target 메소드가 return 하는 시점에서 동작한다.
     */

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}



    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());



        Object[] args= joinPoint.getArgs();
        for (Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}

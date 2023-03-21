package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut(){

    }

    @Before(value = "pointcut()")
    public void before(){
        System.out.println("before");
    }

    @AfterReturning(value = "pointcut()")
    public void afterRetuning() {
        System. out .println( "afterRetuning");
    }

    @After(value = "pointcut()")
    public void after() {
        System. out .println( "after.........");
    }

    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        System. out .println( "afterThrowing");
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println(" around before");
        //执行被增强的方法
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("around after");
        return proceed;
    }



}

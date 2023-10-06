package com.example.travel.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.example.travel..*Controller.*(..))")
    private void controllers(){}

    @Pointcut("execution(* com.example.travel..*Service.*(..))")
    private void services(){}

    @Before("controllers()")
    public void beforeControllerLog(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        Object[] args = joinPoint.getArgs();

        log.info("[Request] Controller URL: {}, MethodName: {}, Arg: {}", request.getRequestURI(), methodName, args);
    }

    @Before("services()")
    public void beforeServiceLog(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        Object[] args = joinPoint.getArgs();

        log.info("[Request] Service MethodName: {}, Arg: {}", methodName, args);
    }

    @AfterReturning(pointcut = "services()", returning = "result")
    public void afterSuccessServiceLog(JoinPoint joinPoint, List result){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        Object[] args = joinPoint.getArgs();

        log.info("[Response] Service MethodName: {}, Arg: {}, Result: {}", methodName, args, result);
    }

    @AfterThrowing(pointcut = "services()", throwing = "ex")
    public void afterFailServiceLog(JoinPoint joinPoint, Throwable ex){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        Object[] args = joinPoint.getArgs();

        log.error("[Error] Service Url: {}, MethodName: {}, Arg: {}, ErrorMessage: {}", request.getRequestURI(), methodName, args, ex.getMessage());
    }
}

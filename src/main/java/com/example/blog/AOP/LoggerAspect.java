//package com.example.blog.AOP;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class LoggerAspect {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Around("com.example..")
//    public Object printAlllog(ProceedingJoinPoint joinPoint) throws Throwable {
//        String type = "[All] ---> ";
//        String name = joinPoint.getSignature().getDeclaringTypeName();
//        logger.info(type + name + "." + joinPoint.getSignature().getName() + "()");
//        return joinPoint.proceed();
//    }
//
//    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) or @annotation(org.springframework.web.bind.annotation.GetMapping) or @annotation(org.springframework.web.bind.annotation.GetMapping) or @annotation(org.springframework.web.bind.annotation.PutMapping)")
//    public Object printRequestlog(ProceedingJoinPoint joinPoint) throws Throwable {
//        String type = "[Request] ---> ";
//        String name = joinPoint.getSignature().getDeclaringTypeName();
//        logger.info(type + name + "." + joinPoint.getSignature().getName() + "()");
//        return joinPoint.proceed();
//    }
//
//    @Around("@execution(* com.example..Service.*Service.*(..)")
//    public Object printSerivcelog(ProceedingJoinPoint joinPoint) throws Throwable {
//        String type = "[Serivce] ---> ";
//        String name = joinPoint.getSignature().getDeclaringTypeName();
//        logger.info(type + name + "." + joinPoint.getSignature().getName() + "()");
//        return joinPoint.proceed();
//    }
//}

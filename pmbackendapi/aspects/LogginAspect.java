//package com.miu.rsmanagementbackendapi.aspects;
//
//import com.miu.rsmanagementbackendapi.domain.logging.LoggerEntity;
//import com.miu.rsmanagementbackendapi.service.logging.LoggerEntityService;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Date;
//
//@Aspect
//@Configuration
//@RequiredArgsConstructor
//public class LogginAspect {
//    private final String TAB = "\t";
//
//    private final HttpServletRequest request;
//    private final LoggerEntityService loggerEntityService;
//
//    @Pointcut("execution(* com.miu.rsmanagementbackendapi.*.*.*.*(..))")
//    public void loggerUserOperations() {
//    }
//
//    @Before("loggerUserOperations()")
//    public void loggingUserOperation(JoinPoint joinPoint) {
//        LoggerEntity logger = buildLoggerEntity(joinPoint);
//        loggerEntityService.saveLoggerEntity(logger);
//    }
//
//    private LoggerEntity buildLoggerEntity(JoinPoint joinPoint) {
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("Execution type: ").append(joinPoint.getKind()).append(TAB)
//                .append(" Operation performed in class: ").append(joinPoint.getSignature().getDeclaringType().getSimpleName()).append(TAB)
//                .append(" as: ").append(joinPoint.getSignature().getName()).append(TAB)
//                .append(" Method Type: ").append(request.getMethod()).append(TAB)
//                .append(" Request URI: ").append(request.getRequestURL());
//
//        LoggerEntity logger = new LoggerEntity.LoggerEntityBuilder()
//                .setDate(new Date())
//                .setTime(new Date())
//                .setPrinciple("USER")
//                .setOperation(builder.toString())
//                .build();
//
//        return logger;
//    }
//
//}

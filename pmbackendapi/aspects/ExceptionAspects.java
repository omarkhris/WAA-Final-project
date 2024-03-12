//package com.miu.rsmanagementbackendapi.aspects;
//
//import com.miu.rsmanagementbackendapi.domain.logging.ExceptionEntity;
//import com.miu.rsmanagementbackendapi.service.logging.ExceptionEntityService;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Date;
//
//@Aspect
//@RequiredArgsConstructor
//@Configuration
//public class ExceptionAspects {
//    private final String TAB = "\t";
//
//    private final ExceptionEntityService exceptionEntityService;
//
//    @Pointcut("execution(* com.miu.rsmanagementbackendapi.*.*.*.*(..))")
//    public void executionLogger() {
//    }
//
//    @AfterThrowing(value = "executionLogger()", throwing = "exception")
//    public void loggingProgramExecutions(JoinPoint joinPoint, Throwable exception) {
//        ExceptionEntity exceptionEntity = buildExceptionEntity(joinPoint, exception);
//        exceptionEntityService.saveExceptionEntity(exceptionEntity);
//    }
//
//
//    private ExceptionEntity buildExceptionEntity(JoinPoint joinPoint, Throwable exception) {
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("Execution type: ").append(joinPoint.getKind()).append(TAB)
//                .append(", Operation performed in class: ").append(joinPoint.getSignature().getDeclaringType().getSimpleName()).append(TAB)
//                .append(" as: ").append(joinPoint.getSignature().getName()).append(TAB);
////                .append(" Method Type: ").append(request.getMethod()).append(TAB)
////                .append(" Request URI: ").append(request.getRequestURL());
//
//        StringBuilder expType = new StringBuilder();
//        expType.append("Exception Occurred: ").append(exception.getClass().getSimpleName()).append(", With message: ").append(exception.getMessage());
//
//        ExceptionEntity exp = new ExceptionEntity.ExceptionEntityBuilder()
//                .setDate(new Date())
//                .setTime(new Date())
//                .setPrinciple("USER")
//                .setOperation(builder.toString())
//                .setExceptionType(expType.toString())
//                .build();
//
//        return exp;
//    }
//
//}

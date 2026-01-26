package by.epam.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
public class FirstAspect {

    /*
    execution (modifiers-pattern ? ret-type-pattern declaring-type-pattern ? name-pattern(param-pattern))
     */
    @Pointcut("execution(public * by.epam.*.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {

    }

    @Before(value = "anyFindByIdServiceMethod() " +
            "&& args(id)  " +
            "&& target(service)" +
            "&& this(serviceProxy)" +
            "&& @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional")
//    @Before("execution(public * by.epam.spring.service.*Service.findById(*))")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Invoked findById method in class {},with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() && target(service)", returning = "result", argNames = "result,service")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("after returning - invoked findById method in class {},result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() && target(service)", throwing = "exception")
    public void addLoggingAfterThrowing(Throwable exception, Object service) {
        log.info("after throwing - invoked findById method in class {},exception {}: {}", service, exception.getClass(), exception.getMessage());
    }

    @After(value = "anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("after finally - invoked findById method in class {}", service);
    }
}

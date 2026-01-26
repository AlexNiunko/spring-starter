package by.epam.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
public class SecondAspect {

    @Around(value = "by.epam.logging.aop.FirstAspect.anyFindByIdServiceMethod() && target(service) && args(id)", argNames = "joinPoint,service,id")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("AROUND Invoked findById method in class {},with id {}", service, id);
        try {
            var result = joinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {},result {}", service, result);
            return result;

        } catch (Throwable exception) {
            log.info("AROUND after throwing - invoked findById method in class {},exception {}: {}", service, exception.getClass(), exception.getMessage());
            throw exception;
        } finally {
            log.info("AROUND after finally - invoked findById method in class {}", service);
        }

    }

}

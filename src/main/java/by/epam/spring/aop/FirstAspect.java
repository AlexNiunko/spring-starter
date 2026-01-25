package by.epam.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class FirstAspect {

    /*
     @within - check annotation on the class target
     */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    /*
     within - check class type name
    */
    @Pointcut("within(by.epam.spring.service.*Service)")
    public void isServiceLayer() {

    }


    /*
    this-check AOP proxy class type
    target-check target object class type
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
//    @Pointcut("target(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {


    }

    /*
     check annotation on the methods
     */
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {

    }

    /*
    args - check method param type
    * - any param type
    .. - 0+ any params type
     */
    @Pointcut("isControllerLayer() && args( org.springframework.ui.Model,..)")
    public void hasModelParam() {

    }

    /*
    @args - check annotation on the param type
    * - any param type
    .. - 0+ any params type
     */
    @Pointcut("@args(by.epam.spring.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {

    }

    /*
    bean - check bean name
     */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {

    }

    /*
    execution (modifiers-pattern ? ret-type-pattern declaring-type-pattern ? name-pattern(param-pattern))
     */
    @Pointcut("execution(public * by.epam.spring.service.*Service.findById(*))")
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
        log.info("Invoked findById method in class {},with id {}",service,id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() && target(service)",returning = "result", argNames = "result,service")
    public void addLoggingAfterReturning(Object result,Object service){
        log.info("after returning - invoked findById method in class {},result {}",service,result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() && target(service)",throwing = "exception")
    public void addLoggingAfterThrowing(Throwable exception,Object service){
        log.info("after throwing - invoked findById method in class {},exception {}: {}",service,exception.getClass(),exception.getMessage());
    }

    @After(value = "anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service){
        log.info("after finally - invoked findById method in class {}",service);
    }

}

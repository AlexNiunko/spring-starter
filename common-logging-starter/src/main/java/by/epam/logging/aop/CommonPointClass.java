package by.epam.logging.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class CommonPointClass {

    /*
    @within - check annotation on the class target
    */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    /*
     within - check class type name
    */
    @Pointcut("within(by.epam.*.service.*Service)")
    public void isServiceLayer() {

    }

}

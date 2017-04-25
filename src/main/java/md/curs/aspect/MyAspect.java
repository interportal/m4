package md.curs.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by MG
 */
@Aspect
//@Component
public class MyAspect {

    // All methods from controller package
    @Pointcut("execution(* md.curs.controller.*.*(..))")
    public void allControllerMethods() {
    }

    @Before("allControllerMethods()")
    public void logMethod(JoinPoint point) {
        System.out.println("Calling method" + point.getSignature().toString());
    }


    @Around("allControllerMethods()")
    public Object profile(ProceedingJoinPoint point) throws Throwable {
        long start = System.nanoTime();

        Object response = point.proceed();

        long time = System.nanoTime() - start;
        System.out.println("Execution took " + time / 1000 + "milis");

        return response;
    }
}

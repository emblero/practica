package demo2.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @After("execution(* demo2.service.*.*(..))")
    public void logMessage(JoinPoint joinPoint){
        System.out.println("Метод: " + joinPoint.getSignature().getName() + " выполнил задачу");
    }

}

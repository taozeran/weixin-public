package com.example.demo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taozeran on 2020/3/24/20:33
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("execution(public * com.example.demo.api..*(..))")
    public Object controllerLog(ProceedingJoinPoint joinPoint){
        Object res = null;
        try {
            StringBuilder sb = new StringBuilder();
            for (Object o : joinPoint.getArgs()) {

                if (o instanceof String) {
                    sb.append(o);
                }else if (o instanceof RequestFacade || o instanceof ResponseFacade) {
                    // pass
                }else{
                    sb.append(o);
                }
                sb.append(" ");
            }
            log.info("api request: {}",sb.toString());
            res = joinPoint.proceed();
            log.info("api response: {}", res);
        } catch (Throwable t) {
            log.info("controller log exception",t);
        }
        return res;
    }
}

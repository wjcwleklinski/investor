package com.wjcwleklinski.investor.aspect;


import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;


@Aspect
@Configuration
public class RestLogger {

    @Autowired
    private Logger logger;

    @Pointcut("@annotation(requestMapping) && execution(* com.wjcwleklinski.investor.controller.ApiController.*(..))")
    public void endpointCallPointcut(RequestMapping requestMapping) {}

    @Pointcut("execution(* com.wjcwleklinski.investor.controller.*.*(..))")
    public void controllerPackagePointcut() {}

    @Before("endpointCallPointcut(requestMapping)")
    public void logPathAndMethod(RequestMapping requestMapping) {

        String method = requestMapping.method()[0].name();
        String url = requestMapping.path()[0];
        logger.info("Endpoint called");
        logger.info("Method: " + method);
        logger.info("Url: " + url);
    }

    @AfterReturning(value = "controllerPackagePointcut()", returning = "returnValue")
    public void logStatusCode(ResponseEntity returnValue) {
        logger.info("Status code: " + returnValue.getStatusCode());
    }

}

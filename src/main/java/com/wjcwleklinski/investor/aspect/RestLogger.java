package com.wjcwleklinski.investor.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Aspect
@Configuration
public class RestLogger {

    @Autowired
    private Logger logger;

    @Pointcut("@annotation(requestMapping) && execution(* com.wjcwleklinski.investor.controller.*.*(..))")
    public void endpointCallPointcut(RequestMapping requestMapping) {}

    @AfterReturning(value = "endpointCallPointcut(requestMapping)", returning = "returnValue")
    //@AfterThrowing(value = "endpointCallPointcut(requestMapping)")
    public void endpointLog(JoinPoint jp, RequestMapping requestMapping, ResponseEntity returnValue) {

        String method = requestMapping.method()[0].name();
        String url = requestMapping.path()[0];
        logger.info(method);
        logger.info(returnValue.getStatusCodeValue() + "");

    }

}

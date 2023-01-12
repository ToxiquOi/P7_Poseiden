package com.nnk.springboot.poseidon.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AspectLogger {

    private static final Logger log = LogManager.getLogger(AspectLogger.class);
    private final ObjectMapper mapper;

    @Autowired
    public AspectLogger(ObjectMapper mapper) {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        this.mapper = mapper;
    }

    @Pointcut("within(com.nnk.springboot.poseidon.controllers..*) " +
            "&& (@annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[] mappings = signature.getMethod().getAnnotations();

        Map<String, Object> parameters = getParameters(joinPoint);

        try {
            if(mappings[0] instanceof GetMapping) {
                log.info("==> path(s): {}, method(s): {}, arguments: {} ",
                        signature.toShortString(), "GET", mapper.writeValueAsString(parameters));
            } else if (mappings[0] instanceof PostMapping) {
                if(parameters instanceof HashMap<?, ?>)
                    log.info("==> path(s): {}, method(s): {}, arguments: {} ",
                        signature.toShortString(), "POST", mapper.writeValueAsString(parameters.values().toArray()[1]));
                else
                    log.info("==> path(s): {}, method(s): {}, arguments: {} ",
                            signature.toShortString(), "POST", mapper.writeValueAsString(parameters));
            } else if(mappings[0] instanceof DeleteMapping) {
                log.info("==> path(s): {}, method(s): {}, arguments: {} ",
                        signature.toShortString(), "DELETE", mapper.writeValueAsString(parameters));
            }
        } catch (JsonProcessingException e) {
            log.error("Error while converting", e);
        }
    }

    @AfterReturning(pointcut = "pointcut()", returning = "entity")
    public void logRestMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[] mappings = signature.getMethod().getAnnotations();

        try {
            logAfter(mappings[0], "<== path(s): {}, method(s): {}, retuning: {}", signature.toShortString(), mapper.writeValueAsString(entity));
        } catch (Exception e) {
            log.error("Error while converting", e);
        }
    }

    @AfterReturning(pointcut = "pointcut()", returning = "redirect")
    public void logControllerMethodAfter(JoinPoint joinPoint, String redirect) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[] mappings = signature.getMethod().getAnnotations();

        logAfter(mappings[0], "<== path(s): {}, method(s): {}, retuning: {}", signature.toShortString(), redirect);
    }

    private void logAfter(Object type, String msg, String methodInfo, String output) {
        if(type instanceof GetMapping) {
            log.info(msg, methodInfo, "GET", output);
        } else if (type instanceof PostMapping) {
            log.info(msg, methodInfo, "POST", output);
        } else if(type instanceof DeleteMapping) {
            log.info(msg, methodInfo, "DELETE", output);
        }
    }

    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        HashMap<String, Object> map = new HashMap<>();

        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }
}

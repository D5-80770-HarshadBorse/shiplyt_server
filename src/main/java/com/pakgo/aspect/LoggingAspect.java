package com.pakgo.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerMethods() {
	}

	@Before("controllerMethods()")
	public void logBefore(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		log.info("Entering {}.{} with args={}", signature.getDeclaringTypeName(), signature.getName(),
				Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "controllerMethods()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		log.info("Exiting {}.{} with result={}", signature.getDeclaringTypeName(), signature.getName(), result);
	}

	@AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
	public void logException(JoinPoint joinPoint, Exception ex) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		log.error("Exception in {}.{}: {}", signature.getDeclaringTypeName(), signature.getName(), ex.getMessage(), ex);
	}
}

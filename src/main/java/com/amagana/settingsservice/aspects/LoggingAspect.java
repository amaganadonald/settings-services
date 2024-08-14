package com.amagana.settingsservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.CategoryRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
	
	/* @Pointcut("execution(* com.amagana.settingsservice.controllers.*.*(..))") */
	@Pointcut("within(com.amagana.settingsservice.services.*)")
	public void loggingPointCut() {}
	
	@Before(value = "loggingPointCut()")
	public void loggingBefore(JoinPoint joinpoint) {
		log.info("Before excute method ::"+ joinpoint.getSignature());
	}
	
	@AfterReturning(value="loggingPointCut()", returning = "result")
	public void loggingAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("after returning for method "+result);
	}
	
	@AfterThrowing(value="loggingPointCut()", throwing = "exception")
	public void loggingAfterThrowing(JoinPoint joinpoint, Exception exception){
		log.error("after error from method::"+joinpoint.getSignature()+" with error " + exception.getMessage());
	}
	
	@Around(value = "loggingPointCut()")
	public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.proceed();
		if (object instanceof AddressRequestDTO) {
			log.info("Around proceed Address ::"+ joinPoint.getArgs());
		} else if (object instanceof CategoryRequestDTO) {
			log.info("Around proceed Category::"+joinPoint.getArgs()[0]);
		}
		return object;
	}

}

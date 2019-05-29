package org.zerock.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	@Before("execution(* org.zerock.service.SampleService*.doAdd(String,String)) && args(str1 , str2 )")
	public void logBeforeWithParam(String str1,String str2) {
		log.info("str1 : "+str1);
		log.info("str2 : "+str2);
	}
	
	@AfterThrowing(pointcut =  "execution(* org.zerock.service.SampleService*.*(..))", throwing="exc")
	public void logException(Exception exc) {
		
		log.info("Exception...!");
		log.info("exception:"+exc);
		
	}
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info("Target : "+pjp.getTarget());
		log.info("Param : "+pjp.getArgs());
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("time:"+(end-start));
		
		return result;
	}
	
}

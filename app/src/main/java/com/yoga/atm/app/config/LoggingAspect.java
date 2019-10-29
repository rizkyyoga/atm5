package com.yoga.atm.app.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(pointcut = "within(com.yoga.atm.app.service..*)", returning = "account")
	public void saveUserMethod(JoinPoint joinPoint, Object account) {
		String message = "";
		Object[] args = joinPoint.getArgs();
		String accountNumber = (String) args[0];
		String type = joinPoint.getSignature().getName();
		double amount = (double) args[1];
		message = "USER : " + accountNumber + " DOING " + type + " WITH AMOUNT " + amount;
		log.info(message);
		System.out.println(message);

	}

	@AfterThrowing(pointcut = "within(com.yoga.atm.app.service..*)", throwing = "exec")
	public void catchAllSQLSyntaxErrors(JoinPoint joinPoint, Throwable exec) {
		String message = "";
		message = exec.getMessage();
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), exec.getCause() != null ? exec.getCause() : "NULL");
		System.out.println(message);
	}
}

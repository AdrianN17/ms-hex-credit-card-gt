package com.bank.credit_card.infraestructure.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Aspect
@Component
public class TransactionalUseCaseAspect {

    private final PlatformTransactionManager transactionManager;

    public TransactionalUseCaseAspect(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * Matches all execute() methods inside any application service
     * (classes inside the application layer whose name ends with "Service").
     */
    @Pointcut("execution(* com.bank.credit_card.application..*Service.execute(..))")
    public void useCaseExecute() {}

    /**
     * Matches all methods annotated with @TransactionalUseCase at class or method level.
     */
    @Pointcut("@within(com.bank.credit_card.infraestructure.aop.annotation.TransactionalUseCase) || " +
              "@annotation(com.bank.credit_card.infraestructure.aop.annotation.TransactionalUseCase)")
    public void transactionalUseCaseAnnotated() {}

    /**
     * Intercepts both pointcuts: service.execute() and @TransactionalUseCase usages.
     */
    @Around("useCaseExecute() || transactionalUseCaseAnnotated()")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        String operation = joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName();

        log.info("[TRANSACTION] BEGIN  → {}", operation);

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object result = joinPoint.proceed();

            transactionManager.commit(status);
            log.info("[TRANSACTION] COMMIT → {}", operation);

            return result;

        } catch (Exception ex) {
            transactionManager.rollback(status);
            log.error("[TRANSACTION] ROLLBACK → {} | cause: {}", operation, ex.getMessage());
            throw ex;
        }
    }
}


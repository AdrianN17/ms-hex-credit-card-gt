package com.bank.credit_card.infraestructure.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a use-case service method (or class) so that the
 * {@link com.bank.credit_card.infraestructure.aop.aspect.TransactionalUseCaseAspect}
 * wraps its execution inside a database transaction.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalUseCase {
}


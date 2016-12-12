package com.weixk.helloworld.exception;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * Created by weixk on 16/12/9.
 */
public class TempValidationInterceptor extends MethodValidationInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TempValidationInterceptor.class);
    private final Validator validator;

    public TempValidationInterceptor() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        for (Object argument : arguments) {
            log.info("argument : {}", argument);
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(argument);
            Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
            ConstraintViolation<Object> constraintViolation = iterator.hasNext() ? iterator.next() : null;
            if (constraintViolation == null) continue;
            log.info("ConstraintViolation : {}", constraintViolation);
            throw new ConstraintViolationException(constraintViolation.getMessage(), null);
        }
        return invocation.proceed();
    }
}

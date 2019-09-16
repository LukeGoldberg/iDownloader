package org.lashly.mvc;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.lashly.domain.exceptions.BizException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Date  2019/8/28 21:54
 */
@Aspect
@Component
public class QueryValidationAspect {

    private final static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @Before("execution(public * org.lashly.controller..*.*(..))")
    public void validate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            Object arg = args[0];
            if (arg != null) {
                Set<ConstraintViolation<Object>> violations = VALIDATOR.validate(arg);
                violations.forEach(v -> {throw new BizException(v.getMessage());});
            }
        }
    }


}

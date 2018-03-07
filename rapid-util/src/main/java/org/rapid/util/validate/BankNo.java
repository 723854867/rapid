package org.rapid.util.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 银行卡号校验
 * 
 * @author lynn
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BankNoValidator.class)
@Documented
public @interface BankNo {

	String message() default "{validate.message.bankno}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}

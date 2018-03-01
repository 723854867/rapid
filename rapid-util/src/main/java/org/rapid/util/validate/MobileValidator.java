package org.rapid.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.PhoneUtil;

public class MobileValidator implements ConstraintValidator<Mobile, String> {
	
	private String countryCode;

	@Override
	public void initialize(Mobile constraintAnnotation) {
		this.countryCode = constraintAnnotation.countryCode();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (null == value)
			return true;
		try {
			return PhoneUtil.isMobile(value, countryCode);
		} catch (Exception e) {
			return false;
		}
	}
}

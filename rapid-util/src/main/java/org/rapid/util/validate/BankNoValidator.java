package org.rapid.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.BankUtil;

public class BankNoValidator implements ConstraintValidator<BankNo, String>  {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null == value ? true : BankUtil.checkBankCard(value);
	}
}

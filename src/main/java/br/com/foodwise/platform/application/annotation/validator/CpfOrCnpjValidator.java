package br.com.foodwise.platform.application.annotation.validator;

import br.com.foodwise.platform.application.annotation.CpfOrCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) return true;
        String digits = value.replaceAll("\\D", "");

        if (digits.matches("(.)\\1*")) {
            return false;

        } else if (digits.matches("\\d{11}")) {
            return Cpf.isValid(digits);

        } else if (digits.matches("\\d{14}")) {
            return Cnpj.isValid(digits);

        } else {
            return false;

        }
    }


}

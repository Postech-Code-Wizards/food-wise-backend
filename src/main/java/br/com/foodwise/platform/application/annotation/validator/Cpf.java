package br.com.foodwise.platform.application.annotation.validator;

public class Cpf {

    static boolean isValid(String digits) {
        if (Long.parseLong(digits) % 10 == 0) {
            return somaPonderada(digits) % 11 < 2;
        }
        else {
            return somaPonderada(digits) % 11 == 0;
        }
    }

    static int somaPonderada(String digits) {
        char[] cs = digits.toCharArray();
        int soma = 0;
        for (int i = 0; i < cs.length; i++) {
            soma += Character.digit(cs[i], 10) * (cs.length - i);
        }
        return soma;
    }
}

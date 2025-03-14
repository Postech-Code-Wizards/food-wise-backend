package br.com.foodwise.platform.application.annotation.validator;

public class Cnpj {

    private Cnpj() {
    }

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
            int index = cs.length - i - 1;
            soma += Character.digit(cs[i], 10) * ((index % 9 + 1) + (index / 9));
        }
        return soma;
    }
}

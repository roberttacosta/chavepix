package com.pix.utils;

import com.pix.config.exceptions.UnprocessableEntity;
import com.pix.model.enums.TipoChaveEnum;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isCpfValid(String CPF, TipoChaveEnum tipoChaveEnum) {

        CPF = CPF.trim();

        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
            throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
        } catch (InputMismatchException erro) {
            throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
        }
    }

    public static boolean isCNPJValid(String CNPJ, TipoChaveEnum tipoChaveEnum) {

        CNPJ = CNPJ.trim();

        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") || (CNPJ.length() != 14))
            throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return (true);
            else
                throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
        } catch (InputMismatchException erro) {
            throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
        }
    }

    public static boolean isValidEmail(String email, TipoChaveEnum tipoChaveEnum) {
        if (email != null && email.length() > 0) {
            String expression = "^[\\w-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches() && email.length() <= 77) {
                return true;
            }
        }
        throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
    }

    public static boolean isValidCelular(String celular, TipoChaveEnum tipoChaveEnum) {
        if (celular != null && celular.length() > 0) {
            String expression = "\\+\\d{13,14}";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(celular);
            if (matcher.matches()) {
                return true;
            }
        }
        throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
    }

    public static boolean isValidChaveAleatoria(String chave, TipoChaveEnum tipoChaveEnum) {
        if (chave.length() <= 36){
            return true;
        }
        throw new UnprocessableEntity("campo.chave.invalido", tipoChaveEnum.name());
    }

    public static boolean isValidConta(int conta){
        String contaString = String.valueOf(conta);

        if(contaString.length() <=8){
            return true;
        }

        throw new UnprocessableEntity("campo.conta.maximo.caractere");
    }

    public static boolean isValidAgencia(int agencia){
        String contaString = String.valueOf(agencia);

        if(contaString.length() <=4){
            return true;
        }

        throw new UnprocessableEntity("campo.agencia.maximo.caractere");
    }

    public static boolean isValidNome(String nome){
        if(nome.length() <=30){
            return true;
        }

        throw new UnprocessableEntity("campo.nome.maximo.caractere");
    }

    public static boolean isValidSobrenome(String sobrenome){
        if(sobrenome.length() <=45){
            return true;
        }

        throw new UnprocessableEntity("campo.sobrenome.maximo.caractere");
    }
}

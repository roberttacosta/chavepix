package com.pix.model.enums;

import com.pix.utils.Validator;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;

public enum TipoChaveEnum {
    CELULAR,
    EMAIL,
    CPF,
    CNPJ,
    ALEATORIO;
}

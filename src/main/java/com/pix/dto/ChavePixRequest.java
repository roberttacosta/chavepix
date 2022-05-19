package com.pix.dto;

import com.pix.model.ChavePix;
import com.pix.model.enums.TipoChaveEnum;
import com.pix.model.enums.TipoContaEnum;
import com.pix.model.enums.TipoPessoaEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChavePixRequest {
    @NotNull(message = "{required.validation}")
    private TipoChaveEnum tipoChave;

    @NotEmpty(message = "{required.validation}")
    private String valorChave;

    @NotNull(message = "{required.validation}")
    private TipoContaEnum tipoConta;

    @NotNull(message = "{required.validation}")
    private Integer agencia;

    @NotNull(message = "{required.validation}")
    private Integer conta;

    @NotEmpty(message = "{required.validation}")
    private String nomeCorrentista;

    private String sobrenomeCorrentista;

    @NotNull(message = "{required.validation}")
    private TipoPessoaEnum tipoPessoa;
}

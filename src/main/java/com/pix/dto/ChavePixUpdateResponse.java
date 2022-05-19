package com.pix.dto;

import com.pix.model.enums.TipoContaEnum;
import com.pix.model.enums.TipoPessoaEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ChavePixUpdateResponse {

    private UUID id;

    private TipoContaEnum tipoConta;

    private Integer agencia;

    private Integer conta;

    private String nomeCorrentista;

    private String sobrenomeCorrentista;

    private TipoPessoaEnum tipoPessoa;
}

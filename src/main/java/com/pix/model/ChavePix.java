package com.pix.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

@Entity
public class ChavePix implements Serializable {

    private static final long serialVersionUID = 1698160421220413812L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String tipoChave;

    @Column(unique = true)
    private String valorChave;

    private String tipoConta;

    private Integer agencia;

    private Integer conta;

    private String nomeCorrentista;

    private String sobrenomeCorrentista;

    private String tipoPessoa;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraInclusao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraInativacao;
}

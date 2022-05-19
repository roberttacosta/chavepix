package com.pix.model;

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
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(length = 9)
    private String tipoChave;

    @Column(unique = true)
    private String valorChave;

    @Column(length = 10)
    private String tipoConta;

    @Column(length = 4)
    private Integer agencia;

    @Column(length = 8)
    private Integer conta;

    @Column(length = 30)
    private String nomeCorrentista;

    @Column(length = 45)
    private String sobrenomeCorrentista;

    @Column(length = 1)
    private String tipoPessoa;

    private LocalDateTime dataHoraInclusao;

    private LocalDateTime dataHoraInativacao;
}

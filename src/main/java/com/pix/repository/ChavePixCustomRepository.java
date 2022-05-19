package com.pix.repository;

import com.pix.config.exceptions.NotFound;
import com.pix.config.exceptions.UnprocessableEntity;
import com.pix.model.ChavePix;
import com.pix.model.enums.TipoChaveEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class ChavePixCustomRepository {

    private final EntityManager entityManager;

    public ChavePixCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ChavePix> find(UUID id, String tipoChave, String agencia, String conta, String nomeCorrentista) {

        if (id != null && tipoChave != null ||
                id != null && agencia != null ||
                id != null && conta != null ||
                id != null && nomeCorrentista != null) {
            throw new UnprocessableEntity("combinacao.invalida");
        }

        String query = "select C from ChavePix as C ";
        String condicao = "where ";

        if (id != null) {
            query += condicao + "C.id = :id";
        }

        if (tipoChave != null) {
            try {
                TipoChaveEnum.valueOf(tipoChave);
            } catch (java.lang.IllegalArgumentException ex) {
                throw new UnprocessableEntity("enum.invalido");
            }
            query += condicao + "C.tipoChave = :tipoChave";
            condicao = " and ";
        }

        if (agencia != null && conta != null) {
            query += condicao + "C.agencia = :agencia ";
            query += "and C.conta = :conta";
            condicao = " and ";
        }

        if (nomeCorrentista != null) {
            query += condicao + "C.nomeCorrentista = :nomeCorrentista";
        }

        var q = entityManager.createQuery(query, ChavePix.class);

        if (id != null) {
            q.setParameter("id", id);
        }

        if (tipoChave != null) {
            try {
                TipoChaveEnum.valueOf(tipoChave);
            } catch (java.lang.IllegalArgumentException ex) {
                throw new UnprocessableEntity("enum.invalido");
            }

            q.setParameter("tipoChave", tipoChave);
        }

        if (agencia != null && conta != null) {
            q.setParameter("agencia", agencia);
            q.setParameter("conta", conta);
        }

        if (nomeCorrentista != null) {
            q.setParameter("nomeCorrentista", nomeCorrentista);
        }

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        }

        throw new NotFound("not.found");
    }

}

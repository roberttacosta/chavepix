package com.pix.repository

import com.pix.config.exceptions.NotFound
import com.pix.config.exceptions.UnprocessableEntity
import com.pix.model.ChavePix
import com.pix.model.enums.TipoChaveEnum
import com.pix.model.enums.TipoContaEnum
import com.pix.model.enums.TipoPessoaEnum
import spock.lang.Specification

import javax.persistence.EntityManager
import java.time.LocalDateTime

class ChavePixCustomRepositoryTest extends Specification{

    ChavePixCustomRepository repository

    EntityManager entityManager

    ChavePix chavePix

    def setup(){
        entityManager = Mock(EntityManager)

        repository = new ChavePixCustomRepository(entityManager)

        chavePix = new ChavePix(id: UUID.fromString("975a4fd0-f121-40d3-9262-fd021e00fa74"), tipoChave: TipoChaveEnum.CPF, valorChave: "12659459690",
                tipoConta: TipoContaEnum.CORRENTE, agencia: 1020, conta: 3456789, nomeCorrentista: "Teste",
                sobrenomeCorrentista: "Silva", tipoPessoa: TipoPessoaEnum.F, dataHoraInclusao: LocalDateTime.now(),
                dataHoraInativacao: null)
    }

    def "find Not Found"(){
        given:
        UUID id = UUID.fromString("975a4fd0-f121-40d3-9262-fd021e00fa74")

        1*entityManager.createQuery(_, ChavePix.class) >> _

        when:
        repository.find(id, null, null, null, null)

        then:
        thrown(NotFound)
    }

    def "find Unprocessable Entity"(){
        given:
        UUID id = UUID.fromString("975a4fd0-f121-40d3-9262-fd021e00fa74")

        when:
        repository.find(id, TipoChaveEnum.ALEATORIO.toString(), null, null, null)

        then:
        thrown(UnprocessableEntity)
    }
}

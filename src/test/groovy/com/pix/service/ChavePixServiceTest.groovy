package com.pix.service

import com.pix.config.exceptions.NotFound
import com.pix.config.exceptions.UnprocessableEntity
import com.pix.dto.ChavePixRequest
import com.pix.dto.ChavePixUpdateRequest
import com.pix.model.ChavePix
import com.pix.model.enums.TipoChaveEnum
import com.pix.model.enums.TipoContaEnum
import com.pix.model.enums.TipoPessoaEnum
import com.pix.repository.ChavePixRepository
import spock.lang.Specification

import java.time.LocalDateTime

class ChavePixServiceTest extends Specification {

    ChavePixService service

    ChavePixRepository repository

    ChavePixRequest request

    ChavePixUpdateRequest updateRequest

    ChavePix chavePix

    def setup() {

        repository = Mock(ChavePixRepository)

        service = new ChavePixService()
        service.chavePixRepository = repository

        request = new ChavePixRequest(tipoChave: TipoChaveEnum.CPF, valorChave: "12659459690", tipoConta: TipoContaEnum.CORRENTE,
                agencia: 1020, conta: 3456789, nomeCorrentista: "Teste", sobrenomeCorrentista: "Silva", tipoPessoa: TipoPessoaEnum.F)

        updateRequest = new ChavePixUpdateRequest(id: UUID.fromString("975a4fd0-f121-40d3-9262-fd021e00fa74"), conta: 376589)

        chavePix = new ChavePix(id: UUID.fromString("975a4fd0-f121-40d3-9262-fd021e00fa74"), tipoChave: TipoChaveEnum.CPF, valorChave: "12659459690",
                tipoConta: TipoContaEnum.CORRENTE, agencia: 1020, conta: 3456789, nomeCorrentista: "Teste",
                sobrenomeCorrentista: "Silva", tipoPessoa: TipoPessoaEnum.F, dataHoraInclusao: LocalDateTime.now(),
                dataHoraInativacao: null)
    }

    def "save"(){
        given:

        List<ChavePix> chavePixList = new ArrayList<ChavePix>()

        1*repository.findAllByConta(_) >> chavePixList
        1*repository.save(_) >> chavePix

        when:
        var result = service.save(request)

        then:
        result.getId() == chavePix.getId().toString()
    }

    def "save with max chave permitido"(){
        given:

        List<ChavePix> chavePixList = new ArrayList<ChavePix>()
        chavePixList.add(chavePix)
        chavePixList.add(chavePix)
        chavePixList.add(chavePix)
        chavePixList.add(chavePix)
        chavePixList.add(chavePix)

        1*repository.findAllByConta(_) >> chavePixList

        when:
        service.save(request)

        then:
        thrown(UnprocessableEntity)
    }

    def "save with valorChave invalid"(){
        given:

        List<ChavePix> chavePixList = new ArrayList<ChavePix>()
        chavePixList.add(chavePix)

        1*repository.findAllByConta(_) >> chavePixList

        request.setValorChave("1245")

        when:
        service.save(request)

        then:
        thrown(UnprocessableEntity)
    }

    def "save with conta invalid"(){
        given:

        List<ChavePix> chavePixList = new ArrayList<ChavePix>()
        chavePixList.add(chavePix)

        1*repository.findAllByConta(_) >> chavePixList

        request.setConta(1245090909)

        when:
        service.save(request)

        then:
        thrown(UnprocessableEntity)
    }

    def "update"(){
        given:

        1*repository.findById(_) >> Optional.of(chavePix)

        when:
        service.updateById(updateRequest)

        then:
        1*repository.save(_) >>{
            ChavePix pix = it.get(0)
            assert pix.dataHoraInclusao != null
        }
    }

    def "update chavePix not registered"(){
        given:

        1*repository.findById(_) >> Optional.empty()

        when:
        service.updateById(updateRequest)

        then:
        thrown(NotFound)
    }
}

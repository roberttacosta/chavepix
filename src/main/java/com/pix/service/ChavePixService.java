package com.pix.service;

import com.pix.config.exceptions.UnprocessableEntity;
import com.pix.dto.ChavePixRequest;
import com.pix.dto.ChavePixResponse;
import com.pix.model.ChavePix;
import com.pix.model.enums.TipoPessoaEnum;
import com.pix.repository.ChavePixRepository;
import com.pix.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ChavePixService {

    @Autowired
    private ChavePixRepository chavePixRepository;

    public ChavePixResponse save(ChavePixRequest request) {

        if (canRegister(request) && Validator.isValidAgencia(request.getAgencia()) && Validator.isValidConta(request.getConta())
                && chaveIsValid(request))
        {
            ChavePix chavePixPersist = new ChavePix();
            chavePixPersist.setTipoChave(request.getTipoChave().toString());
            chavePixPersist.setValorChave(request.getValorChave());
            chavePixPersist.setAgencia(request.getAgencia());
            chavePixPersist.setConta(request.getConta());
            chavePixPersist.setDataHoraInclusao(LocalDateTime.now());
            chavePixPersist.setNomeCorrentista(request.getNomeCorrentista());
            chavePixPersist.setSobrenomeCorrentista(request.getSobrenomeCorrentista());
            chavePixPersist.setTipoConta(request.getTipoConta().toString());
            chavePixPersist.setTipoPessoa(request.getTipoPessoa().toString());

            chavePixPersist = chavePixRepository.save(chavePixPersist);

            ChavePixResponse chavePixResponse = new ChavePixResponse();
            chavePixResponse.setId(chavePixPersist.getId().toString());

            return chavePixResponse;
        }
        else throw new UnprocessableEntity("faf","any msg");
    }

    private boolean canRegister(ChavePixRequest request) {
        int registers = chavePixRepository.findAllByConta(request.getConta()).size();

        if (request.getTipoPessoa() == TipoPessoaEnum.F && registers < 5) {
            return true;
        }
        if(request.getTipoPessoa() == TipoPessoaEnum.J && registers < 20) {
            return true;
        }
        throw new UnprocessableEntity("maximo.chave.cadastrada", String.valueOf(request.getConta()));
    }

    private boolean chaveIsValid(ChavePixRequest request) {
        switch (request.getTipoChave()) {
            case CPF:
                return Validator.isCpfValid(request.getValorChave(), request.getTipoChave());
            case CNPJ:
                return Validator.isCNPJValid(request.getValorChave(), request.getTipoChave());
            case ALEATORIO:
                return Validator.isValidChaveAleatoria(request.getValorChave(), request.getTipoChave());
            case CELULAR:
                return Validator.isValidCelular(request.getValorChave(), request.getTipoChave());
            case EMAIL:
                return Validator.isValidEmail(request.getValorChave(), request.getTipoChave());
            default:
                throw new UnprocessableEntity("sdasd","any msg");
        }
    }
}

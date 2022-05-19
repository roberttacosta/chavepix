package com.pix.service;

import com.pix.config.exceptions.NotFound;
import com.pix.config.exceptions.UnprocessableEntity;
import com.pix.dto.ChavePixRequest;
import com.pix.dto.ChavePixResponse;
import com.pix.dto.ChavePixUpdateRequest;
import com.pix.dto.ChavePixUpdateResponse;
import com.pix.model.ChavePix;
import com.pix.model.enums.TipoContaEnum;
import com.pix.model.enums.TipoPessoaEnum;
import com.pix.repository.ChavePixRepository;
import com.pix.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


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
            chavePixPersist.setSobrenomeCorrentista(request.getSobrenomeCorrentista() == null ? ""  :
                    request.getSobrenomeCorrentista());
            chavePixPersist.setTipoConta(request.getTipoConta().toString());
            chavePixPersist.setTipoPessoa(request.getTipoPessoa().toString());

            chavePixPersist = chavePixRepository.save(chavePixPersist);

            ChavePixResponse chavePixResponse = new ChavePixResponse();
            chavePixResponse.setId(chavePixPersist.getId().toString());

            return chavePixResponse;
        }
        else throw new UnprocessableEntity("erro.gerenerico");
    }

    public ChavePixUpdateResponse updateById(ChavePixUpdateRequest request){
        Optional<ChavePix> chavePix = chavePixRepository.findById(request.getId());
        if(chavePix.isPresent() && chavePix.get().getDataHoraInativacao() == null){
            ChavePix chavePixUpdate = chavePix.get();
            if (request.getTipoConta() != null)
            {
                chavePixUpdate.setTipoConta(request.getTipoConta().toString());
            }
            if (request.getAgencia() != null && Validator.isValidAgencia(request.getAgencia()))
            {
                chavePixUpdate.setAgencia(request.getAgencia());
            }
            if (request.getConta() != null && Validator.isValidConta(request.getConta()))
            {
                chavePixUpdate.setConta(request.getConta());
            }
            if (request.getTipoPessoa() != null)
            {
                chavePixUpdate.setTipoPessoa(request.getTipoPessoa().toString());
            }
            if (request.getNomeCorrentista() != null && !request.getNomeCorrentista().isEmpty()
                    && Validator.isValidNome(request.getNomeCorrentista()))
            {
                chavePixUpdate.setNomeCorrentista(request.getNomeCorrentista());
            }
            if (request.getSobrenomeCorrentista() != null && !request.getSobrenomeCorrentista().isEmpty()
                    && Validator.isValidSobrenome(request.getSobrenomeCorrentista()))
            {
                chavePixUpdate.setSobrenomeCorrentista(request.getSobrenomeCorrentista());
            }

            try {
                chavePixRepository.save(chavePixUpdate);
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }

            ChavePixUpdateResponse response = new ChavePixUpdateResponse();
            response.setId(chavePixUpdate.getId());
            response.setAgencia(chavePixUpdate.getAgencia());
            response.setConta(chavePixUpdate.getConta());
            response.setNomeCorrentista(chavePixUpdate.getNomeCorrentista());
            response.setSobrenomeCorrentista(chavePixUpdate.getSobrenomeCorrentista());
            response.setTipoConta(TipoContaEnum.valueOf(chavePixUpdate.getTipoConta()));
            response.setTipoPessoa(TipoPessoaEnum.valueOf(chavePixUpdate.getTipoPessoa()));

            return response;
        }
        throw new NotFound("chave.pix.nao.cadastrada", request.getId().toString());
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
                throw new UnprocessableEntity("enum.invalido");
        }
    }
}

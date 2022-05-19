package com.pix.controller;

import com.pix.dto.ChavePixRequest;
import com.pix.dto.ChavePixResponse;
import com.pix.dto.ChavePixUpdateRequest;
import com.pix.dto.ChavePixUpdateResponse;
import com.pix.model.ChavePix;
import com.pix.repository.ChavePixCustomRepository;
import com.pix.service.ChavePixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "v1/chavepix")
public class ChavePixController {

    @Autowired
    private ChavePixService service;

    @Autowired
    private ChavePixCustomRepository chavePixCustomRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ChavePixResponse> insert (@Valid @RequestBody ChavePixRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ChavePixUpdateResponse> update (@Valid @RequestBody ChavePixUpdateRequest request){
        return ResponseEntity.ok(service.updateById(request));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ChavePix>> findCustom(
            @RequestParam(value = "id", required = false) UUID id,
            @RequestParam(value = "tipoChave", required = false) String tipoChaveEnum,
            @RequestParam(value = "agencia", required = false) Integer agencia,
            @RequestParam(value = "conta", required = false) Integer conta,
            @RequestParam(value = "nomeCorrentista", required = false) String nomeCorrentista
    ){
        return ResponseEntity.ok(chavePixCustomRepository.find(id, tipoChaveEnum, agencia, conta,
                nomeCorrentista));
    }
}

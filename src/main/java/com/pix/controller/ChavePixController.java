package com.pix.controller;

import com.pix.dto.ChavePixRequest;
import com.pix.dto.ChavePixResponse;
import com.pix.model.ChavePix;
import com.pix.service.ChavePixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "v1/chavepix")
public class ChavePixController {

    @Autowired
    private ChavePixService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ChavePixResponse> insert (@Valid @RequestBody ChavePixRequest request){
        return ResponseEntity.ok(service.save(request));
    }
}

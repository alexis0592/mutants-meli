package com.meli.challenge.mutantchallenge.controller;

import com.meli.challenge.mutantchallenge.model.DNA;
import com.meli.challenge.mutantchallenge.service.IDNAService;
import com.meli.challenge.mutantchallenge.utils.exceptions.InvalidDnaSequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/mutant")
public class DNAController {
    @Autowired
    private IDNAService dnaService;

    @PostMapping
    public ResponseEntity<Object> isMutant(@Valid @RequestBody DNA dna)  {

        try{
            if(dnaService.isMutant(dna.getDna())){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

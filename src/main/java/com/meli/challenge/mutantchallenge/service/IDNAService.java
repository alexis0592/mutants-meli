package com.meli.challenge.mutantchallenge.service;

import com.meli.challenge.mutantchallenge.service.model.dto.MutantStatDTO;
import com.meli.challenge.mutantchallenge.utils.exceptions.InvalidDnaSequenceException;

public interface IDNAService {
    boolean isMutant(String[] dna) throws InvalidDnaSequenceException;
    public MutantStatDTO getStats();
}

package com.meli.challenge.mutantchallenge.repository;

import com.meli.challenge.mutantchallenge.model.Mutant;

import java.util.List;

public interface IMutantRepository {

    Mutant save(Mutant mutant);
    List<Mutant> getAll();

}

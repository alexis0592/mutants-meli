package com.meli.challenge.mutantchallenge.service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MutantStat {

    @JsonProperty("count_mutant_dna")
    private int countMutantDna;

    @JsonProperty("count_human_dna")
    private int countHumanDna;

    private long ratio;

}

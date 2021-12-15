package com.meli.challenge.mutantchallenge.service.processor;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HorizontalSequenceProcess implements MutantProcess {

    @Override
    public int processDnaSequence(String[] dna, String sequence) {
        return (int) Arrays.stream(dna)
                .filter(seq -> seq.contains(sequence))
                .count();
    }

}

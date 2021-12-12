package com.meli.challenge.mutantchallenge.service.processor;

import org.springframework.stereotype.Component;

@Component
public class DiagonalSequenceProcess implements MutantProcess {

    @Override
    public int processDnaSequence(String[] dna, String sequence) {

        int occurrencesDiagonalPpal = processPrincipalDiagonal(dna, sequence);
        int occurrencesSecondaryPpal = processSecondaryDiagonal(dna, sequence);

        return occurrencesDiagonalPpal + occurrencesSecondaryPpal;
    }

    private int processPrincipalDiagonal(String[] dna, String sequence) {
        int occurrences = 0;
        StringBuilder dnaToAnalyze = new StringBuilder();
        for (int i = 0; i < dna.length; i++) {
            dnaToAnalyze.append(dna[i].charAt(i));
        }
        if(dnaToAnalyze.indexOf(sequence) > -1){
            occurrences++;
        }
        return occurrences;
    }

    private int processSecondaryDiagonal(String[] dna, String sequence) {
        int occurrences = 0;
        StringBuilder dnaToAnalyze = new StringBuilder();
        for (int i = dna.length; i > 0; i--) {
            dnaToAnalyze.append(dna[dna.length - i].charAt(i - 1));
        }
        if(dnaToAnalyze.indexOf(sequence) > -1){
            occurrences++;
        }
        return occurrences;
    }
}

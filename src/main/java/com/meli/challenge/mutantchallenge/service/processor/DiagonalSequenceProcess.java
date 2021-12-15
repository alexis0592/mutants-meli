package com.meli.challenge.mutantchallenge.service.processor;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DiagonalSequenceProcess implements MutantProcess {

    @Override
    public int processDnaSequence(String[] dna, String sequence) {

        int occurrencesDiagonalPpal = processPrincipalDiagonal(dna, sequence);
        int occurrencesSecondaryPpal = processSecondaryDiagonal(dna, sequence);
        int ocurrencesLowerDiagonal = processBottomDiagonal(dna, sequence);
        int occurrencesUpperDiagonal = processUpperDiagonal(dna, sequence);

        return occurrencesDiagonalPpal + occurrencesSecondaryPpal +
                ocurrencesLowerDiagonal + occurrencesUpperDiagonal;
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

    private int processBottomDiagonal(String[] dna, String sequence){
        int lowerDiagonalIterations = dna.length - sequence.length();
        int occurrences = 0;
        for(int i = 1; i < lowerDiagonalIterations + 1; i++){
            StringBuilder dnaToAnalyze = new StringBuilder();
            for(int j = i; j < dna.length; j++){
                dnaToAnalyze.append(dna[j].charAt(j - i));
            }
            if(dnaToAnalyze.indexOf(sequence) > -1 ){
                occurrences++;
            }
            if(occurrences == 2){
                return occurrences;
            }
        }
        return occurrences;
    }

    private int processUpperDiagonal(String[] dna, String sequence){
        int lowerDiagonalIterations = dna.length - sequence.length();
        int occurrences = 0;
        for(int i = 1; i < lowerDiagonalIterations+1; i++){
            StringBuilder dnaToAnalyze = new StringBuilder();
            for(int j = 0; j < dna.length - i; j++){
                dnaToAnalyze.append(dna[j].charAt(j + i));
            }
            if(dnaToAnalyze.indexOf(sequence) > -1 ){
                occurrences++;
            }
            if(occurrences == 2){
                return occurrences;
            }
        }
        return occurrences;
    }
}

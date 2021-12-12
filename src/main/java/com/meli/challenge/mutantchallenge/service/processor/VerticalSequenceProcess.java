package com.meli.challenge.mutantchallenge.service.processor;

import org.springframework.stereotype.Component;

@Component
public class VerticalSequenceProcess implements MutantProcess {

    @Override
    public int processDnaSequence(String[] dna, String sequence) {
        int result = 0;

        for(int i = 0; i < dna.length; i++){
            StringBuilder dnaToAnalyze = new StringBuilder();
            for (String s : dna) {
                dnaToAnalyze.append(s.charAt(i));
            }
            if(dnaToAnalyze.indexOf(sequence) > -1){
                result++;
            }
        }
        return result;
    }

}

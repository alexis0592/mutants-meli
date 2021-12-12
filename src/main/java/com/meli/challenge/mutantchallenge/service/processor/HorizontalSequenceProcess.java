package com.meli.challenge.mutantchallenge.service.processor;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class HorizontalSequenceProcess implements MutantProcess {

    @Override
    public int processDnaSequence(String[] dna, String sequence) {
        return (int) Arrays.stream(dna)
                .filter(seq -> seq.contains(sequence))
                .count();
    }

//    private boolean processHorizontalCharSequence(String charSequence){
//        return countConsecutiveCharOccurrence(charSequence.toCharArray());
//    }
//
//    private boolean countConsecutiveCharOccurrence(char[] nitrogenBaseChars) {
//        int count;
//        for(int i = 0; i < nitrogenBaseChars.length; i++){
//            char aux = nitrogenBaseChars[i];
//            count = 1;
//            for (int j = i + 1; j < nitrogenBaseChars.length; j++){
//                if(aux == nitrogenBaseChars[j]){
//                    count++;
//                }else {
//                    break;
//                }
//            }
//            if(count >= 4) return true;
//        }
//        return false;
//    }
}

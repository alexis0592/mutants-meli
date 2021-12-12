package com.meli.challenge.mutantchallenge.service;

import com.meli.challenge.mutantchallenge.service.processor.DiagonalSequenceProcess;
import com.meli.challenge.mutantchallenge.service.processor.HorizontalSequenceProcess;
import com.meli.challenge.mutantchallenge.service.processor.MutantProcess;
import com.meli.challenge.mutantchallenge.service.processor.VerticalSequenceProcess;
import com.meli.challenge.mutantchallenge.utils.Constants;
import com.meli.challenge.mutantchallenge.utils.Utils;
import com.meli.challenge.mutantchallenge.utils.exceptions.InvalidDnaSequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DNAService implements IDNAService{

    @Autowired
    private HorizontalSequenceProcess horizontalProcess;

    @Autowired
    private VerticalSequenceProcess verticalProcess;

    @Autowired
    private DiagonalSequenceProcess diagonalProcess;

    private final String[] allowedSequences = {"AAAA", "TTTT", "CCCC", "GGGG"};

    public boolean isMutant(String[] dna) throws InvalidDnaSequenceException {

        if(isValidSequence(dna)){
            throw new InvalidDnaSequenceException();
        }

        if(dna.length <= 3){
            return false;
        }

        int occurrences = 0;
        for(String sequence: allowedSequences){
            occurrences += horizontalProcess.processDnaSequence(dna, sequence);
            occurrences += verticalProcess.processDnaSequence(dna, sequence);
            occurrences += diagonalProcess.processDnaSequence(dna, sequence);
        }

        return occurrences > Constants.MINIMUN_SEQUENCE_OCCURRENCE;
    }

    private boolean isValidSequence(String[] dna){
        return Arrays.stream(dna)
                .anyMatch(seq -> Utils.validateRegExp(Constants.SEQUENCE_VALID, seq));

    }

//    public boolean findHorizontalSequence(String sequence){
//        return countConsecutiveCharOccurrence(sequence.toCharArray());
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

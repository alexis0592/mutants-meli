package com.meli.challenge.mutantchallenge.service;

import com.meli.challenge.mutantchallenge.model.Mutant;
import com.meli.challenge.mutantchallenge.repository.IMutantRepository;
import com.meli.challenge.mutantchallenge.service.model.dto.MutantStatDTO;
import com.meli.challenge.mutantchallenge.service.processor.DiagonalSequenceProcess;
import com.meli.challenge.mutantchallenge.service.processor.HorizontalSequenceProcess;
import com.meli.challenge.mutantchallenge.service.processor.VerticalSequenceProcess;
import com.meli.challenge.mutantchallenge.utils.Constants;
import com.meli.challenge.mutantchallenge.utils.Utils;
import com.meli.challenge.mutantchallenge.utils.exceptions.InvalidDnaSequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DNAService implements IDNAService{

    @Autowired
    private HorizontalSequenceProcess horizontalProcess;

    @Autowired
    private VerticalSequenceProcess verticalProcess;

    @Autowired
    private DiagonalSequenceProcess diagonalProcess;

    @Autowired
    private IMutantRepository mutantRepository;

    public boolean isMutant(String[] dna) throws InvalidDnaSequenceException {

        if(isValidSequence(dna)){
            throw new InvalidDnaSequenceException();
        }

        if(dna.length <= 3){
            saveMutant(dna, false);
            return false;
        }

        boolean isMutant;
        int occurrences = 0;
        for(String sequence: Constants.VALID_SEQUENCES){
            occurrences += horizontalProcess.processDnaSequence(dna, sequence);
            occurrences += verticalProcess.processDnaSequence(dna, sequence);
            occurrences += diagonalProcess.processDnaSequence(dna, sequence);
        }
        isMutant = occurrences > Constants.MINIMUN_SEQUENCE_OCCURRENCE;

        saveMutant(dna, isMutant);

        return isMutant;
    }

    public MutantStatDTO getStats(){
        List<Mutant> mutants = mutantRepository.getAll();

        double mutantsCount = (double)mutants.stream()
                .filter(Mutant::isMutant)
                .count();
        double humansCount = (double)mutants.size() - mutantsCount;

        double ratio = (humansCount == 0) ? 0.0 : mutantsCount / humansCount;

        return MutantStatDTO.builder()
                .countHumanDna((int)humansCount)
                .countMutantDna((int)mutantsCount)
                .ratio(ratio)
                .build();
    }

    private boolean isValidSequence(String[] dna){
        return Arrays.stream(dna)
                .anyMatch(seq -> Utils.validateRegExp(Constants.SEQUENCE_VALID, seq));

    }

    private void saveMutant(String[] dna, boolean isMutant){
        mutantRepository.save(new Mutant().builder()
                .isMutant(isMutant)
                .dna(Utils.arraytoPlainString(dna))
                .build()
        );
    }
}

package com.meli.challenge.mutantchallenge.service;

import com.meli.challenge.mutantchallenge.model.Mutant;
import com.meli.challenge.mutantchallenge.repository.impl.MutantRepositoryImpl;
import com.meli.challenge.mutantchallenge.service.model.dto.MutantStatDTO;
import com.meli.challenge.mutantchallenge.service.processor.DiagonalSequenceProcess;
import com.meli.challenge.mutantchallenge.service.processor.HorizontalSequenceProcess;
import com.meli.challenge.mutantchallenge.service.processor.VerticalSequenceProcess;
import com.meli.challenge.mutantchallenge.utils.exceptions.InvalidDnaSequenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class DNAServiceTest {

    @InjectMocks
    private DNAService dnaService;

    @Mock
    private MutantRepositoryImpl mutanrepository;

    @Mock
    private HorizontalSequenceProcess horizontalProcess;

    @Mock
    private VerticalSequenceProcess verticalProcess;

    @Mock
    private DiagonalSequenceProcess diagonalProcess;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mutanrepository.save(any())).thenReturn(new Mutant());
    }


    @Test
    void shouldReturnFalseWithOneOccurenceDNASequence() throws InvalidDnaSequenceException {
        //Arrange
        String[] dna = {"ATGAGA", "ATGAGG", "TAGCGA", "ATGAGG"};

        when(horizontalProcess.processDnaSequence(dna, "")).thenReturn(1);
        when(verticalProcess.processDnaSequence(dna, "")).thenReturn(0);
        when(diagonalProcess.processDnaSequence(dna, "")).thenReturn(0);

        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnMutants() throws InvalidDnaSequenceException {
        //Arrange
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        when(horizontalProcess.processDnaSequence(dna, "AAAA")).thenReturn(1);
        when(verticalProcess.processDnaSequence(dna, "TTTT")).thenReturn(1);
        when(diagonalProcess.processDnaSequence(dna, "CCCC")).thenReturn(1);

        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnNoMutants() throws InvalidDnaSequenceException {
        //Arrange
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

        when(horizontalProcess.processDnaSequence(dna, "")).thenReturn(1);
        when(verticalProcess.processDnaSequence(dna, "")).thenReturn(0);
        when(diagonalProcess.processDnaSequence(dna, "")).thenReturn(0);

        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnStatsSuccesfully(){
        //Arrange
        List<Mutant> mutantList = new ArrayList<>();
        mutantList.add(Mutant.builder()
                .dna("")
                .isMutant(true)
                .build());
        mutantList.add(Mutant.builder()
                .dna("")
                .isMutant(false)
                .build());
        mutantList.add(Mutant.builder()
                .dna("")
                .isMutant(false)
                .build());

        when(mutanrepository.getAll()).thenReturn(mutantList);

        //Act
        MutantStatDTO response = dnaService.getStats();

        //Assert
        assertThat(response.getCountHumanDna()).isEqualTo(2);
        assertThat(response.getCountMutantDna()).isEqualTo(1);
        assertThat(response.getRatio()).isEqualTo(0.5);

    }
}

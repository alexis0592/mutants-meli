package com.meli.challenge.mutantchallenge.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DNAServiceTest {

    @Autowired
    private DNAService dnaService;

    @Test
    void shouldReturnFalseWithDNAArrayWithWronSequence(){
        //Arrange
        String[] dna = {"ATGCGA", "ATGAGG", "TfGCGA", "ATGCGA"};
        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWithDNAArrayWithLengthLessThree(){
        //Arrange
        String[] dna = {"ATGAGA", "ATGAGG", "TFGCGA"};
        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWithOneOccurenceDNASequence(){
        //Arrange
        String[] dna = {"ATGAGA", "ATGAGG", "TAGCGA", "ATGAGG"};
        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnMutants(){
        //Arrange
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnNoMutants(){
        //Arrange
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        //Act
        boolean result = dnaService.isMutant(dna);
        //Assert
        assertThat(result).isFalse();
    }
}

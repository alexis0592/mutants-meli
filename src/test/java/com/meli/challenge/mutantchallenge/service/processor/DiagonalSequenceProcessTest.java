package com.meli.challenge.mutantchallenge.service.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiagonalSequenceProcessTest {

    @Autowired
    private DiagonalSequenceProcess diagonalSequenceProcess;

    @Test
    void shouldReturnOneWithoutDiagonalPpalSequence(){
        //Arrange
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        //Act
        int result = diagonalSequenceProcess.processDnaSequence(dna, "AAAA");
        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnOneWithoutDiagonalSecondaryAndPpalSequence(){
        //Arrange
        String[] dna = {"ATGCGA", "CAGTAC", "TTAAGT", "AGAAGG", "CACCTA", "ACACTG"};
        //Act
        int result = diagonalSequenceProcess.processDnaSequence(dna, "AAAA");
        //Assert
        assertThat(result).isEqualTo(2);
    }

}
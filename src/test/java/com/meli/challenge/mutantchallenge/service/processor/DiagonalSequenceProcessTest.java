package com.meli.challenge.mutantchallenge.service.processor;

import com.meli.challenge.mutantchallenge.model.Mutant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DiagonalSequenceProcessTest {

    @InjectMocks
    private DiagonalSequenceProcess diagonalSequenceProcess;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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
    void shouldReturnTwoWithoutDiagonalSecondaryAndPpalSequence(){
        //Arrange
        String[] dna = {"ATGCGA", "CAGTAC", "TTAAGT", "AGAAGG", "CACCTA", "ACACTG"};
        //Act
        int result = diagonalSequenceProcess.processDnaSequence(dna, "AAAA");
        //Assert
        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldReturnOneWithSequenceInBottomDiagonal(){
        //Arrange
        String[] dna = {"AATGCA", "CTCAGG", "CATACT", "ACACTC", "CTCAGC", "GAGCAA"};
        //Act
        int result = diagonalSequenceProcess.processDnaSequence(dna, "CCCC");
        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnOneWithSequenceInUpperDiagonal(){
        //Arrange
        String[] dna = {"AAGGCCA", "CTAGGG", "CGTAGT", "ACACAG", "CTCAGC", "GAGCAA"};
        //Act
        int result = diagonalSequenceProcess.processDnaSequence(dna, "GGGG");
        //Assert
        assertThat(result).isEqualTo(1);
    }

}
package com.meli.challenge.mutantchallenge.service.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VerticalSequenceProcessTest {

    @Autowired
    private VerticalSequenceProcess verticalSequenceProcess;

    @Test
    void shouldReturnOneWithVerticalSequenceOfA(){
        //Arrange
        String[] dna = {"ATGAGA", "ATGGAG", "AAGCGA", "AAAATG", "ACACTG", "AGTATG"};
        //Act
        int result = verticalSequenceProcess.processDnaSequence(dna, "AAAA");
        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnOneWithVerticalSequenceOfC(){
        //Arrange
        String[] dna = {"ATGTGA", "AGGCAG", "ACGCGA", "ACACTG", "CCACTG", "AGTATG"};
        //Act
        int result = verticalSequenceProcess.processDnaSequence(dna, "CCCC");
        //Assert
        assertThat(result).isEqualTo(1);
    }
}
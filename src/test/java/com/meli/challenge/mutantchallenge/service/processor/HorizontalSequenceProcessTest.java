package com.meli.challenge.mutantchallenge.service.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HorizontalSequenceProcessTest {

    @Autowired
    private HorizontalSequenceProcess horizontalSequenceProcess;

    @Test
    void shouldReturnTrueWithHorizontalSequence(){
        //Arrange
        String[] dna = {"ATGAGA", "ATGGAG", "AAGAGA", "AAAATG"};
        //Act
        int result = horizontalSequenceProcess.processDnaSequence(dna, "AAAA");
        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnFalseWithoutHorizontalSequence(){
        //Arrange
        String[] dna = {"ATGAGA", "ATGAGG", "TAGCGA", "ATGAGG"};
        //Act
        int result = horizontalSequenceProcess.processDnaSequence(dna, "AAAA");
        //Assert
        assertThat(result).isEqualTo(0);
    }
}
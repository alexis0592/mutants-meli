package com.meli.challenge.mutantchallenge.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UtilsTest {

    @Test
    void shouldCountOccurrenceOfT() {
        //Arrange
        String sequence = "ATTCTTF";

        //Act
        int count = Utils.countCharOccurrence(sequence, "T");

        //Assert
        assertThat(count).isEqualTo(4);
    }
}
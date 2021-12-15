package com.meli.challenge.mutantchallenge.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamoDbBean
public class Mutant {

    private String dna;
    private boolean isMutant;

    @DynamoDbPartitionKey
    public String getDna(){
        return dna;
    }

}

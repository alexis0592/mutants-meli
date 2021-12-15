package com.meli.challenge.mutantchallenge.repository.impl;

import com.meli.challenge.mutantchallenge.config.DynamoDBConfig;
import com.meli.challenge.mutantchallenge.model.Mutant;
import com.meli.challenge.mutantchallenge.repository.IMutantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MutantRepositoryImpl implements IMutantRepository {
    private final Logger logger = LoggerFactory.getLogger(MutantRepositoryImpl.class);

    @Autowired
    DynamoDBConfig dynamoDbClient;

    private DynamoDbTable<Mutant> getDynamoDBTableMapperEnhancedClient(){
        return dynamoDbClient.dynamoDbEnhancedClient()
                .table("mutants", TableSchema.fromBean(Mutant.class));
    }

    @Override
    public Mutant save(Mutant mutant) {
        try {
            getDynamoDBTableMapperEnhancedClient().putItem(mutant);
        }catch (DynamoDbException e){
            logger.error(e.getMessage());
            return null;
        }
        return mutant;
    }

    @Override
    public List<Mutant> getAll() {
        List<Mutant> result = new ArrayList<>();
        try {
            for(Mutant mutant: getDynamoDBTableMapperEnhancedClient().scan().items()){
                result.add(mutant);
            }
        }catch (DynamoDbException e){
            logger.error(e.getMessage());
        }
        return result;
    }
}

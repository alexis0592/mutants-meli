package com.meli.challenge.mutantchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Profile("!local")
@Configuration
public class DynamoDBConfigPdn implements DynamoDBConfig {

    @Override
    @Bean
    public DynamoDbClient amazonDynamoDb(){
        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(amazonDynamoDb())
                .build();
    }
}

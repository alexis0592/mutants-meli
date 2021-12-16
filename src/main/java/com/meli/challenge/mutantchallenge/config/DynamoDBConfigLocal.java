package com.meli.challenge.mutantchallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
@Profile("local")
public class DynamoDBConfigLocal implements DynamoDBConfig {

    @Override
    @Bean
    public DynamoDbClient amazonDynamoDb(){
       return DynamoDbClient.builder()
               .region(Region.US_EAST_1)
               .endpointOverride(URI.create("http://localhost:4566"))
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

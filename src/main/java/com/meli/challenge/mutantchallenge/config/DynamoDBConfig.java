package com.meli.challenge.mutantchallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Value("${config.aws.region}")
    private String region;
    @Value("${config.aws.dynamodb.url}")
    private String dynamoDbEndpointUrl;
    @Value("${config.aws.dynamodb.access-key}")
    private String accessKey;
    @Value("${config.aws.dynamodb.secret-key}")
    private String secretKey;

   @Bean
    public DynamoDbClient amazonDynamoDb(){
       return DynamoDbClient.builder()
               .region(Region.US_EAST_1)
               .build();
   }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(amazonDynamoDb())
                .build();
    }


}

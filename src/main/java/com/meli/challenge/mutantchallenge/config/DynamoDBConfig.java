package com.meli.challenge.mutantchallenge.config;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public interface DynamoDBConfig {
     DynamoDbClient amazonDynamoDb();
     DynamoDbEnhancedClient dynamoDbEnhancedClient();
}

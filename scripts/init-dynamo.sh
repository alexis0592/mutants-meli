#!/bin/bash

# -- > Create DynamoDb Table
echo Creating  DynamoDb \'Mutants\' table ...
echo $(awslocal dynamodb create-table --cli-input-json '{"TableName":"mutants", "KeySchema":[{"AttributeName":"dna","KeyType":"HASH"}], "AttributeDefinitions":[{"AttributeName":"dna","AttributeType":"S"}],"BillingMode":"PAY_PER_REQUEST"}')

# --> List DynamoDb Tables
echo Listing tables ...
echo $(awslocal dynamodb list-tables)
echo $(awslocal dynamodb list-tables)
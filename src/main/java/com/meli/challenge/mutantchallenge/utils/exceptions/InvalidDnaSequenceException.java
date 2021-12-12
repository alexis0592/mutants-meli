package com.meli.challenge.mutantchallenge.utils.exceptions;

public class InvalidDnaSequenceException extends Exception{
    public InvalidDnaSequenceException() {
        super("The DNA Sequence is Invalid");
    }
}

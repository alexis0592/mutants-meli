package com.meli.challenge.mutantchallenge.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
public class Constants {
    public static final String SEQUENCE_VALID = "[^ATCG]";
    public static final int MINIMUN_SEQUENCE_OCCURRENCE = 1;
}

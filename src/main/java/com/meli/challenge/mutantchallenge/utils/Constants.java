package com.meli.challenge.mutantchallenge.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
public class Constants {
    public static final String SEQUENCE_VALID = "[^ATCG]";
    public static final String[] VALID_SEQUENCES = {
            NitrogenBase.AAAA.getNitrogenBase(),
            NitrogenBase.TTTT.getNitrogenBase(),
            NitrogenBase.CCCC.getNitrogenBase(),
            NitrogenBase.GGGG.getNitrogenBase()
    };
    public static final int MINIMUN_SEQUENCE_OCCURRENCE = 1;
}

enum NitrogenBase {
    AAAA("AAAA"), TTTT("TTTT"), CCCC("CCCC"), GGGG("GGGG");

    private final String nitrogenBase;

    NitrogenBase(String nitrogenBase) {
        this.nitrogenBase = nitrogenBase;
    }

    public String getNitrogenBase() {
        return nitrogenBase;
    }
}


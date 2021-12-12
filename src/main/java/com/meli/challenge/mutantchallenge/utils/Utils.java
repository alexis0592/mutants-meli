package com.meli.challenge.mutantchallenge.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utils {

    public static boolean validateRegExp(String regExp, String sequenceToValidate){
        Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sequenceToValidate);
        return matcher.find();
    }

    public static int countCharOccurrence(String value, String occurrence){

        return StringUtils.countOccurrencesOf(value, occurrence);
    }
}

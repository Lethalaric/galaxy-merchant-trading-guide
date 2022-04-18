package com.prospace.challenge.galaxymerchant.utility;

import java.util.*;

public class RomanConversionUtility {
    private static final List<Character> CANNOT_BE_REPEATED_CODES = Arrays.asList('D', 'L', 'V');
    private static final Map<Character, Integer> ROMAN_CODE_INTEGER_MAPPING = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    private static final TreeMap<Integer, String> INTEGER_ROMAN_CODE_MAPPING = new TreeMap<>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    public int convertRomanToInteger(String words) throws IllegalArgumentException {
        validateRepeatedWords(words);

        int integerResult = 0;

        for (int i = 0; i < words.length(); i++) {
            if ( i < words.length()-1 && ableToSubstracted(words.charAt(i), words.charAt(i+1))) {
                integerResult += (ROMAN_CODE_INTEGER_MAPPING.get(words.charAt(i+1)) - ROMAN_CODE_INTEGER_MAPPING.get(words.charAt(i)));
                i++;
            } else {
                integerResult += ROMAN_CODE_INTEGER_MAPPING.get(words.charAt(i));
            }
        }

        return integerResult;
    }

    public String convertIntegerToRoman(int intValue) {
        String result = "";
        do {
            int smallerValue = INTEGER_ROMAN_CODE_MAPPING.floorKey(intValue);
            if ( intValue == smallerValue ) {
                result += INTEGER_ROMAN_CODE_MAPPING.get(intValue);
                intValue -= smallerValue;
            }
        } while(intValue != 0);

        return result;
    }

    public void validateRepeatedWords(String words) throws IllegalArgumentException {
        int repeatedCounter = 0;

        if (words.length() > 2) {
            for (int i = 1; i < words.length(); i++) {
                if (CANNOT_BE_REPEATED_CODES.contains(words.charAt(i)) && CANNOT_BE_REPEATED_CODES.contains(words.charAt(i - 1))) {
                    throw new IllegalArgumentException("D L V codes cannot be repeated");
                }

                if (repeatedCounter > 3) {
                    throw new IllegalArgumentException("Repeated codes cannot exceed 3 times");
                }

                if (words.charAt(i) == words.charAt(i - 1)) {
                    repeatedCounter++;
                } else {
                    repeatedCounter = 0;
                }
            }
        }
    }

    public boolean ableToSubstracted(char code1, char code2) {
        switch(code1) {
            case 'I': {
                return code2 == 'V' || code2 == 'X';
            }
            case 'X': {
                return code2 == 'L' || code2 == 'C';
            }
            case 'C': {
                return code2 == 'D' || code2 == 'M';
            }
            case 'V':
            case 'L':
            case 'D':
            default: return false;
        }
    }
}

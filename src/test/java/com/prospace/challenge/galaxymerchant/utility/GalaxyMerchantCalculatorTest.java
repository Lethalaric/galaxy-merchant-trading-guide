package com.prospace.challenge.galaxymerchant.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyMerchantCalculatorTest {

    private final GalaxyMerchantCalculator galaxyMerchantCalculator = new GalaxyMerchantCalculator();

    private final String[][] testCases =
    {
        new String[] {
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L",
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits",
                "how much is pish tegj glob glob ?",
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob prok Gold ?",
                "how many Credits is glob prok Iron ?",
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
        },
    };

    @Test
    void CalculateTest() {
        for (String[] sentences: testCases) {
            galaxyMerchantCalculator.startCalculator(sentences);
        }
    }

}
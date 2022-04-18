package com.prospace.challenge.galaxymerchant.utility;

public class MetalUtility {
    public double findMetalValue(int romanInt, int credit) {
        return 1.0*credit / romanInt;
    }

    public double calculateMetal(int romantInt, double metalValue) {
        return romantInt * metalValue;
    }
}

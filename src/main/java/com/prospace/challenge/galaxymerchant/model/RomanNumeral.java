package com.prospace.challenge.galaxymerchant.model;

public enum RomanNumeral {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    public final int numeral;

    private RomanNumeral(int value) {
        this.numeral = value;
    }
}

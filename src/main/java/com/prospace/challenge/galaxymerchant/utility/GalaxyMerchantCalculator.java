package com.prospace.challenge.galaxymerchant.utility;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;

public class GalaxyMerchantCalculator {
    private static final Map<String, String> DATA_CONVERTER = new HashMap<>();
    private static final Map<String, Double> METAL_CONVERTER = new HashMap<>();

    private final DecimalFormat decimalFormat = new DecimalFormat("0.#");
    private final RomanConversionUtility romanConversionUtility = new RomanConversionUtility();
    private final MetalUtility metalUtility = new MetalUtility();

    public void startCalculator(String[] stringValues) {
        for (int i = 0; i < stringValues.length; i++) {
            String[] sentenceAsArray = stringValues[i].split(" ");
            int sentenceLength = sentenceAsArray.length;
            if (sentenceLength == 3) {
                setDataConverter(sentenceAsArray[0], sentenceAsArray[2]);
            } else if (sentenceAsArray[sentenceLength-1].equals("Credits")) {
                setMetalConverter(sentenceAsArray, sentenceLength);
            } else if (sentenceAsArray[sentenceLength-1].equals("?")) {
                List<String> questionVariables = getListOfQuestionVariables(sentenceAsArray, sentenceLength);

                String questionVariableAsString = String.join(" ", questionVariables);
                if (METAL_CONVERTER.containsKey(questionVariables.get(questionVariables.size()-1))) {
                    String romanCode = getRomanCodes(questionVariables.subList(0, questionVariables.size()-1).toArray(new String[]{}));
                    int romanInt = romanConversionUtility.convertRomanToInteger(romanCode);
                    System.out.println(questionVariableAsString + " is " + decimalFormat.format(metalUtility.calculateMetal(romanInt, METAL_CONVERTER.get(questionVariables.get(questionVariables.size()-1)))) + " Credits");
                } else {
                    String romanCode = getRomanCodes(questionVariables.toArray(new String[]{}));
                    if (romanCode.contains("null")) {
                        System.out.println("I have no idea what you are talking about ");
                    } else {
                        int romanInt = romanConversionUtility.convertRomanToInteger(romanCode);

                        System.out.println(questionVariableAsString + " is " + decimalFormat.format(romanInt));
                    }
                }
            } else {
                System.out.println("I have no idea what you are talking about ");
            }
        }
    }

    private List<String> getListOfQuestionVariables(String[] sentenceAsArray, int sentenceLength) {

        List<String> questionVariables = new ArrayList<>();
        for (int j = sentenceLength-2; j > 0; j--) {
            if (sentenceAsArray[j].equals("is")) {
                break;
            }
            questionVariables.add(sentenceAsArray[j]);
        }
        Collections.reverse(questionVariables);
        return questionVariables;
    }

    private void setDataConverter(String s1, String s2) {
        DATA_CONVERTER.put(s1, s2);
    }

    private void setMetalConverter(String[] sentenceAsArray, int sentenceLength) {
        int credits = Integer.parseInt(sentenceAsArray[sentenceLength-2]);
        String metalType = sentenceAsArray[sentenceLength-4];
        String[] datas = Arrays.copyOfRange(sentenceAsArray, 0, sentenceLength-4);
        String romanCode = getRomanCodes(datas);
        METAL_CONVERTER.put(metalType, metalUtility.findMetalValue(romanConversionUtility.convertRomanToInteger(romanCode), credits));
    }

    public String getRomanCodes(String[] datas) {
        String result = "";
        for (int i = 0; i < datas.length; i++) {
            result += DATA_CONVERTER.get(datas[i]);
        }
        return result;
    }
}

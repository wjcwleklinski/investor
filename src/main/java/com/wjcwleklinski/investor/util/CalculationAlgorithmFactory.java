package com.wjcwleklinski.investor.util;

public class CalculationAlgorithmFactory {

    public static CalculationAlgorithm provideAlgorithm(String algorithmName) {
        if(algorithmName.equals(CalculationAlgorithm.ON_INVESTMENTS_END))
            return new OnInvestmentsEndAlgorithm();
        else if (algorithmName.equals(CalculationAlgorithm.ON_CALCULATION_DATE))
            return new OnCalculationDateAlgorithm();
        else
            return null;
    }
}

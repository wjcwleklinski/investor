package com.wjcwleklinski.investor.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OnInvestmentsEndAlgorithm implements CalculationAlgorithm {

    @Override
    public BigDecimal computeProfit(BigDecimal amount, BigDecimal rate, Integer capitalisationPeriod, Integer durationInDays) {

        BigDecimal capitalisationCount = BigDecimal.valueOf(12 / capitalisationPeriod);
        BigDecimal durationInYears = BigDecimal.valueOf(360 / durationInDays);
        BigDecimal result = rate.divide(capitalisationCount).add(BigDecimal.ONE);
        result = result.pow(durationInYears.multiply(capitalisationCount).intValue());
        return result.multiply(amount).subtract(amount).setScale(2, RoundingMode.CEILING);
    }
}

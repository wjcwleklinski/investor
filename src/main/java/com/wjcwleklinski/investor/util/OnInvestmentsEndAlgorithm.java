package com.wjcwleklinski.investor.util;


import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class OnInvestmentsEndAlgorithm implements CalculationAlgorithm {

    @Override
    public BigDecimal computeProfit(Calculation calculation, Investment investment) {

        BigDecimal capitalisationCount = BigDecimal.valueOf(12 / investment.getCapitalisationPeriod());
        BigDecimal durationInYears = BigDecimal.valueOf((double)investment.calculateDurationInDays() / 360);

        BigDecimal result = investment.getRate().divide(capitalisationCount).add(BigDecimal.ONE);
        result = result.pow(durationInYears.multiply(capitalisationCount).intValue());
        return result.multiply(calculation.getAmount()).subtract(calculation.getAmount())
                .setScale(2, RoundingMode.CEILING);
    }
}

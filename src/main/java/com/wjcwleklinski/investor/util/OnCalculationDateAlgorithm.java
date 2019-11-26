package com.wjcwleklinski.investor.util;

import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

public class OnCalculationDateAlgorithm implements CalculationAlgorithm {

    @Override
    public BigDecimal computeProfit(Calculation calculation, Investment investment) {

        Period period = Period.between(investment.getStartDate(), calculation.getCalculationDate());
        Integer durationInMonths = ( 360 * period.getYears() + 30 * period.getMonths() + period.getDays()) / 30;

        Integer passedCapitalisationCount = durationInMonths / investment.getCapitalisationPeriod();

        BigDecimal capitalisationCount = BigDecimal.valueOf(12 / investment.getCapitalisationPeriod());

        BigDecimal result = investment.getRate().divide(capitalisationCount).add(BigDecimal.ONE);
        result = result.pow(passedCapitalisationCount);
        return result.multiply(calculation.getAmount()).subtract(calculation.getAmount())
                .setScale(2, RoundingMode.CEILING);
    }
}

package com.wjcwleklinski.investor.util;

import java.math.BigDecimal;

public interface CalculationAlgorithm {

    String ON_INVESTMENTS_END = "ON_INVESTMENTS_END";
    String ON_CALCULATION_DATE = "ON_CALCULATION_DATE";

    BigDecimal computeProfit(BigDecimal amount, BigDecimal rate, Integer capitalisationPeriod, Integer durationInDays);
}

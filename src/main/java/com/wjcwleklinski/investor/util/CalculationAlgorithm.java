package com.wjcwleklinski.investor.util;

import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculationAlgorithm {

    String ON_INVESTMENTS_END = "ON_INVESTMENTS_END";
    String ON_CALCULATION_DATE = "ON_CALCULATION_DATE";

    BigDecimal computeProfit(Calculation calculation, Investment investment);

}

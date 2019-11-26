package com.wjcwleklinski.investor.exception;

import java.time.LocalDate;

public class WrongCalculationDateException extends RuntimeException {

    private Long investmentId;

    private LocalDate wrongCalculationDate;

    public WrongCalculationDateException(Long investmentId, LocalDate wrongCalculationDate) {
        this.investmentId = investmentId;
        this.wrongCalculationDate = wrongCalculationDate;
    }

    public Long getInvestmentId() {
        return investmentId;
    }

    public LocalDate getWrongCalculationDate() {
        return wrongCalculationDate;
    }
}

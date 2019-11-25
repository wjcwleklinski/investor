package com.wjcwleklinski.investor.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Duration {

    private LocalDate startDate;
    private LocalDate endDate;

    public Duration(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getDurationInDays() {
        return Period.between(startDate, endDate).getDays();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{" +
                startDate + "-" +
                endDate +
                '}';
    }
}

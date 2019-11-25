package com.wjcwleklinski.investor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
public class Investment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal rate;

    private Integer capitalisationPeriod;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL)
    private List<Calculation> calculations;

    private Long calculationsCounter;

    public void addCalculation(Calculation calculation) {
        this.calculations.add(calculation);
        calculation.setInvestment(this);
    }

    public void removeCalculation(Calculation calculation) {
        this.calculations.remove(calculation);
        calculation.setInvestment(null);
    }

    public Integer calculateDurationInDays() {
//        return ChronoUnit.DAYS.between(startDate, endDate);
        Period period = Period.between(startDate, endDate);
        return period.getYears() * 360 + period.getMonths() * 30 + period.getDays();
    }

    public Investment() {}

    public Investment(String name, BigDecimal rate, Integer capitalisationPeriod, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.rate = rate;
        this.capitalisationPeriod = capitalisationPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getCapitalisationPeriod() {
        return capitalisationPeriod;
    }

    public void setCapitalisationPeriod(Integer capitalisationPeriod) {
        this.capitalisationPeriod = capitalisationPeriod;
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

    public Long getCalculationsCounter() {
        return calculationsCounter;
    }

    public void setCalculationsCounter(Long calculationsCounter) {
        this.calculationsCounter = calculationsCounter;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }
}

package com.wjcwleklinski.investor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wjcwleklinski.investor.validation.DateChronology;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
//@DateChronology(startDate = "startDate", endDate = "endDate")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Provide investments name")
    private String name;

    @DecimalMin("0.01")
    @DecimalMax("1.00")
    @NotNull(message = "Provide investments rate")
    private BigDecimal rate;

    @Min(1)
    @Max(12)
    @NotNull(message = "Provide capitalisation period in months")
    private Integer capitalisationPeriod;

    @NotNull(message = "Provide start date")
    private LocalDate startDate;

    @NotNull(message = "Provide end date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL)
    private List<Calculation> calculations;

    private Long calculationsCounter;

    @AssertTrue(message = "startDate and endDate are not in chronological order")
    public boolean isValid() {
        return startDate.isBefore(endDate);
    }

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

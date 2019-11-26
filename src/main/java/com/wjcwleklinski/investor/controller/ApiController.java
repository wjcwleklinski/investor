package com.wjcwleklinski.investor.controller;

import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.exception.InvestmentNotFoundException;
import com.wjcwleklinski.investor.exception.WrongAlgorithmNameException;
import com.wjcwleklinski.investor.exception.WrongCalculationDateException;
import com.wjcwleklinski.investor.service.CalculationService;
import com.wjcwleklinski.investor.service.InvestmentService;
import com.wjcwleklinski.investor.util.CalculationAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private CalculationService calculationService;

    @PostMapping(path = "/investments")
    public Map<String, Object> addInvestment(@RequestBody Investment investment )
    {
        investmentService.save(investment);
        Map<String, Object> output = new HashMap<>();
        output.put("id", investment.getId());
        output.put("name", investment.getName());
        output.put("rate", investment.getRate());
        output.put("duration in days", investment.calculateDurationInDays());
        return output;
    }

    @GetMapping(path = "/investments")
    public List<Map<String, Object>> listInvestments() {

        List<Investment> investments = investmentService.findAll();
        List<Map<String, Object>> output = investments.stream().map(inv -> {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", inv.getId());
            obj.put("name", inv.getName());
            return obj;
        }).collect(Collectors.toList());

        return output;
    }

    @PostMapping(path = "/investments/{id}/calculations")
    public Map<String, Object> addCalculation(@PathVariable("id") Long id,
                                                      @RequestBody Calculation calculation) {

        Optional<Investment> optionalInvestment = investmentService.findById(id);
        if (!optionalInvestment.isPresent()) {
            throw new InvestmentNotFoundException(id);
        }

        Investment investment = optionalInvestment.get();
        investment.addCalculation(calculation);

        calculation.updateCalculationDate();

        if ( !(calculation.getAlgorithm().equals(CalculationAlgorithm.ON_CALCULATION_DATE) ||
            calculation.getAlgorithm().equals(CalculationAlgorithm.ON_INVESTMENTS_END)) ) {
            throw new WrongAlgorithmNameException(calculation.getAlgorithm());
        }

        if (calculation.getAlgorithm().equals(CalculationAlgorithm.ON_CALCULATION_DATE) &&
                (calculation.getCalculationDate().isBefore(investment.getStartDate()) ||
                calculation.getCalculationDate().isAfter(investment.getEndDate()) ) ) {
            throw new WrongCalculationDateException(investment.getId(), calculation.getCalculationDate());
        }

        calculation.setProfit(calculationService.calculateProfit(calculation, investment));

        calculationService.save(calculation);

        Map<String, Object> output = new HashMap<>();
        output.put("amount", calculation.getAmount());
        output.put("calculation date", calculation.getCalculationDate());
        output.put("investment", investment.getName());
        output.put("algorithm", calculation.getAlgorithm());
        output.put("profit", calculation.getProfit());

        return output;
    }

    @GetMapping(path = "/investments/{id}/calculations")
    public Investment listCalculations(@PathVariable("id") Long id) {

        Optional<Investment> optionalInvestment = investmentService.findById(id);
        if (!optionalInvestment.isPresent()) {
            throw new InvestmentNotFoundException(id);
        }
        return optionalInvestment.get();
    }
}























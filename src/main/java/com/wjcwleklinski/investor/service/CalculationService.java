package com.wjcwleklinski.investor.service;

import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.repository.CalculationRepository;
import com.wjcwleklinski.investor.util.CalculationAlgorithm;
import com.wjcwleklinski.investor.util.CalculationAlgorithmFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    public Calculation save(Calculation calculation) {
        return calculationRepository.save(calculation);
    }

    public List<Calculation> findAll() {
        return (List<Calculation>) calculationRepository.findAll();
    }

    public BigDecimal calculateProfit(Calculation calculation, Investment investment) {
        CalculationAlgorithm algorithm = CalculationAlgorithmFactory.provideAlgorithm(calculation.getAlgorithm());
        return algorithm.computeProfit(calculation, investment);
    }
}

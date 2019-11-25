package com.wjcwleklinski.investor.service;

import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    public Calculation save(Calculation calculation) {
        return calculationRepository.save(calculation);
    }
}

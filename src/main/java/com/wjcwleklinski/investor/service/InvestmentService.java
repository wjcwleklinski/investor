package com.wjcwleklinski.investor.service;

import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    public List<Investment> findAll() {
        return (List<Investment>) investmentRepository.findAll();
    }

    public Optional<Investment> findById(Long id) {
        return investmentRepository.findById(id);
    }
}

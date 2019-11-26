package com.wjcwleklinski.investor.service;

import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.repository.InvestmentRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"investments"})
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private Logger logger;

    private List<Investment> investments;

    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Cacheable
    public List<Investment> findAll() {
        investments = (List<Investment>) investmentRepository.findAll();
        logger.info("Getting investments");
        return investments;
    }

    public Optional<Investment> findById(Long id) {
        return investmentRepository.findById(id);
    }
}

package com.wjcwleklinski.investor.controller;

import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping(path = "/investments")
    public ResponseEntity<Investment> addInvestment(@RequestBody Investment investment )//Map<String, Object> incomingInvestment)
    {

//        String name = (String) incomingInvestment.get("name");
//        Integer rate = (Integer) incomingInvestment.get("interestRate");
//        Integer capitalizationPeriod = (Integer) incomingInvestment.get("capitalizationPeriod");
//        LocalDate startDate = (LocalDate) incomingInvestment.get("startDate");
//        LocalDate endDate = (LocalDate) incomingInvestment.get("endDate");
//
//        Investment investment = new Investment(name, rate, capitalizationPeriod, startDate, endDate);
        investmentService.save(investment);
        return new ResponseEntity<>(investment, HttpStatus.OK);
    }

    @GetMapping(path = "/investments")
    public List<Investment> listInvestments() {
        return investmentService.findAll();
    }
}

package com.wjcwleklinski.investor.controller;

import com.wjcwleklinski.investor.exception.InvestmentNotFoundException;
import com.wjcwleklinski.investor.exception.WrongAlgorithmNameException;
import com.wjcwleklinski.investor.exception.WrongCalculationDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(WrongCalculationDateException.class)
    public  ResponseEntity wrongCalculationDate(WrongCalculationDateException e) {

        Long investmentId = e.getInvestmentId();
        LocalDate calculationDate = e.getWrongCalculationDate();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Calculation from " + calculationDate + " is not correct for investment of id: " + investmentId);
    }

    @ExceptionHandler(WrongAlgorithmNameException.class)
    public ResponseEntity wrongAlgorithmName(WrongAlgorithmNameException e) {

        String algorithmName = e.getAlgorithmName();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Algorithm of name: " + algorithmName + " does not exist");
    }


    @ExceptionHandler(InvestmentNotFoundException.class)
    public ResponseEntity investmentNotFound(InvestmentNotFoundException e) {

        Long id = e.getId();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Investment of id: " + id + " not found");
    }
}

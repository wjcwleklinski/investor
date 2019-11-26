package com.wjcwleklinski.investor.exception;

public class InvestmentNotFoundException extends RuntimeException {

    private Long id;

    public InvestmentNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

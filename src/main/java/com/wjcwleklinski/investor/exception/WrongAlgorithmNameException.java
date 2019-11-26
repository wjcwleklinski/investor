package com.wjcwleklinski.investor.exception;

public class WrongAlgorithmNameException extends RuntimeException {

    private String algorithmName;

    public WrongAlgorithmNameException(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}

package com.digisphere.propertize.application.contract.utils;

public class TerminationFeePercentCalculate {

    public static Double calculate(Integer period) {
        if(period <= 2) return 0.05;
        if(period <= 4) return 0.15;
        else return 0.30;
    }
}

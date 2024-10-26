package com.digisphere.propertize.application.contract.utils;

import java.time.LocalDate;

public class CalculateContractPeriod {

    public static LocalDate calculate(LocalDate startDate, Integer period) {
        return startDate.plusYears(period);
    }
}

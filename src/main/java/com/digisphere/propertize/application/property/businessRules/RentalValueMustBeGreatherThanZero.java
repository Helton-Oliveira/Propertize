package com.digisphere.propertize.application.property.businessRules;

import com.digisphere.propertize.application.property.domain.Property;

public class RentalValueMustBeGreatherThanZero implements IPropertyRules{
    @Override
    public void valid(Property property) {
        Double rentValue = property.getRentValue();
        if (rentValue <= 0) {
            throw new RuntimeException("ERRO! VALOR DA PROPRIEDADE DEVE SER MAIOR QUE 0");
        }
    }
}

package com.digisphere.propertize.application.property.domain.component;

public enum PropertyStatus {
    AVAILABLE("disponivel"),
    RENTED("alugado"),
    UNDER_MAINTENANCE("em manutenção"),
    INACTIVE("inativa");


    private String status;

    PropertyStatus (String status) {
        this.status = status;
    }

    public static PropertyStatus fromString(String text) {
        for (PropertyStatus statusP : PropertyStatus.values()) {
            if(statusP.status.equalsIgnoreCase(text)) return statusP;
        }
        throw new IllegalArgumentException("ERRO NENHUM STATUS DE PROPRIEDADE ENCONTRADO PARA: " + text);
    }
}

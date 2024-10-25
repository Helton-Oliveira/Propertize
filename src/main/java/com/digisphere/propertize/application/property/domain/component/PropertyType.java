package com.digisphere.propertize.application.property.domain.component;

public enum PropertyType {
    RESIDENTIAL("residencial"),
    COMMERCIAL("comercial"),
    INDUSTRIAL("industrial");

    private String type;

    PropertyType (String type) {
        this.type = type;
    }

    public static PropertyType fromString(String text) {
        for (PropertyType typeP : PropertyType.values()) {
            if(typeP.type.equalsIgnoreCase(text)) return typeP;
        }

        throw new IllegalArgumentException("ERRO NENHUM TYPE DE PROPRIEDADE ENCONTRADO PARA: " + text);
    }
}

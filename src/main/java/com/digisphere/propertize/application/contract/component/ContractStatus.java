package com.digisphere.propertize.application.contract.component;

public enum ContractStatus {
    ACTIVE("ativo"),
    TERMINATED("terminado"),
    EXPIRED("expirado"),
    RENEWED("renovado");

    public String status;

    ContractStatus(String status) {
        this.status = status;
    }

    public static ContractStatus fromString(String text) {
        for (ContractStatus contractStatus : ContractStatus.values() ) {
            if(contractStatus.status.equalsIgnoreCase(text)) return contractStatus;
        }

        throw new IllegalArgumentException("ERRO! TEXTO NAO RECONHECIDO: "  + text);
    }
}

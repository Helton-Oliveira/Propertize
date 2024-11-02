package com.digisphere.propertize.application.maintenance.domain.component;

public enum MaintenanceStatus {
    OPEN("aberto"),
    IN_PROGRESS("em progresso"),
    COMPLETED("completo"),
    CANCELED("cancelado");

    private String status;

    MaintenanceStatus(String status) {
        this.status = status;
    }

    public static MaintenanceStatus getStatus(String text) {
        for (MaintenanceStatus maintenanceStatus : MaintenanceStatus.values()) {
            if (maintenanceStatus.status.equalsIgnoreCase(text)) return maintenanceStatus;
        }

        throw new RuntimeException("ERRO STATUS NAO ENCONTRADO");
    }

}

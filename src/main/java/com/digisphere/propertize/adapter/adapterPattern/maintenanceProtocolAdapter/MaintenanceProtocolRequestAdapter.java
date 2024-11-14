package com.digisphere.propertize.adapter.adapterPattern.maintenanceProtocolAdapter;

import com.digisphere.propertize.adapter.dtos.maintenanceProtocol.*;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IGetOneMaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IOpenProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IUpdateMaintenanceStatus;

import java.util.Map;

public class MaintenanceProtocolRequestAdapter implements IMaintenanceProtocolRequestAdapter{
    private final IOpenProtocol openProtocol;
    private final IGetOneMaintenanceProtocol getOneMaintenanceProtocol;
    private final IUpdateMaintenanceStatus updateMaintenanceStatus;

    public MaintenanceProtocolRequestAdapter(IOpenProtocol openProtocol, IGetOneMaintenanceProtocol getOneMaintenanceProtocol, IUpdateMaintenanceStatus updateMaintenanceStatus) {
        this.openProtocol = openProtocol;
        this.getOneMaintenanceProtocol = getOneMaintenanceProtocol;
        this.updateMaintenanceStatus = updateMaintenanceStatus;
    }

    @Override
    public OutputMaintenanceProtocol adaptCreateMaintenanceProtocolRequest(CreateMaintenanceProtocolDto dto) {

        Map<String, String> data = Map.of(
                "propertyId", dto.propertyId(),
                "tenantCpf", dto.tenantCpf(),
                "maintenanceDetails", dto.maintenanceDetails()
        );

        MaintenanceProtocol protocol = openProtocol.execute(data);
        return toDto(protocol);
    }

    @Override
    public OutputMaintenanceProtocol adaptFindMaintenanceProtocolRequest(MaintenanceProtocolIdReferenceDto dto) {
        MaintenanceProtocol protocol = getOneMaintenanceProtocol.execute(dto.protocol());
        return toDto(protocol);
    }

    @Override
    public String adaptUpdateMaintenanceProtocolRequest(MaintenanceProtocolUpdateStatusDto dto) {
        Map<String, String> newData = Map.of(
                "status", dto.status()
        );
        return updateMaintenanceStatus.execute(dto.protocol(), newData);
    }

    @Override
    public String adaptCloseMaintenanceProtocolRequest(ClosedMaintenanceProtocolDto dto) {
        Map<String, String> newData = Map.of(
                "status", dto.status(),
                "reason", dto.reason()
        );
        return updateMaintenanceStatus.execute(dto.protocol(), newData);
    }

    private OutputMaintenanceProtocol toDto(MaintenanceProtocol protocol) {
        return new OutputMaintenanceProtocol(
                protocol.getProtocol().toString(),
                protocol.getRequestingTenantCpf(),
                protocol.getPropertyIdForMaintenance().toString(),
                protocol.getOpeningDate().toString(),
                protocol.getMaintenanceDetails(),
                protocol.getStatus().name(),
                protocol.getDateOfResolution() != null ? protocol.getDateOfResolution().toString() : null,
                protocol.getReasonForCancellation()
        );
    }
}

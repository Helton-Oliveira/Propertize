package com.digisphere.propertize.adapter.adapterPattern.maintenanceProtocolAdapter;

import com.digisphere.propertize.adapter.dtos.maintenanceProtocol.*;

public interface IMaintenanceProtocolRequestAdapter {
    OutputMaintenanceProtocol adaptCreateMaintenanceProtocolRequest(CreateMaintenanceProtocolDto dto);
    OutputMaintenanceProtocol adaptFindMaintenanceProtocolRequest(MaintenanceProtocolIdReferenceDto dto);
    String adaptUpdateMaintenanceProtocolRequest(MaintenanceProtocolUpdateStatusDto dto);
    String adaptCloseMaintenanceProtocolRequest(ClosedMaintenanceProtocolDto dto);
}

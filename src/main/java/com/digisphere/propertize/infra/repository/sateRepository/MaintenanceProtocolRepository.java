package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.IMaintenanceBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MaintenanceProtocolRepository extends StateRepository{

    private final IConnection connection;
    private final IMaintenanceBuilder maintenanceBuilder;

    public MaintenanceProtocolRepository(IConnection connection, IMaintenanceBuilder maintenanceBuilder) {
        this.connection = connection;
        this.maintenanceBuilder = maintenanceBuilder;
    }

    @Override
    public boolean save(Map<String, Object> data) {
        try {
            var st = connection.query("INSERT INTO maintenance_protocols (protocol, property_id_for_maintenance, requesting_tenant_id, opening_date, maintenance_details, maintenance_status) VALUES (?,?,?,?,?,?)");
            buildInsertion(st, data);
            var result = st.executeUpdate();
            if(result == 0) throw new RuntimeException("ERRO AO CADASTRAR PROTOCOLO");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public <T> T getOne(String id) {
        return null;
    }

    @Override
    public <T> List<T> getAll() {
        return List.of();
    }

    @Override
    public String update(String id, Map<String, String> updateData) {
        return "";
    }

    private void buildInsertion(PreparedStatement stmt, Map<String, Object> data) throws SQLException {
        var protocol = (MaintenanceProtocol) data.get("protocol");

        stmt.setObject(1, protocol.getProtocol());
        stmt.setObject(2, protocol.getPropertyIdForMaintenance());
        stmt.setObject(3, protocol.getRequestingTenantId());
        stmt.setDate(4, Date.valueOf(protocol.getOpeningDate()));
        stmt.setString(5, protocol.getMaintenanceDetails());
        stmt.setString(6, protocol.getStatus().toString());
    }
}

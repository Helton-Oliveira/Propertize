package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.IMaintenanceBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            var st = connection.query("INSERT INTO maintenance_protocols (protocol, property_id_for_maintenance, requesting_tenant_cpf, opening_date, maintenance_details, maintenance_status) VALUES (?,?,?,?,?,?)");
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
    public <T> T getOne(String pk) {
        try {
            var st = connection.query("SELECT * FROM maintenance_protocols WHERE protocol = ?");
            st.setObject(1, UUID.fromString(pk));
            var result = st.executeQuery();
            if (!result.next()) throw new RuntimeException("ID PROTOCOLO NÃO EXISTENTE");
            rebuild(result);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (T) maintenanceBuilder.build();
    }

    @Override
    public <T> List<T> getAll() {
        List<T> maintenanceProtocols = new ArrayList<>();
        try {
            var st = connection.query("SELECT * FROM maintenance_protocols WHERE maintenance_status IN ('OPEN', 'IN_PROGRESS')");
            var result = st.executeQuery();

            while (result.next()) {
                rebuild(result);
                maintenanceProtocols.add((T) maintenanceBuilder.build());
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maintenanceProtocols;
    }

    @Override
    public String update(String pk, Map<String, String> updateData) {
        String column = " maintenance_status = ? ";
        if(MaintenanceStatus.getStatus(updateData.get("status")).equals(MaintenanceStatus.COMPLETED)) column = " maintenance_status = ?, date_of_resolution = ? ";
        if (MaintenanceStatus.getStatus(updateData.get("status")).equals(MaintenanceStatus.CANCELED) && updateData.containsKey("reason")) column = " date_of_resolution = ?, maintenance_status = ?, reason_for_cancellation = ? ";
        try {
            var st = connection.query("UPDATE maintenance_protocols SET" + column + "WHERE protocol = ?");
            changeUpdateColumn(updateData, st, pk);
            var result = st.executeUpdate();
            connection.close();
           if(result == 0) throw new RuntimeException("ERRO AO ATUALIZAR STATUS DO PROTOCOLO");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return "STATUS DO PROTOCOLO " + pk + " ATUALIZADO PARA " + updateData.get("status").toUpperCase();
    }

    private void changeUpdateColumn(Map<String, String> updateData, PreparedStatement st, String id) throws SQLException {
        if(MaintenanceStatus.getStatus(updateData.get("status")) != MaintenanceStatus.COMPLETED || MaintenanceStatus.getStatus(updateData.get("status")) != MaintenanceStatus.CANCELED) {
            st.setString(1, MaintenanceStatus.getStatus(updateData.get("status")).name());
            st.setObject(2, UUID.fromString(id));
        }

        if(MaintenanceStatus.getStatus(updateData.get("status")) == MaintenanceStatus.COMPLETED) {
            st.setString(1, MaintenanceStatus.getStatus(updateData.get("status")).name());
            st.setDate(2, Date.valueOf(LocalDate.now()));
            st.setObject(3, UUID.fromString(id));
        }

        if (MaintenanceStatus.getStatus(updateData.get("status")) == MaintenanceStatus.CANCELED) {
            st.setDate(1, Date.valueOf(LocalDate.now()));
            st.setString(2, MaintenanceStatus.CANCELED.name());
            st.setString(3, updateData.get("reason"));
            st.setObject(4, UUID.fromString(id));
        }
    }

    private void buildInsertion(PreparedStatement stmt, Map<String, Object> data) throws SQLException {
        var protocol = (MaintenanceProtocol) data.get("protocol");

        stmt.setObject(1, protocol.getProtocol());
        stmt.setObject(2, protocol.getPropertyIdForMaintenance());
        stmt.setString(3, protocol.getRequestingTenantCpf());
        stmt.setDate(4, Date.valueOf(protocol.getOpeningDate()));
        stmt.setString(5, protocol.getMaintenanceDetails());
        stmt.setString(6, protocol.getStatus().toString());
    }

    private void rebuild(ResultSet result) throws SQLException {
        maintenanceBuilder.setProtocol((UUID) result.getObject("protocol"));
        maintenanceBuilder.setRequestingTenantCpf(result.getString("requesting_tenant_cpf"));
        maintenanceBuilder.setPropertyId((UUID) result.getObject("property_id_for_maintenance"));
        maintenanceBuilder.setOpeningDate(result.getDate("opening_date").toLocalDate());
        maintenanceBuilder.setDateOfResolution(result.getDate("date_of_resolution") != null ? result.getDate("date_of_resolution").toLocalDate() : null);
        maintenanceBuilder.setMaintenanceDetails(result.getString("maintenance_details"));
        maintenanceBuilder.setMaintenanceStatus(MaintenanceStatus.valueOf(result.getString("maintenance_status")));
        maintenanceBuilder.setReasonForCancellation(result.getString("reason_for_cancellation"));
    }
}

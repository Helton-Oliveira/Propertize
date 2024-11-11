package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.domain.component.ContractStatus;
import com.digisphere.propertize.application.contract.contractBuilder.IContractBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ContractRepository extends StateRepository{

    private final IConnection connection;
    private final IContractBuilder contractBuilder;

    public ContractRepository(IConnection connection, IContractBuilder contractBuilder) {
        this.connection = connection;
        this.contractBuilder = contractBuilder;
    }

    @Override
    public boolean save(Map<String, Object> data) {
        try {
            var st = connection.query("INSERT INTO contracts (id, property_id, tenant_cpf, start_date, end_date, monthly_rent, payment_due_day, security_deposit, contract_status, termination_fee, maintenance_clause,contract_terms) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            buildInsertion(st, data);
            var result = st.executeUpdate();
            if(result == 0) throw new RuntimeException("ERRO! CONTRATO NAO CRIADO");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public <T> T getOne(String pk) {
        try {
           var st = connection.query("SELECT * FROM contracts WHERE id = ?");
            st.setObject(1, UUID.fromString(pk));
            var result = st.executeQuery();
            if(!result.next()) throw new RuntimeException("NOT FOUND");
            rebuild(result);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (T) contractBuilder.build();
    }

    @Override
    public <T> List<T> getAll() {
        List<T> contractList = new ArrayList<>();
        try {
            var st = connection.query("SELECT * FROM contracts");
            var result = st.executeQuery();

            while(result.next()) {
                rebuild(result);
                contractList.add((T) contractBuilder.build());
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contractList;
    }

    @Override
    public String delete(String pk) {
        return null;
    }

    @Override
    public String update(String pk, Map<String, String> updateData) {
        String column = "";
        if(updateData.containsKey("status")) column = "contract_status = ?";
        if(updateData.containsKey("terminationDate") || updateData.containsKey("terminationReason")) column = "termination_date = ?, termination_reason = ?";

        try {
            var st = connection.query("UPDATE contracts SET " + column + " WHERE pk = ?");
            changeUpdate(updateData, st, pk);
            var result = st.executeUpdate();

            if(result == 0) throw new RuntimeException("ERRO! CONTRATO NAO ATUALIZADO");

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "CONTRATO ATUALIZADO NA COLUNA " + column.replaceAll("[^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s+", " ")
                .toUpperCase().trim();
    }

    private void changeUpdate(Map<String, String> updateData, PreparedStatement st, String id) throws SQLException {
        if(updateData.containsKey("terminationDate")) {
            st.setDate(1, Date.valueOf(updateData.get("terminationDate")));
            st.setString(2, updateData.get("terminationReason"));
            st.setObject(3, UUID.fromString(id));
        }

        if (updateData.containsKey("status")) {
            st.setString(1, ContractStatus.fromString(updateData.get("status")).toString());
            st.setObject(2, UUID.fromString(id));
        }
    }

    private void buildInsertion(PreparedStatement st, Map<String, Object> data) throws SQLException {
        var contract = (Contract) data.get("contract");

        st.setObject(1, contract.getId());
        st.setObject(2, contract.getPropertyId());
        st.setString(3, contract.getTenantCpf());
        st.setDate(4, Date.valueOf(contract.getStartDate()));
        st.setDate(5, Date.valueOf(contract.getEndDate()));
        st.setDouble(6, contract.getMonthlyRent());
        st.setInt(7, contract.getPaymentDueDay());
        st.setDouble(8, contract.getSecurityDeposit());
        st.setString(9, contract.getStatus().name());
        st.setDouble(10, contract.getTerminationFee());
        st.setString(11, contract.getMaintenanceClause());
        st.setString(12, contract.getContractTerms());
    }

    private void rebuild(ResultSet result) throws SQLException {
        contractBuilder.setId(result.getObject("id", UUID.class));
        contractBuilder.setPropertyId(result.getObject("property_id", UUID.class));
        contractBuilder.setTenantCpf(result.getString("tenant_cpf"));
        contractBuilder.setStartDate(result.getDate("start_date").toLocalDate());
        contractBuilder.setEndDate(result.getDate("end_date").toLocalDate());
        contractBuilder.setMonthlyRent(result.getDouble("monthly_rent"));
        contractBuilder.setPaymentDueDay(result.getInt("payment_due_day"));
        contractBuilder.setSecurityDeposit(result.getDouble("security_deposit"));
        contractBuilder.setStatus(ContractStatus.valueOf(result.getString("contract_status")));
        contractBuilder.setTerminationFee(result.getDouble("termination_fee"));
        contractBuilder.setRenewable(result.getBoolean("is_renewable"));
        contractBuilder.setAutoRenewal(result.getBoolean("auto_renewal"));
        contractBuilder.setMaintenanceClause(result.getString("maintenance_clause"));
        contractBuilder.setSubleasingAllowed(result.getBoolean("subleasing_allowed"));
        contractBuilder.setContractTerms(result.getString("contract_terms"));
        contractBuilder.setTerminationDate(result.getDate("termination_date") != null ? result.getDate("termination_date").toLocalDate() : null);
        contractBuilder.setTerminationReason(result.getString("termination_reason"));
    }

}

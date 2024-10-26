package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.application.contract.Contract;
import com.digisphere.propertize.application.contract.contractBuilder.IContractBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
            var st = connection.query("INSERT INTO contracts (id, property_id, tenant_id, start_date, end_date, monthly_rent, payment_due_day, security_deposit, contract_status, termination_fee, maintenance_clause,contract_terms) " +
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
    public <T> T getOne(String id) {
        return null;
    }

    @Override
    public <T> List<T> getAll() {
        return List.of();
    }

    @Override
    public String delete(String id) {
        return null;
    }

    private void buildInsertion(PreparedStatement st, Map<String, Object> data) throws SQLException {
        var contract = (Contract) data.get("contract");

        st.setObject(1, contract.getId());
        st.setObject(2, contract.getPropertyId());
        st.setObject(3, contract.getTenantId());
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

}

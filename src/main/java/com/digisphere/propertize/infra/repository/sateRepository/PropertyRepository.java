package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.IPropertyBuilder;
import com.digisphere.propertize.infra.ErrorHandler.CustomException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PropertyRepository extends StateRepository {
    private final IConnection connection;
    private final IPropertyBuilder propertyBuilder;

    public PropertyRepository(IConnection connection, IPropertyBuilder propertyBuilder) {
        this.connection = connection;
        this.propertyBuilder = propertyBuilder;
    }

    @Override
    public boolean save(Map<String, Object> data) {
        try {
            var stmt = connection.query("INSERT INTO properties " +
                    "(id, owner_cpf, property_type, size, bedroom_count, bathroom_count, has_garage, rent_value, status, construction_date, description, street, number, complement, neighborhood, city, state, postal_code, maintenance_pending) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            buildInsertion(stmt, data);
            var result = stmt.executeUpdate();
            if (result == 0) throw new CustomException("ERROR! NÃO FOI POSSÍVEL SALVAR PROPRIEDADE");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public <T> T getOne(String pk) {
        try {
            var st = connection.query("SELECT * FROM properties WHERE id = ?");
            st.setObject(1, UUID.fromString(pk));
            var response = st.executeQuery();

            if (!response.next()) throw new CustomException("ERROR! NAO ENCONTRADO ");

            rebuild(response);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (T) propertyBuilder.getProperty();
    }

    @Override
    public <T> List<T> getAll() {
        List<T> propertyList = new ArrayList<>();
        try {
            var st = connection.query("SELECT * FROM properties");
            var response = st.executeQuery();

            while (response.next()) {
                rebuild(response);
                propertyList.add((T) propertyBuilder.getProperty());
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return propertyList;
    }

    @Override
    public String update(String pk, Map<String, String> updateData) {
        String column = "";
        if(updateData.containsKey("maintenancePending")) column = "maintenance_pending";
        if(updateData.containsKey("status")) column = "status";
        if(updateData.containsKey("rentValue")) column = "rent_value";

        try {
            var st = connection.query("UPDATE properties SET " + column + " = ? WHERE id = ?");
            changeUpdate(column, st, updateData);
            st.setObject(2, UUID.fromString(pk));
            var response = st.executeUpdate();

            if (response == 0) throw new CustomException("ERRO PROPRIEDADE NAO ATUALIZADA");

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "ITEM " + column.toUpperCase() + " DA PROPRIEDADE ATUALIZAD(A) COM SUCESSO.";
    }

    private void buildInsertion(PreparedStatement stmt, Map<String, Object> data) throws SQLException {
        var property = (Property) data.get("property");

        stmt.setObject(1, property.getId());
        stmt.setObject(2, property.getOwnerCpf());
        stmt.setString(3, property.getType().toString());
        stmt.setDouble(4, property.getSize());
        stmt.setInt(5, property.getBedroomCount());
        stmt.setInt(6, property.getBathroomCount());
        stmt.setBoolean(7, property.getHasGarage());
        stmt.setBigDecimal(8, BigDecimal.valueOf(property.getRentValue()));
        stmt.setString(9, property.getStatus().toString());
        stmt.setDate(10, java.sql.Date.valueOf(property.getConstructionDate()));
        stmt.setString(11, property.getDescription());

        var address = property.getAddress();
        stmt.setString(12, address.getStreet());
        stmt.setString(13, address.getNumber());
        stmt.setString(14, address.getComplement());
        stmt.setString(15, address.getNeighborhood());
        stmt.setString(16, address.getCity());
        stmt.setString(17, address.getState());
        stmt.setString(18, address.getPostalCode());

        stmt.setBoolean(19, property.getMaintenancePending());
    }

    private void rebuild(ResultSet response) throws SQLException {
        propertyBuilder.setId((UUID) response.getObject("id"));
        propertyBuilder.setOwnerCpf(response.getString("owner_cpf"));
        propertyBuilder.setType(PropertyType.valueOf(response.getString("property_type")));
        propertyBuilder.setSize(response.getDouble("size"));
        propertyBuilder.setBedroomCount(response.getInt("bedroom_count"));
        propertyBuilder.setBathroomCount(response.getInt("bathroom_count"));
        propertyBuilder.setHasGarage(response.getBoolean("has_garage"));
        propertyBuilder.setRentValue(Double.valueOf(response.getBigDecimal("rent_value").toString()));
        propertyBuilder.setStatus(PropertyStatus.valueOf(response.getString("status")));
        propertyBuilder.setConstructionDate(response.getDate("construction_date").toLocalDate());
        propertyBuilder.setDescription(response.getString("description"));
        propertyBuilder.setMaintenancePending(response.getBoolean("maintenance_pending"));

        Address address = new Address();
        address.setStreet(response.getString("street"));
        address.setNumber(response.getString("number"));
        address.setComplement(response.getString("complement"));
        address.setNeighborhood(response.getString("neighborhood"));
        address.setCity(response.getString("city"));
        address.setState(response.getString("state"));
        address.setPostalCode(response.getString("postal_code"));

        propertyBuilder.setAddress(address);
    }

    private void changeUpdate(String column, PreparedStatement st,  Map<String, String> updateData) throws SQLException {
        switch (column){
            case "maintenance_pending" -> st.setBoolean(1, Boolean.valueOf(updateData.get("maintenancePending")));
            case "status" -> st.setString(1, PropertyStatus.fromString(updateData.get("status")).name());
            case "rent_value" -> st.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong(updateData.get("rentValue"))));
        }
    }

}

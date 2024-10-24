package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.user.domain.Role;
import com.digisphere.propertize.user.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class UserRepository extends StateRepository {

    private final IConnection connection;
    private final IUserBuilder builder;

    public UserRepository(IConnection connection, IUserBuilder builder) {
        this.connection = connection;
        this.builder = builder;
    }

    @Override
   public boolean save(Map<String, Object> data) {
        try {
            var st = connection.query("INSERT INTO users (id, name, email, cpf, password, phone, active, role) VALUES (?,?,?,?,?,?,?,?)");
            buildInsertion(st, data);
            var result = st.executeUpdate();
            if (result != 0) {
                return true;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public <T> T getOne(String id) {
        try {
            var st = connection.query("SELECT * FROM users WHERE id = ?");
            st.setObject(1, UUID.fromString(id));
            var result = st.executeQuery();

            if (result.next()) {
                rebuild(result);
                return (T) builder.getUser();
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public <T> T getAll() {
        return null;
    }

    @Override
    public <T> T delete(String id) {
        return null;
    }

    private void buildInsertion(PreparedStatement st, Map<String, Object> data) throws SQLException {
       var user = (User) data.get("user");

       st.setObject(1,user.getId());
       st.setString(2, user.getName());
       st.setString(3, user.getEmail());
       st.setString(4, user.getCpf());
       st.setString(5, user.getPassword());
       st.setString(6, user.getPhone());
       st.setBoolean(7, user.getActive());
       st.setString(8, String.valueOf(user.getRole()));
    }

    private void rebuild(ResultSet result) throws SQLException {
        builder.setId((UUID) result.getObject("id"));
        builder.setName(result.getString("name"));
        builder.setEmail(result.getString("email"));
        builder.setCpf(result.getString("cpf"));
        builder.setPhone(result.getString("phone"));
        builder.setRole(Role.valueOf(result.getString("role")));
        builder.setActive(result.getBoolean("active"));
    }
}

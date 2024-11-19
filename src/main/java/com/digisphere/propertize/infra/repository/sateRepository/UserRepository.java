package com.digisphere.propertize.infra.repository.sateRepository;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.ErrorHandler.CustomException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            var st = connection.query("INSERT INTO users (cpf, name, email, password, phone, active, role) VALUES (?,?,?,?,?,?,?)");
            buildInsertion(st, data);
            var result = st.executeUpdate();
            if (result == 0) {
                throw new CustomException("ERRO AO SALVAR USUÁRIO");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public <T> T getOne(String pk) {
        System.out.println(pk);
        try {
            var st = connection.query("SELECT * FROM users WHERE cpf = ?");
            st.setObject(1, pk);
            var result = st.executeQuery();

            if (!result.next()) {
                 throw new CustomException("ERRO AO BUSCAR USUÁRIO");
            }
            rebuild(result);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (T) builder.getUser();
    }

    @Override
    public <T> List<T> getAll() {
        List<T> userList = new ArrayList<>();
        try {
            var st = connection.query("SELECT * FROM users WHERE active IN ('TRUE')");
            var result = st.executeQuery();

            while (result.next()) {
                rebuild(result);
                userList.add((T) builder.getUser());
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public String update(String pk, Map<String, String> updateData) {
        String colmun = "";
        if (updateData.containsKey("password")) colmun = "password";
        if (updateData.containsKey("phone")) colmun = "phone";

        try {
            var st = connection.query("UPDATE users SET " + colmun + " = ? WHERE cpf = ?");
            st.setString(1, updateData.get(colmun));
            st.setString(2, pk);
            var result = st.executeUpdate();
            if (result == 0) throw new CustomException("ERRO AO ATUALIZAR USUÁRIO");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return colmun.toUpperCase() + " DO USUÁRIO COM CPF: " + pk + " ATUALIZADA COM SUCESSO!";
    }

    @Override
    public String delete(String pk) {
        try {
            var st = connection.query("UPDATE users SET active = ? WHERE cpf = ?");
            st.setBoolean(1, false);
            st.setString(2, pk);
            var result = st.executeUpdate();

            if(result == 0) {
                throw new CustomException("ERRO! IMPOSSÍVEL DESATIVAR USUÁRIO COM CPF: " + pk);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "USUÁRIO COM CPF: " + pk + " DESAIVADO COM SUCESSO.";
    }

    private void buildInsertion(PreparedStatement st, Map<String, Object> data) throws SQLException {
       var user = (User) data.get("user");

       st.setString(1,user.getCpf());
       st.setString(2, user.getName());
       st.setString(3, user.getEmail());
       st.setString(4, user.getPassword());
       st.setString(5, user.getPhone());
       st.setBoolean(6, user.getActive());
       st.setString(7, String.valueOf(user.getRole()));
    }

    private void rebuild(ResultSet result) throws SQLException {
        builder.setName(result.getString("name"));
        builder.setEmail(result.getString("email"));
        builder.setCpf(result.getString("cpf"));
        builder.setPhone(result.getString("phone"));
        builder.setRole(Role.valueOf(result.getString("role")));
        builder.setActive(result.getBoolean("active"));
    }
}

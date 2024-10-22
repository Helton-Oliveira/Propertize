package com.digisphere.propertize;

import com.digisphere.propertize.user.useCase.CreateUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    @DisplayName("Deve criar um usuario admnistrador")
    void createAdmin() {

        Map<String, String> input = new HashMap<>();

        input.put("name", "Richard Robson");
        input.put("email", "richard@propertize.com");
        input.put("cpf", "1234567890");
        input.put("password", "senhaForte123");
        input.put("phone", "19 99845577");
        input.put("role", "admin");

        var admin = new CreateUser();
        var result = admin.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("ADMIN");
    }
}

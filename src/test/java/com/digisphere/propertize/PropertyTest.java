package com.digisphere.propertize;

import com.digisphere.propertize.application.property.useCase.CreateProperty;
import jakarta.activation.spi.MailcapRegistryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyTest {

    @Test
    @DisplayName("deve criar uma propriedade")
    void createProperty() {
        Map<String, String> address = new HashMap<>();

        address.put("street", "Main Street");
        address.put("number", "123");
        address.put("complement", "Apt 4B");
        address.put("neighborhood", "Downtown");
        address.put("city", "Springfield");
        address.put("state", "Illinois");
        address.put("postalCode", "62701");


        var creator = new CreateProperty();
        var property = creator.execute("e3b0c442-98fc-1fcf-b26d-8dce9e12eb32",
                "A beautiful family home with a large garden and modern amenities.",
                address,
                "residencial",
                150.0,
                3,
                2,
                true,
                2500,
                "disponivel",
                LocalDate.of(2015, 6, 15));

        assertThat(property.getId()).isNotNull();
    }
}

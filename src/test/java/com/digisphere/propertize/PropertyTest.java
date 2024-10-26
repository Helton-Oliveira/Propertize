package com.digisphere.propertize;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.useCase.CreateProperty;
import com.digisphere.propertize.application.property.useCase.GetAllProperties;
import com.digisphere.propertize.application.property.useCase.GetOneProperty;
import com.digisphere.propertize.application.property.useCase.UpdateProperty;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyTest {

    @Test
    @DisplayName("deve criar uma propriedade")
    void createProperty() {
        Map<String, String> address = new HashMap<>();
        IRepositoryContext repositoryContext = new RepositoryContext();


        address.put("street", "Main Street");
        address.put("number", "143");
        address.put("complement", "not");
        address.put("neighborhood", "Downtown");
        address.put("city", "Springfield");
        address.put("state", "Illinois");
        address.put("postalCode", "34701");

        var creator = new CreateProperty(repositoryContext);
        var property = creator.execute("7193ef6b-bffc-444d-a25e-d7966a5b6b69",
                "A beautiful family home with a large garden and modern amenities.",
                address,
                "residencial",
                180.0,
                4,
                3,
                true,
                4.500,
                "disponivel",
                LocalDate.of(2010, 8, 16));

        assertThat(property.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar uma propriedade")
    void getOneProperty() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneProperty(repositoryContext);
        var property = get.execute("b80801b3-d748-4387-8bf5-65b88605c11c");

        assertThat(property.getId().toString()).isEqualTo("b80801b3-d748-4387-8bf5-65b88605c11c");
    }

    @Test
    @DisplayName("Deve retornar uma lista de propriedades")
    void getAllProperties() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetAllProperties(repositoryContext);
        List<Property> propertyList = get.execute();

        propertyList.forEach(p -> {
            assertThat(p.getId()).isNotNull();
        });
    }

    @Test
    @DisplayName("Deve atualizar valor do aluguel da propriedade")
    void updateRentValue() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        Map<String, String> data = new HashMap<>();

        data.put("rentValue", "3000");

        var updated = new UpdateProperty(repositoryContext);
        String newProperty = updated.execute("2788e2c9-233a-4f8c-ad42-6bb4c0b22546", data);

       assertThat(newProperty).isEqualTo("ITEM RENT_VALUE DA PROPRIEDADE ATUALIZAD(A) COM SUCESSO.");
    }
}

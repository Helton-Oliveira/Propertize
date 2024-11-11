package com.digisphere.propertize;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.useCase.CreateProperty;
import com.digisphere.propertize.application.property.useCase.GetAllProperties;
import com.digisphere.propertize.application.property.useCase.GetOneProperty;
import com.digisphere.propertize.application.property.useCase.UpdateProperty;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyTest {

    @Test
    @DisplayName("deve criar uma propriedade")
    void createProperty() {
        Map<String, String> data = new HashMap<>();
        IRepositoryContext repositoryContext = new RepositoryContext();
        ITemplateMethod abstractDirector = new TemplateMethodDirector();

        data.put("ownerCpf", "71686187092");
        data.put("description", "A beautiful family home with a large garden and modern amenities.");
        data.put("street", "Main Street");
        data.put("number", "143");
        data.put("complement", "not");
        data.put("neighborhood", "Downtown");
        data.put("city", "Springfield");
        data.put("state", "Illinois");
        data.put("postalCode", "34701");
        data.put("type", "residencial");
        data.put("size", "180.0");
        data.put("bedRoomCount", "4");
        data.put("bathRoomCount", "3");
        data.put("hasGarage", "true");
        data.put("rentValue", "4500.00");
        data.put("status", "disponivel");
        data.put("constructionDate", "2010-08-16");

        var creator = new CreateProperty(repositoryContext, abstractDirector);
        var property = creator.execute(data);

        assertThat(property.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar uma propriedade")
    void getOneProperty() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneProperty(repositoryContext);
        var property = get.execute("647460b5-fb18-4329-9343-9b539b789779");

        assertThat(property.getId().toString()).isEqualTo("647460b5-fb18-4329-9343-9b539b789779");
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
        String newProperty = updated.execute("647460b5-fb18-4329-9343-9b539b789779", data);

       assertThat(newProperty).isEqualTo("ITEM RENT_VALUE DA PROPRIEDADE ATUALIZAD(A) COM SUCESSO.");
    }
}

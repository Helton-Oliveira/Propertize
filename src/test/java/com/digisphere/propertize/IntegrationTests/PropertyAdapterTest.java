package com.digisphere.propertize.IntegrationTests;

import com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter.IPropertyAdapter;
import com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter.PropertyAdapter;
import com.digisphere.propertize.adapter.dtos.property.CreatePropertyDto;
import com.digisphere.propertize.adapter.dtos.property.OutputProperty;
import com.digisphere.propertize.adapter.dtos.property.PropertyReferenceDto;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
import com.digisphere.propertize.application.property.businessRules.CheckIfTheOwnerIsActive;
import com.digisphere.propertize.application.property.businessRules.IPropertyRules;
import com.digisphere.propertize.application.property.businessRules.RentalValueMustBeGreatherThanZero;
import com.digisphere.propertize.application.property.useCase.CreateProperty;
import com.digisphere.propertize.application.property.useCase.GetOneProperty;
import com.digisphere.propertize.application.property.useCase.UpdateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.ICreateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetOneProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IUpdateProperty;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyAdapterTest {

    private ICreateProperty createProperty;
    private IGetOneProperty getOneProperty;
    private IUpdateProperty updateProperty;
    private IRepositoryContext repositoryContext;
    private ITemplateMethod templateMethod;
    private IPropertyAdapter propertyAdapter;
    private List<IPropertyRules> propertyRules = new ArrayList<>();

    @BeforeEach
    public void setup() {
        repositoryContext = new RepositoryContext();
        templateMethod = new TemplateMethodDirector();
        propertyRules.add(new CheckIfTheOwnerIsActive(repositoryContext));
        propertyRules.add(new RentalValueMustBeGreatherThanZero());
        createProperty = new CreateProperty(repositoryContext, templateMethod, propertyRules);
        getOneProperty = new GetOneProperty(repositoryContext);
        updateProperty = new UpdateProperty(repositoryContext);

        propertyAdapter = new PropertyAdapter(createProperty, getOneProperty, updateProperty);
    }

    @Test
    @DisplayName("Deve adaptar a requisicao de criar uma propriedade")
    void adapterToCreateProperties() {
        var dto = new CreatePropertyDto(
                "71686187092",
                "Apartamento novo com excelente localização.",
                "residencial",
                "75.5",
                "2",
                "2",
                "true",
                "1500.0",
                "2020-05-15",
                "Rua das Flores",
                "123",
                "Apto 101",
                "Centro",
                "São Paulo",
                "SP",
                "01000000"
        );
        OutputProperty property = propertyAdapter.adaptCreatePropertyRequest(dto);

        assertThat(property.id()).isNotNull();
    }

    @Test
    @DisplayName("Deve adaptar a requisicao de atualizar valor do aluguel de uma propriedade")
    void adapterToUpdatePropertyRentValue() {
        var dto = new PropertyReferenceDto("647460b5-fb18-4329-9343-9b539b789779", "1150");
        String response = propertyAdapter.adaptUpdatePropertyRequest(dto);

        assertThat(response).isEqualTo("ITEM RENT_VALUE DA PROPRIEDADE ATUALIZAD(A) COM SUCESSO.");
    }

    @Test
    @DisplayName("Deve adaptar a requisicao de buscar uma propriedade")
    void adapterToFindOneProperty() {
        var dto = new PropertyReferenceDto("6f1e1be7-49dd-4cfb-82c9-8a9348f91946", "1150");
        OutputProperty property = propertyAdapter.adaptFindPropertyRequest(dto);

        assertThat(property.id()).isEqualTo("6f1e1be7-49dd-4cfb-82c9-8a9348f91946");
    }

}

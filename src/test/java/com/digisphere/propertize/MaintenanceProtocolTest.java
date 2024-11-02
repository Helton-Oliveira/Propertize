package com.digisphere.propertize;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.OpenProtocol;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MaintenanceProtocolTest {

    @Test
    @DisplayName("deve abrir um chamado para manutencao de propriedade")
    void openTicket() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        Map<String, String> input = new HashMap<>();

        input.put("propertyId", "b80801b3-d748-4387-8bf5-65b88605c11c");
        input.put("tenantId", "e921f072-624e-4eac-ac87-8ae532bcf7d8");
        input.put("MaintenanceDetails", "Vazamento do encanamento da caixa d`gua");

        var openProtocol = new OpenProtocol(repositoryContext);
        MaintenanceProtocol protocol = openProtocol.execute(input);

        assertThat(protocol.getStatus().name()).isEqualTo("OPEN");
    }
}

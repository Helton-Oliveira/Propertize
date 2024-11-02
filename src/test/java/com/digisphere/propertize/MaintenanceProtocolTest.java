package com.digisphere.propertize;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;
import com.digisphere.propertize.application.maintenance.useCase.GetOneMaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.OpenProtocol;
import com.digisphere.propertize.application.maintenance.useCase.PickUpOpenMaintenanceProtocols;
import com.digisphere.propertize.application.maintenance.useCase.UpdateMaintenanceStatus;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
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
        input.put("MaintenanceDetails", "Infiltracao da chuva");

        var openProtocol = new OpenProtocol(repositoryContext);
        MaintenanceProtocol protocol = openProtocol.execute(input);

        assertThat(protocol.getStatus().name()).isEqualTo("OPEN");
    }

    @Test
    @DisplayName("deve buscar um protocolo de manutencao")
    void getTicket() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneMaintenanceProtocol(repositoryContext);
        var protocol = get.execute("c8d51e7c-fc18-4291-bf21-f8b2d78e8e63");

        assertThat(protocol.getProtocol().toString()).isEqualTo("c8d51e7c-fc18-4291-bf21-f8b2d78e8e63");
    }

    @Test
    @DisplayName("deve buscar um todos os protocolos de manutencao com chamados em aberto")
    void getAllTickets() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var getAll = new PickUpOpenMaintenanceProtocols(repositoryContext);
        List<MaintenanceProtocol> protocols = getAll.execute();

        protocols.forEach(p -> {
            assertThat(p.getStatus().toString()).isNotEqualTo(MaintenanceStatus.CANCELED.name());
            assertThat(p.getStatus().toString()).isNotEqualTo(MaintenanceStatus.COMPLETED.name());
        });
    }

    @Test
    @DisplayName("Deve alterar o status do protocolo")
    void updateStatusMaintenanceProtocolForCanceled() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        Map<String ,String> data = new HashMap<>();

        data.put("status", "cancelado");
        data.put("reason", "foi verificado e não consta vazamento no encanamento, era apenas uma telha solta");

        var updatedStatus = new UpdateMaintenanceStatus(repositoryContext);
        var column = updatedStatus.execute("c8d51e7c-fc18-4291-bf21-f8b2d78e8e63", data);

        assertThat(column).isEqualTo("STATUS DO PROTOCOLO c8d51e7c-fc18-4291-bf21-f8b2d78e8e63 ATUALIZADO PARA CANCELADO");
    }

    @Test
    @DisplayName("Deve alterar o status do protocolo para em progresso")
    void updateStatusMaintenanceProtocolForInProgress() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        Map<String ,String> data = new HashMap<>();

        data.put("status", "em progresso");

        var updatedStatus = new UpdateMaintenanceStatus(repositoryContext);
        var column = updatedStatus.execute("0da76e41-44e2-49d0-87a5-5abf51e0c000", data);

        assertThat(column).isEqualTo("STATUS DO PROTOCOLO 0da76e41-44e2-49d0-87a5-5abf51e0c000 ATUALIZADO PARA EM PROGRESSO");
    }
}

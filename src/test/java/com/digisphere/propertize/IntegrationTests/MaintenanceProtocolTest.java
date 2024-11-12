package com.digisphere.propertize.IntegrationTests;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
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
        ITemplateMethod abstractDirector = new TemplateMethodDirector();
        Map<String, String> input = new HashMap<>();

        input.put("propertyId", "647460b5-fb18-4329-9343-9b539b789779");
        input.put("tenantCpf", "07416672074");
        input.put("MaintenanceDetails", "Fiacao velha");

        var openProtocol = new OpenProtocol(repositoryContext, abstractDirector);
        MaintenanceProtocol protocol = openProtocol.execute(input);

        assertThat(protocol.getStatus().name()).isEqualTo("OPEN");
    }

    @Test
    @DisplayName("deve buscar um protocolo de manutencao")
    void getTicket() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneMaintenanceProtocol(repositoryContext);
        var protocol = get.execute("ebdd733e-a622-4357-b5ce-c6d24bed2ffc");

        assertThat(protocol.getProtocol().toString()).isEqualTo("ebdd733e-a622-4357-b5ce-c6d24bed2ffc");
    }

    @Test
    @DisplayName("deve buscar todos os protocolos de manutencao com chamados em aberto")
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
        var column = updatedStatus.execute("ebdd733e-a622-4357-b5ce-c6d24bed2ffc", data);

        assertThat(column).isEqualTo("STATUS DO PROTOCOLO ebdd733e-a622-4357-b5ce-c6d24bed2ffc ATUALIZADO PARA CANCELADO");
    }

    @Test
    @DisplayName("Deve alterar o status do protocolo para em progresso")
    void updateStatusMaintenanceProtocolForInProgress() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        Map<String ,String> data = new HashMap<>();

        data.put("status", "em progresso");

        var updatedStatus = new UpdateMaintenanceStatus(repositoryContext);
        var column = updatedStatus.execute("308c0f04-59d8-423b-bba9-c98dddde6e2f", data);

        assertThat(column).isEqualTo("STATUS DO PROTOCOLO 308c0f04-59d8-423b-bba9-c98dddde6e2f ATUALIZADO PARA EM PROGRESSO");
    }
}

package com.digisphere.propertize;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.useCase.CreateContract;
import com.digisphere.propertize.application.contract.useCase.GetAllContracts;
import com.digisphere.propertize.application.contract.useCase.GetOneContract;
import com.digisphere.propertize.application.contract.useCase.UpdateContract;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractTest {

    @Test
    @DisplayName("Deve criar um contrato")
    void createContract() {
        Map<String, String> data = new HashMap<>();
        data.put("propertyId", "647460b5-fb18-4329-9343-9b539b789779");
        data.put("tenantCpf", "07416672074");
        data.put("period", "2");
        data.put("monthlyRent", "2500.00");
        data.put("paymentDueDay", "5");
        data.put("securityDeposit", "7500.00");
        IRepositoryContext repositoryContext = new RepositoryContext();
        ITemplateMethod abstractDirector = new TemplateMethodDirector();

        var createContract = new CreateContract(repositoryContext, abstractDirector);
        Contract contract  = createContract.execute(data);

        assertThat(contract.getId()).isNotNull();
        assertThat(contract.getTerminationFee()).isEqualTo(0.05);
        assertThat(contract.getEndDate().getYear()).isEqualTo(2026);

    }

    @Test
    @DisplayName("Deve buscar 1 contrato")
    void getOneContract() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneContract(repositoryContext);
        Contract contract = get.execute("77efa89a-a949-4e6d-9330-812a23ec3fe7");

        assertThat(contract.getId().toString()).isEqualTo("77efa89a-a949-4e6d-9330-812a23ec3fe7");

    }

    @Test
    @DisplayName("Deve buscar todos os contratos")
    void getAllContracts() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var getAll = new GetAllContracts(repositoryContext);
        List<Contract> contract = getAll.execute();

        assertThat(contract.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Deve atualizar um contrato")
    void updateContract() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        Map<String, String> data = new HashMap<>();
        data.put("terminationDate","2024-10-28");
        data.put("terminationReason", "optou por mudar para outro local");

        var update = new UpdateContract(repositoryContext);
        var contract = update.execute("a922bd54-0d01-4609-8559-d67163ec6daf", data);

        assertThat(contract).isEqualTo("CONTRATO ATUALIZADO NA COLUNA TERMINATIONDATE TERMINATIONREASON");
    }
}

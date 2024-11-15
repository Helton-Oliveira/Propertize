package com.digisphere.propertize.IntegrationTests;

import com.digisphere.propertize.application.contract.businessRules.CheckContractCreationDate;
import com.digisphere.propertize.application.contract.businessRules.IContractRules;
import com.digisphere.propertize.application.contract.businessRules.TenantContractValidator;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractTest {

    @Test
    @DisplayName("Deve criar um contrato")
    void createContract() {
        Map<String, String> data = new HashMap<>();
        data.put("propertyId", "3f1ebd61-ad29-4be2-bd37-bcf3b1a9dd6d");
        data.put("tenantCpf", "43651026051");
        data.put("period", "2");
        data.put("paymentDueDay", "5");
        data.put("securityDeposit", "7500.00");
        IRepositoryContext repositoryContext = new RepositoryContext();
        ITemplateMethod abstractDirector = new TemplateMethodDirector();
        List<IContractRules> contractRules = new ArrayList<>();
        contractRules.add(new TenantContractValidator());
        contractRules.add(new CheckContractCreationDate());

        var createContract = new CreateContract(repositoryContext, abstractDirector, contractRules);
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
        var contract = update.execute("77efa89a-a949-4e6d-9330-812a23ec3fe7", data);

        assertThat(contract).isEqualTo("CONTRATO ATUALIZADO NA COLUNA CONTRACTSTATUS TERMINATIONDATE TERMINATIONREASON");
    }
}

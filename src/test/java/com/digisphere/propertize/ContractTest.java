package com.digisphere.propertize;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.useCase.CreateContract;
import com.digisphere.propertize.application.contract.useCase.GetAllContracts;
import com.digisphere.propertize.application.contract.useCase.GetOneContract;
import com.digisphere.propertize.application.contract.useCase.UpdateContract;
import com.digisphere.propertize.application.director.bridgePattern.implementations.AbstractDirector;
import com.digisphere.propertize.application.director.bridgePattern.abstractions.IAbstractDirector;
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
        data.put("propertyId", "b80801b3-d748-4387-8bf5-65b88605c11c");
        data.put("tenantId", "06dd53ec-d492-455f-b632-b827794d3d85");
        data.put("period", "2");
        data.put("monthlyRent", "2500.00");
        data.put("paymentDueDay", "5");
        data.put("securityDeposit", "7500.00");
        IRepositoryContext repositoryContext = new RepositoryContext();
        IAbstractDirector abstractDirector = new AbstractDirector();

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
        Contract contract = get.execute("fd3f2a74-6c6f-458d-aa27-9772bb25e02e");

        assertThat(contract.getId().toString()).isEqualTo("fd3f2a74-6c6f-458d-aa27-9772bb25e02e");

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

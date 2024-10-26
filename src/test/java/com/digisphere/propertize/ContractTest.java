package com.digisphere.propertize;

import com.digisphere.propertize.application.contract.Contract;
import com.digisphere.propertize.application.contract.useCase.CreateContract;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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

        var createContract = new CreateContract(repositoryContext);
        Contract contract  = createContract.execute(data);

        assertThat(contract.getId()).isNotNull();
        assertThat(contract.getTerminationFee()).isEqualTo(0.05);
        assertThat(contract.getEndDate().getYear()).isEqualTo(2026);

    }
}

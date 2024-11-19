package com.digisphere.propertize.application.contract.businessRules;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.infra.ErrorHandler.CustomException;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.time.LocalDate;
import java.util.List;

public class CheckContractCreationDate implements IContractRules {

    @Override
    public void valid(Contract contract, IRepositoryContext repositoryContext) {
        repositoryContext.changeState("contracts");
        List<Contract> contracts = repositoryContext.getAll();
        LocalDate newContractStartDate = contract.getStartDate();

        if (!contracts.isEmpty()) {
            List<Contract> contractFiltered = contracts.stream()
                    .filter(c -> c.getTenantCpf().equals(contract.getTenantCpf()) || c.getPropertyId().equals(contract.getPropertyId()))
                    .toList();

            contractFiltered.forEach(c -> {
                if (newContractStartDate.isBefore(c.getEndDate())) throw new CustomException("ERRO! PROPRIEDADE OU INQUILINO JÁ EXISTENTE EM OUTRO CONTRATO AINDA VÁLIDO");
            });
        }
        repositoryContext.changeState("properties");
    }
}

package com.digisphere.propertize.adapter.adapterPattern.contractAdapter;

import com.digisphere.propertize.adapter.dtos.contract.ContractIdRequestDto;
import com.digisphere.propertize.adapter.dtos.contract.ContractUpdateRequestDto;
import com.digisphere.propertize.adapter.dtos.contract.CreateContractDto;
import com.digisphere.propertize.adapter.dtos.contract.OutputContractDto;
import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.useCase.interfaces.ICreateContract;
import com.digisphere.propertize.application.contract.useCase.interfaces.IGetOneContract;
import com.digisphere.propertize.application.contract.useCase.interfaces.IUpdateContract;

import java.util.Map;

public class ContractRequestAdapter implements IContractRequestAdapter {

    private final ICreateContract createContract;
    private final IGetOneContract getOneContract;
    private final IUpdateContract updateContract;

    public ContractRequestAdapter(ICreateContract createContract, IGetOneContract getOneContract, IUpdateContract updateContract) {
        this.createContract = createContract;
        this.getOneContract = getOneContract;
        this.updateContract = updateContract;
    }

    @Override
    public OutputContractDto adaptCreateContractRequest(CreateContractDto dto) {
        Map<String, String> data = Map.of(
                "propertyId", dto.propertyId(),
                "tenantCpf", dto.tenantCpf(),
                "period", dto.period(),
                "paymentDueDay", dto.paymentDueDay(),
                "securityDeposit", dto.securityDeposit()
        );
        var contract = createContract.execute(data);

        return output(contract);
    }

    @Override
    public OutputContractDto adaptFindContractRequest(ContractIdRequestDto dto) {
        var contract = getOneContract.execute(dto.id());
        return output(contract);
    }

    @Override
    public String adaptUpdateContractRequest(ContractUpdateRequestDto dto) {
        Map<String, String> newData = Map.of(
                "terminationDate", dto.terminationDate(),
                "terminationReason", dto.terminationReason()
        );

        return updateContract.execute(dto.id(), newData);
    }

    private OutputContractDto output(Contract contract) {
        return new OutputContractDto(
                contract.getId().toString(),
                contract.getPropertyId().toString(),
                contract.getTenantCpf(),
                contract.getStartDate().toString(),
                contract.getEndDate().toString(),
                contract.getMonthlyRent().toString(),
                contract.getPaymentDueDay().toString(),
                contract.getSecurityDeposit().toString(),
                contract.getStatus().toString()
        );
    }
}

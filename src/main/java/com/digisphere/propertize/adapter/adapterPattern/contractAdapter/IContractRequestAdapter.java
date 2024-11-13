package com.digisphere.propertize.adapter.adapterPattern.contractAdapter;

import com.digisphere.propertize.adapter.dtos.contract.ContractIdRequestDto;
import com.digisphere.propertize.adapter.dtos.contract.ContractUpdateRequestDto;
import com.digisphere.propertize.adapter.dtos.contract.CreateContractDto;
import com.digisphere.propertize.adapter.dtos.contract.OutputContractDto;

public interface IContractRequestAdapter {
    OutputContractDto adaptCreateContractRequest(CreateContractDto dto);
    OutputContractDto adaptFindContractRequest(ContractIdRequestDto dto);
    String adaptUpdateContractRequest(ContractUpdateRequestDto dto);
}

package com.digisphere.propertize.adapter.controller;

import com.digisphere.propertize.adapter.adapterPattern.contractAdapter.IContractRequestAdapter;
import com.digisphere.propertize.adapter.dtos.contract.ContractIdRequestDto;
import com.digisphere.propertize.adapter.dtos.contract.ContractUpdateRequestDto;
import com.digisphere.propertize.adapter.dtos.contract.CreateContractDto;
import com.digisphere.propertize.adapter.dtos.contract.OutputContractDto;
import com.digisphere.propertize.application.contract.useCase.interfaces.IGetAllContracts;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final IContractRequestAdapter contractRequestAdapter;
    private final IGetAllContracts getAllContracts;

    public ContractController(IContractRequestAdapter contractRequestAdapter, IGetAllContracts getAllContracts) {
        this.contractRequestAdapter = contractRequestAdapter;
        this.getAllContracts = getAllContracts;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createContract(@RequestBody @Valid CreateContractDto dto, UriComponentsBuilder uriBuilder) {
        OutputContractDto contract = contractRequestAdapter.adaptCreateContractRequest(dto);
        var uri = uriBuilder.path("contracts/{id}").buildAndExpand(contract.id()).toUri();
        return ResponseEntity.created(uri).body(contract);
    }

    @GetMapping
    public ResponseEntity<List<OutputContractDto>> getAllContracts() {
        List<OutputContractDto> outputContractDtos = getAllContracts.execute().stream()
                .map(c -> new OutputContractDto(
                        c.getId().toString(),
                        c.getPropertyId().toString(),
                        c.getTenantCpf(),
                        c.getStartDate().toString(),
                        c.getEndDate().toString(),
                        c.getMonthlyRent().toString(),
                        c.getPaymentDueDay().toString(),
                        c.getSecurityDeposit().toString(),
                        c.getStatus().toString()
                ))
                .toList();

        return ResponseEntity.ok(outputContractDtos);
    }

    @GetMapping("/one")
    public ResponseEntity<OutputContractDto> getOneContract(@RequestBody @Valid ContractIdRequestDto dto) {
        OutputContractDto contract = contractRequestAdapter.adaptFindContractRequest(dto);
        return ResponseEntity.ok(contract);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateContract(@RequestBody @Valid ContractUpdateRequestDto dto) {
        String reponse = contractRequestAdapter.adaptUpdateContractRequest(dto);
        return ResponseEntity.ok(reponse);
    }
}


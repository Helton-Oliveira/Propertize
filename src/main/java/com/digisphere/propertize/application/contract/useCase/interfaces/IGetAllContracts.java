package com.digisphere.propertize.application.contract.useCase.interfaces;

import com.digisphere.propertize.application.contract.domain.Contract;

import java.util.List;

public interface IGetAllContracts {
    List<Contract> execute();
}

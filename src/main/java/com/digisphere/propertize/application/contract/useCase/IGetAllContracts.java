package com.digisphere.propertize.application.contract.useCase;

import com.digisphere.propertize.application.contract.Contract;

import java.util.List;

public interface IGetAllContracts {
    List<Contract> execute();
}

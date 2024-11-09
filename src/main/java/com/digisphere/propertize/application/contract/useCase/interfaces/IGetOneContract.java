package com.digisphere.propertize.application.contract.useCase.interfaces;

import com.digisphere.propertize.application.contract.domain.Contract;

public interface IGetOneContract {
    Contract execute(String id);
}

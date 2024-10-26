package com.digisphere.propertize.application.contract.useCase;

import com.digisphere.propertize.application.contract.Contract;

public interface IGetOneContract {
    Contract execute(String id);
}

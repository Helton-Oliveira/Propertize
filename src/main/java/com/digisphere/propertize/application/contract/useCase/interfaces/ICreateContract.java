package com.digisphere.propertize.application.contract.useCase.interfaces;

import com.digisphere.propertize.application.contract.domain.Contract;

import java.util.Map;

public interface ICreateContract {
    Contract execute(Map<String, String> data);
}

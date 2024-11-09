package com.digisphere.propertize.application.contract.useCase.interfaces;

import java.util.Map;

public interface IUpdateContract {
    String execute(String id, Map<String, String> data);
}

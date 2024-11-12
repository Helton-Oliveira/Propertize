package com.digisphere.propertize.application.user.useCase.strategyPattern.context;

import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.strategyPattern.strategy.CreateOwner;
import com.digisphere.propertize.application.user.useCase.strategyPattern.strategy.IStrategy;
import com.digisphere.propertize.application.user.useCase.strategyPattern.strategy.CreateAdmin;
import com.digisphere.propertize.application.user.useCase.strategyPattern.strategy.CreateTenant;

import java.util.Map;

public class Context implements IContext{
    private IStrategy strategy;

    @Override
    public void setStrategy(Map<String, String> data) {
        if (data.isEmpty()) throw new RuntimeException("ERRO! PARÂMETROS PARA CONTRUÇÃO DO USUÁRIO ERRADOS OU NULOS");
        if (data.get("role").equalsIgnoreCase("tenant")) this.strategy = new CreateTenant();
        if (data.get("role").equalsIgnoreCase("owner")) this.strategy = new CreateOwner();
        if (data.get("role").equalsIgnoreCase("admin")) this.strategy = new CreateAdmin();
    }

    @Override
    public User executeStrategy(Map<String, String> data, IUserBuilder userBuilder) {
        return strategy.execute(data, userBuilder);
    }
}

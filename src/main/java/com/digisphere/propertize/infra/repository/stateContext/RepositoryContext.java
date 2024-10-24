package com.digisphere.propertize.infra.repository.stateContext;

import com.digisphere.propertize.adapter.connection.PostgreSqlAdapter;
import com.digisphere.propertize.infra.repository.sateRepository.StateRepository;
import com.digisphere.propertize.infra.repository.sateRepository.UserRepository;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;

import java.util.Map;

public class RepositoryContext implements IRepositoryContext {
    private StateRepository stateRepository;

    public void changeState(String state) {
        if (state.equalsIgnoreCase("users")) this.stateRepository = new UserRepository(new PostgreSqlAdapter(), new UserBuilder());
    }

    @Override
    public boolean save(Map<String, Object> data) {
        return stateRepository.save(data);
    }

    @Override
    public <T> T getOne(String id) {
       return stateRepository.getOne(id);
    }

    @Override
    public <T> T getAll() {
        return stateRepository.getAll();

    }

    @Override
    public <T> T delete(String id) {
        return stateRepository.delete(id);
    }

}

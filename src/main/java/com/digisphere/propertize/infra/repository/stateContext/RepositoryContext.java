package com.digisphere.propertize.infra.repository.stateContext;

import com.digisphere.propertize.adapter.connection.PostgreSqlAdapter;
import com.digisphere.propertize.infra.repository.sateRepository.StateRepository;
import com.digisphere.propertize.infra.repository.sateRepository.UserRepository;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;

import java.util.List;
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
    public String update(String id, Map<String, String> data) {
        return stateRepository.update(id, data);
    }

    @Override
    public <T> List<T> getAll() {
        return stateRepository.getAll();

    }

    @Override
    public String delete(String id) {
        return stateRepository.delete(id);
    }

}

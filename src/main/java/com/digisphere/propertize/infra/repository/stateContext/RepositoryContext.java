package com.digisphere.propertize.infra.repository.stateContext;

import com.digisphere.propertize.adapter.connection.IConnection;
import com.digisphere.propertize.adapter.connection.PostgreSqlAdapter;
import com.digisphere.propertize.application.contract.contractBuilder.ContractBuilder;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.MaintenanceBuilder;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.PropertyBuilder;
import com.digisphere.propertize.infra.repository.sateRepository.*;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;

import java.util.List;
import java.util.Map;

public class RepositoryContext implements IRepositoryContext {
    private StateRepository stateRepository;
    private final IConnection connection = new PostgreSqlAdapter();

    public void changeState(String state) {
        if (state.equalsIgnoreCase("users")) this.stateRepository = new UserRepository(connection, new UserBuilder());
        if (state.equalsIgnoreCase("properties")) this.stateRepository = new PropertyRepository(connection, new PropertyBuilder());
        if (state.equalsIgnoreCase("contracts")) this.stateRepository = new ContractRepository(connection, new ContractBuilder());
        if (state.equalsIgnoreCase("maintenanceProtocols")) this.stateRepository = new MaintenanceProtocolRepository(connection, new MaintenanceBuilder());
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

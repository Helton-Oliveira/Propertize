package com.digisphere.propertize.infra.repository.stateContext;

import java.util.Map;

public interface IRepositoryContext {
    void changeState(String state);
    boolean save(Map<String, Object> data);
    <T> T getOne(String id );
    <T> T getAll();
    <T> T delete(String id);
}

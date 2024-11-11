package com.digisphere.propertize.infra.repository.stateContext;

import java.util.List;
import java.util.Map;

public interface IRepositoryContext {
    void changeState(String state);
    boolean save(Map<String, Object> data);
    <T> T getOne(String pk);
    String update(String pk, Map<String, String> data);
    <T> List<T> getAll();
    String delete(String pk);
}

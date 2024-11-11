package com.digisphere.propertize.infra.repository.sateRepository;

import java.util.List;
import java.util.Map;

public abstract class StateRepository {
    public abstract boolean save(Map<String, Object> data);
    public abstract <T> T getOne(String pk);
    public abstract <T> List<T> getAll();
    public String delete(String pk){return null;}
    public abstract String update(String pk, Map<String, String> updateData);
}

package com.digisphere.propertize.infra.repository.sateRepository;

import java.util.List;
import java.util.Map;

public abstract class StateRepository {
    public abstract boolean save(Map<String, Object> data);
    public abstract <T> T getOne(String id );
    public abstract <T> List<T> getAll();
    public abstract String delete(String id);
    public String update(String id, Map<String, String> updateData){ return null; }
}

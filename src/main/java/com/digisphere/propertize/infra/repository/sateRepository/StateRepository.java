package com.digisphere.propertize.infra.repository.sateRepository;

import java.util.Map;

public abstract class StateRepository {
    public abstract boolean save(Map<String, Object> data);
    public abstract <T> T getOne(String id );
    public abstract <T> T getAll();
    public abstract <T> T delete(String id);
}

package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.transaction.entity.Patron;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emerald on 8/26/2016.
 * patron dao
 */
public class DaoPatron extends DaoCommon<Patron> implements Dao<Patron> {
    DaoPatron() {
        // package access
    }

    @Override
    public Patron save(Patron entity) {
        return saveBase(entity);
    }

    @Override
    public void update(Patron entity) {
        updateBase(entity);
    }

    @Override
    public void delete(Patron entity) {
        deleteBase(entity);
    }

    @Override
    public Patron findById(Serializable id) {
        return findOne(Patron.FIND_BY_ID, "id", id);
    }

    public List<Patron> findByName(String name) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("name", "%" + name + "%");
        return findMatching(Patron.FIND_BY_NAME, parmMap);
    }
}

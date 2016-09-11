package com.ohmyapp.library.transaction.dao;

import java.io.Serializable;

/**
 * Created by Emerald on 8/26/2016.
 * Media dao
 */
public interface Dao<T> {
    /**
     * save entity
     * @param entity    entity
     * @return          saved entity
     */
    T save(T entity);

    /**
     * update entity
     * @param entity    entity
     */
    void update(T entity);

    /**
     * delete entity
     * @param entity entity
     */
    void delete(T entity);

    /**
     * find one entity
     * @param id    id
     * @return      entity
     */
    T findById(Serializable id);
}

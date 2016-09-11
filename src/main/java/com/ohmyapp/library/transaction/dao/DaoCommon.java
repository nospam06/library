package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.transaction.TransactionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Emerald on 8/26/2016.
 * common dao methods
 */
abstract class DaoCommon<T> {
    private static final Logger LOGGER = Logger.getLogger(DaoCommon.class);

    T saveBase(T entity) {
        Session session = TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        Serializable pk = session.save(entity);
        LOGGER.debug("pk = " + pk);
        return entity;
    }

    void updateBase(T entity) {
        Session session = TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        session.saveOrUpdate(entity);
    }

    void deleteBase(T entity) {
        Session session = TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        session.delete(entity);
    }

    @SuppressWarnings(value = "unchecked")
    T findOne(String namedQuery, String parm, Serializable id) {
        Query query = createQuery(namedQuery);
        query.setParameter(parm, id);
        List resultList = query.getResultList();
        TransactionFactory.commit();
        if (resultList == null || resultList.isEmpty()) {
            return null;
        } else {
            return (T) resultList.get(0);
        }
    }

    @SuppressWarnings(value = "unchecked")
    List<T> findMatching(String namedQuery, Map<String, Serializable> parmMap) {
        Query query = createQuery(namedQuery);
        for (Map.Entry<String, Serializable> parm : parmMap.entrySet()) {
            query.setParameter(parm.getKey(), parm.getValue());
        }
        List resultList = query.getResultList();
        TransactionFactory.commit();
        return resultList;
    }

    private Query createQuery(String namedQuery) {
        Session session = TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        return session.createNamedQuery(namedQuery);
    }
}

package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.service.LibraryException;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Emerald on 8/28/2016.
 * Dao factory
 */
public class DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);
    private static final ConcurrentHashMap<String, Dao> daoMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends Dao> T getDao(Class<? extends Dao> clazz) throws LibraryException {
        Dao dao = daoMap.get(clazz.getSimpleName());
        if (dao == null) {
            try {
                dao = (T) Class.forName(clazz.getName()).newInstance();
                daoMap.put(clazz.getSimpleName(), dao);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                LOGGER.error(e.getMessage(), e);
                throw new LibraryException(e.getMessage(), e);
            }
        }
        return (T) dao;
    }
}

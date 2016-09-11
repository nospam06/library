package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.transaction.entity.MediaCopy;
import com.ohmyapp.library.transaction.entity.Patron;
import com.ohmyapp.library.transaction.entity.Record;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emerald on 8/26/2016.
 * record dao
 */
public class DaoRecord extends DaoCommon<Record> implements Dao<Record> {
    DaoRecord() {
        // package access
    }

    @Override
    public Record save(Record entity) {
        return saveBase(entity);
    }

    @Override
    public void update(Record entity) {
        updateBase(entity);
    }

    @Override
    public void delete(Record entity) {
        deleteBase(entity);
    }

    @Override
    public Record findById(Serializable id) {
        return findOne(Record.FIND_BY_ID, "id", id);
    }

    public List<Record> findByPatron(Patron patron) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("patron", patron);
        return super.findMatching(Record.FIND_BY_PATRON, parmMap);
    }

    public List<Record> findByMediaCopy(MediaCopy mediaCopy) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("mediaCopy", mediaCopy);
        parmMap.put("returned", false);
        return super.findMatching(Record.FIND_BY_MEDIACOPY, parmMap);
    }
}

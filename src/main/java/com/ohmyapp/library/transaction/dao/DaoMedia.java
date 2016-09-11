package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.transaction.entity.Media;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emerald on 8/26/2016.
 * media dao
 */
public class DaoMedia extends DaoCommon<Media> implements Dao<Media> {
    DaoMedia() {
    // package access
    }

    @Override
    public Media save(Media entity) {
        return saveBase(entity);
    }

    @Override
    public void update(Media entity) {
        updateBase(entity);
    }

    @Override
    public void delete(Media entity) {
        deleteBase(entity);
    }

    @Override
    public Media findById(Serializable id) {
        return findOne(Media.FIND_BY_ID, "id", id);
    }

    public List<Media> findByTitle(String title) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("title", "%" + title + "%");
        return super.findMatching(Media.FIND_BY_TITLE, parmMap);
    }

    public List<Media> findByAuthor(String author) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("author", "%" + author + "%");
        return super.findMatching(Media.FIND_BY_AUTHOR, parmMap);
    }

    public List<Media> findByFormat(String format) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("format", "%" + format + "%");
        return super.findMatching(Media.FIND_BY_FORMAT, parmMap);
    }

    public List<Media> findByGenre(String genre) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("genre", "%" + genre + "%");
        return super.findMatching(Media.FIND_BY_GENRE, parmMap);
    }
}

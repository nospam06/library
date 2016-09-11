package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.transaction.entity.Media;
import com.ohmyapp.library.transaction.entity.MediaCopy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emerald on 8/26/2016.
 * mediacopy dao
 */
public class DaoMediaCopy extends DaoCommon<MediaCopy> implements Dao<MediaCopy> {
    DaoMediaCopy() {
        // package access
    }

    @Override
    public MediaCopy save(MediaCopy entity) {
        return saveBase(entity);
    }

    @Override
    public void update(MediaCopy entity) {
        updateBase(entity);
    }

    @Override
    public void delete(MediaCopy entity) {
        deleteBase(entity);
    }

    @Override
    public MediaCopy findById(Serializable id) {
        return findOne(MediaCopy.FIND_BY_ID, "id", id);
    }

    /**
     * find by barcode
     *
     * @param barcode barcode
     * @return mediacopy
     */
    public List<MediaCopy> findByBarcode(String barcode) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("barcode", '%' + barcode + '%');
        return super.findMatching(MediaCopy.FIND_BY_BARCODE, parmMap);
    }

    /**
     * find by status of a media
     *
     * @param media  media parent
     * @return mediacopy
     */
    public List<MediaCopy> findByMedia(Media media) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("parent", media);
        return super.findMatching(MediaCopy.FIND_BY_MEDIA, parmMap);
    }

    /**
     * find by title and status
     * @param title   media parent title
     * @param status   media copy status
     * @return mediacopy
     */
    public List<MediaCopy> findByStatusTitle(String title, String status) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("title", '%' + title + '%');
        parmMap.put("status", status);
        return super.findMatching(MediaCopy.FIND_BY_STATUS_TITLE, parmMap);
    }

    /**
     * find by barcode checked out
     *
     * @param barcode barcode
     * @return mediacopy
     */
    public List<MediaCopy> findCheckedoutByBarcode(String barcode) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("barcode", '%' + barcode + '%');
        return super.findMatching(MediaCopy.FIND_CHECKEDOUT_BY_BARCODE, parmMap);
    }

    /**
     * find checked out media copy by title
     *
     * @param title media parent title
     * @return mediacopy
     */
    public List<MediaCopy> findCheckedoutByTitle(String title) {
        Map<String, Serializable> parmMap = new HashMap<>();
        parmMap.put("title", '%' + title + '%');
        return super.findMatching(MediaCopy.FIND_CHECKEDOUT_BY_TITLE, parmMap);
    }
}

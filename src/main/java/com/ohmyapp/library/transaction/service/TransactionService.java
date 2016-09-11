package com.ohmyapp.library.transaction.service;

import com.ohmyapp.library.pojo.MediaCopyPojo;
import com.ohmyapp.library.pojo.MediaPojo;
import com.ohmyapp.library.pojo.PatronPojo;
import com.ohmyapp.library.pojo.RecordPojo;
import com.ohmyapp.library.service.LibraryException;

import java.util.Collection;
import java.util.List;

/**
 * Created by Emerald on 8/29/2016.
 * Transaction service
 */
public interface TransactionService {
    /**
     * find a patron by id
     *
     * @param id patron id
     * @return patron
     * @throws LibraryException ex
     */
    PatronPojo getPatron(Long id) throws LibraryException;

    /**
     * find a patron
     *
     * @param name patron name
     * @return patron
     * @throws LibraryException ex
     */
    Collection<PatronPojo> findPatron(String name) throws LibraryException;

    /**
     * update a patron
     *
     * @param patronPojo@return patron
     * @throws LibraryException ex
     */
    PatronPojo savePatron(PatronPojo patronPojo) throws LibraryException;

    /**
     * find media by id
     *
     * @param id media id
     * @return media
     * @throws LibraryException ex
     */
    MediaPojo getMedia(Long id) throws LibraryException;

    /**
     * find media
     *
     * @param search search phrase
     * @return search result
     * @throws LibraryException ex
     */
    Collection<MediaPojo> findMedia(String search) throws LibraryException;

    /**
     * add media
     *
     * @param mediaPojo media pojo
     * @return media
     * @throws LibraryException ex
     */
    MediaPojo saveMedia(MediaPojo mediaPojo) throws LibraryException;

    /**
     * find media copy
     *
     * @param search search phrase
     * @return search result
     * @throws LibraryException ex
     */
    Collection<MediaPojo> findMediaCopy(String search) throws LibraryException;

    /**
     * find media copy which is checked out
     *
     * @param search search phrase
     * @return search result
     * @throws LibraryException ex
     */
    Collection<MediaCopyPojo> findCheckedoutMediaCopy(String search) throws LibraryException;

    /**
     * check out by media
     *
     * @param patronPojo patron
     * @param mediaPojo  media
     * @return check out record
     * @throws LibraryException ex
     */
    RecordPojo checkout(PatronPojo patronPojo, MediaPojo mediaPojo) throws LibraryException;

    /**
     * check out by media
     *
     * @param patronPojo    patron
     * @param mediaCopyPojo media
     * @return check out record
     * @throws LibraryException ex
     */
    RecordPojo checkout(PatronPojo patronPojo, MediaCopyPojo mediaCopyPojo) throws LibraryException;

    /**
     * check in a media copy
     *
     * @param recordPojo check out record
     * @return check in record
     * @throws LibraryException ex
     */
    RecordPojo checkin(RecordPojo recordPojo) throws LibraryException;

    /**
     * find patron record
     *
     * @param patronPojo patron
     * @return record list
     * @throws LibraryException ex
     */
    List<RecordPojo> patronRecord(PatronPojo patronPojo) throws LibraryException;
}

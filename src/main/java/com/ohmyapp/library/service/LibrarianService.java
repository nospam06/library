package com.ohmyapp.library.service;

import com.ohmyapp.library.pojo.MediaCopyPojo;
import com.ohmyapp.library.pojo.MediaPojo;
import com.ohmyapp.library.pojo.PatronPojo;
import com.ohmyapp.library.pojo.RecordPojo;

import java.util.Collection;
import java.util.List;

/**
 * Created by Emerald on 8/28/2016.
 * Librarian Service
 */
public interface LibrarianService {
    /**
     * find patron by id
     *
     * @param id patron id
     * @return patron
     * @throws LibraryException ex
     */
    PatronPojo getPatron(Long id) throws LibraryException;

    /**
     * find patron by name
     *
     * @param name partial name
     * @return patron list
     * @throws LibraryException ex
     */
    Collection<PatronPojo> findPatron(String name) throws LibraryException;

    /**
     * update a patron
     *
     * @param patronPojo patron pojo
     * @return patron
     * @throws LibraryException ex
     */
    PatronPojo savePatron(PatronPojo patronPojo) throws LibraryException;

    /**
     * find media by id
     *
     * @param id media id
     * @return media found
     * @throws LibraryException ex
     */
    MediaPojo getMedia(Long id) throws LibraryException;

    /**
     * find media by title or author
     *
     * @param search search string
     * @return media found
     * @throws LibraryException ex
     */
    Collection<MediaPojo> findMedia(String search) throws LibraryException;

    /**
     * add media to library
     *
     * @param mediaPojo mediaPojo
     * @return media created
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
     * find media copy checked out
     *
     * @param search search phrase
     * @return search result
     * @throws LibraryException ex
     */
    Collection<MediaCopyPojo> findCheckedoutMediaCopy(String search) throws LibraryException;


    /**
     * checkout a media to a patron
     *
     * @param patron patron
     * @param media  media
     * @return record
     * @throws LibraryException ex
     */
    RecordPojo checkout(PatronPojo patron, MediaPojo media) throws LibraryException;

    /**
     * checkout a media to a patron
     *
     * @param patron    patron
     * @param mediaCopy mediaCopy
     * @return record
     * @throws LibraryException ex
     */
    RecordPojo checkout(PatronPojo patron, MediaCopyPojo mediaCopy) throws LibraryException;

    /**
     * check in a media copy
     *
     * @param recordPojo check out record
     * @return record
     * @throws LibraryException ex
     */
    RecordPojo checkin(RecordPojo recordPojo) throws LibraryException;

    /**
     * display patron library records
     *
     * @param patron patron
     * @return patron records
     * @throws LibraryException ex
     */
    List<RecordPojo> patronRecord(PatronPojo patron) throws LibraryException;
}

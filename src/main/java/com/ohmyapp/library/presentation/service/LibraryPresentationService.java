package com.ohmyapp.library.presentation.service;

import com.ohmyapp.library.service.LibraryException;

/**
 * Created by Emerald on 9/3/2016.
 *
 */
public interface LibraryPresentationService {
    /**
     * get patron by id
     *
     * @param id patron id
     * @return patron
     * @throws LibraryException ex
     */
    String getPatron(String id) throws LibraryException;

    /**
     * search patron
     *
     * @param name search string
     * @return search result
     * @throws LibraryException ex
     */
    String findPatron(String name) throws LibraryException;

    /**
     * get media by id
     *
     * @param id media id
     * @return media
     * @throws LibraryException ex
     */
    String getMedia(String id) throws LibraryException;

    /**
     * search media
     *
     * @param search search string
     * @return search result
     * @throws LibraryException ex
     */
    String findMedia(String search) throws LibraryException;

    /**
     * checkout media to patron
     *
     * @param patronId patron id
     * @param mediaId  media id
     * @return checkout record
     * @throws LibraryException ex
     */
    String checkout(String patronId, String mediaId) throws LibraryException;

    /**
     * find media copy
     *
     * @param search serach string
     * @return serach result
     * @throws LibraryException ex
     */
    String findMediaCopy(String search) throws LibraryException;

    /**
     * find checked out media copy
     *
     * @param search serach string
     * @return serach result
     * @throws LibraryException ex
     */
    String findCheckedoutMediaCopy(String search) throws LibraryException;

    /**
     * checkin media
     *
     * @param mediaCopyId media copy id
     * @return record
     * @throws LibraryException ex
     */
    String checkin(String mediaCopyId) throws LibraryException;

    /**
     * update patron
     *
     * @param patron patron
     * @return patron
     */
    String savePatron(String patron) throws LibraryException;

    /**
     * update media
     *
     * @param media media
     * @return media
     * @throws LibraryException ex
     */
    String saveMedia(String media) throws LibraryException;

    /**
     * find patron librarian record
     *
     * @param id patron id
     * @return records
     * @throws LibraryException ex
     */
    String getPatronRecord(String id) throws LibraryException;
}

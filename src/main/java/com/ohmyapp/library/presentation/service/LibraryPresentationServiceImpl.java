package com.ohmyapp.library.presentation.service;

import com.google.gson.Gson;
import com.ohmyapp.library.pojo.MediaCopyPojo;
import com.ohmyapp.library.pojo.MediaPojo;
import com.ohmyapp.library.pojo.PatronPojo;
import com.ohmyapp.library.pojo.Pojo;
import com.ohmyapp.library.pojo.RecordPojo;
import com.ohmyapp.library.presentation.data.RecordResponse;
import com.ohmyapp.library.presentation.data.Response;
import com.ohmyapp.library.service.LibrarianService;
import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.service.LibraryServiceFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Emerald on 8/28/2016.
 * presentation layer for client
 */
public class LibraryPresentationServiceImpl implements LibraryPresentationService {
    private static final Logger LOGGER = Logger.getLogger(LibraryPresentationServiceImpl.class);
    private static final Gson gson = new Gson();
    private LibrarianService librarianService = LibraryServiceFactory.getLibrarianService();

    @Override
    public String getPatron(String id) throws LibraryException {
        if (id == null || id.trim().isEmpty()) {
            return prepareResponse(new PatronPojo());
        }
        PatronPojo patronPojo = librarianService.getPatron(Long.parseLong(id));
        return prepareResponse(patronPojo);
    }

    @Override
    public String findPatron(String name) throws LibraryException {
        Collection<PatronPojo> patronCollection = librarianService.findPatron(name);
        return prepareResponse(new ArrayList<>(patronCollection));
    }

    @Override
    public String getMedia(String id) throws LibraryException {
        if (id == null || id.trim().isEmpty()) {
            return prepareResponse(new MediaPojo());
        }
        MediaPojo mediaPojo = librarianService.getMedia(Long.parseLong(id));
        return prepareResponse(mediaPojo);
    }

    @Override
    public String findMedia(String search) throws LibraryException {
        Collection<MediaPojo> mediaCollection = librarianService.findMedia(search);
        return prepareResponse(new ArrayList<>(mediaCollection));
    }

    @Override
    public String checkout(String patronId, String mediaId) throws LibraryException {
        PatronPojo patronPojo = new PatronPojo();
        patronPojo.setId(Long.parseLong(patronId));
        MediaPojo mediaPojo = new MediaPojo();
        mediaPojo.setId(Long.parseLong(mediaId));
        RecordPojo recordPojo = librarianService.checkout(patronPojo, mediaPojo);
        RecordResponse recordResponse = new RecordResponse();
        try {
            BeanUtils.copyProperties(recordResponse, recordPojo.getMediaPojo());
            BeanUtils.copyProperties(recordResponse, recordPojo.getMediaPojo().getMediaCopies().get(0));
            BeanUtils.copyProperties(recordResponse, recordPojo.getPatronPojo());
            BeanUtils.copyProperties(recordResponse, recordPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
        return prepareResponse(recordResponse);
    }

    @Override
    public String findMediaCopy(String search) throws LibraryException {
        Collection<MediaPojo> mediaCollection = librarianService.findMediaCopy(search);
        Collection<Pojo> recordResponses = new ArrayList<>();
        for (MediaPojo mediaPojo : mediaCollection) {
            RecordResponse recordResponse = new RecordResponse();
            try {
                BeanUtils.copyProperties(recordResponse, mediaPojo);
                BeanUtils.copyProperties(recordResponse, mediaPojo.getMediaCopies().get(0));
                recordResponses.add(recordResponse);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new LibraryException(e.getMessage(), e);
            }
        }
        return prepareResponse(recordResponses);
    }

    @Override
    public String findCheckedoutMediaCopy(String search) throws LibraryException {
        Collection<MediaCopyPojo> mediaCopyPojoCollection = librarianService.findCheckedoutMediaCopy(search);
        Collection<Pojo> recordResponses = new ArrayList<>();
        for (MediaCopyPojo mediaCopyPojo : mediaCopyPojoCollection) {
            RecordResponse recordResponse = new RecordResponse();
            try {
                BeanUtils.copyProperties(recordResponse, mediaCopyPojo);
                BeanUtils.copyProperties(recordResponse, mediaCopyPojo.getMediaPojo());
                BeanUtils.copyProperties(recordResponse, mediaCopyPojo.getRecordPojo());
                recordResponses.add(recordResponse);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new LibraryException(e.getMessage(), e);
            }
        }
        return prepareResponse(recordResponses);
    }

    @Override
    public String checkin(String record) throws LibraryException {
        RecordPojo recordPojo = gson.fromJson(record, RecordPojo.class);
        recordPojo = librarianService.checkin(recordPojo);
        RecordResponse recordResponse = new RecordResponse();
        try {
            BeanUtils.copyProperties(recordResponse, recordPojo.getMediaPojo());
            BeanUtils.copyProperties(recordResponse, recordPojo.getMediaPojo().getMediaCopies().get(0));
            BeanUtils.copyProperties(recordResponse, recordPojo.getPatronPojo());
            BeanUtils.copyProperties(recordResponse, recordPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
        return prepareResponse(recordResponse);
    }

    @Override
    public String savePatron(String patron) throws LibraryException {
        PatronPojo patronpojo = gson.fromJson(patron, PatronPojo.class);
        patronpojo = librarianService.savePatron(patronpojo);
        return prepareResponse(patronpojo);
    }

    @Override
    public String saveMedia(String media) throws LibraryException {
        MediaPojo mediapojo = gson.fromJson(media, MediaPojo.class);
        mediapojo = librarianService.saveMedia(mediapojo);
        return prepareResponse(mediapojo);
    }

    @Override
    public String getPatronRecord(String id) throws LibraryException {
        PatronPojo patronPojo = new PatronPojo();
        try {
            Long patronId = Long.parseLong(id);
            patronPojo.setId(patronId);
        } catch (NumberFormatException ex) {
            throw new LibraryException("patron id is not valid", ex);
        }
        List<RecordPojo> recordPojoList = librarianService.patronRecord(patronPojo);
        List<Pojo> recordResponseList = new ArrayList<>();
        try {
            for (RecordPojo recordPojo : recordPojoList) {
                RecordResponse recordResponse = new RecordResponse();
                BeanUtils.copyProperties(recordResponse, recordPojo.getMediaPojo());
                BeanUtils.copyProperties(recordResponse, recordPojo.getMediaPojo().getMediaCopies().get(0));
                BeanUtils.copyProperties(recordResponse, recordPojo.getPatronPojo());
                BeanUtils.copyProperties(recordResponse, recordPojo);
                recordResponseList.add(recordResponse);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
        return prepareResponse(recordResponseList);
    }

    private String prepareResponse(Pojo data) {
        return prepareResponse(Collections.singleton(data));
    }

    private String prepareResponse(Collection<Pojo> data) {
        Response response = new Response();
        List<Pojo> dataList = new ArrayList<>();
        dataList.addAll(data);
        response.setData(dataList);
        String json = gson.toJson(response);
        LOGGER.debug(json);
        return json;
    }
}

package com.ohmyapp.library.transaction.service;

import com.ohmyapp.library.pojo.MediaCopyPojo;
import com.ohmyapp.library.pojo.MediaPojo;
import com.ohmyapp.library.pojo.PatronPojo;
import com.ohmyapp.library.pojo.RecordPojo;
import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.TransactionFactory;
import com.ohmyapp.library.transaction.dao.DaoFactory;
import com.ohmyapp.library.transaction.dao.DaoMedia;
import com.ohmyapp.library.transaction.dao.DaoMediaCopy;
import com.ohmyapp.library.transaction.dao.DaoPatron;
import com.ohmyapp.library.transaction.dao.DaoRecord;
import com.ohmyapp.library.transaction.entity.Media;
import com.ohmyapp.library.transaction.entity.MediaCopy;
import com.ohmyapp.library.transaction.entity.Patron;
import com.ohmyapp.library.transaction.entity.Record;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Emerald on 8/27/2016.
 * services performed by a librarian
 */
public class TransactionServiceImpl implements TransactionService {
    private static final Logger LOGGER = Logger.getLogger(TransactionServiceImpl.class);

    @Override
    public PatronPojo getPatron(Long id) throws LibraryException {
        try {
            TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
            DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
            Patron patron = daoPatron.findById(id);
            if (patron == null) {
                throw new LibraryException("Cannot find patron", null);
            }
            PatronPojo pojo = new PatronPojo();
            BeanUtils.copyProperties(pojo, patron);
            return pojo;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public Collection<PatronPojo> findPatron(String name) throws LibraryException {
        try {
            DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
            List<Patron> patronList = daoPatron.findByName(name);
            Set<PatronPojo> patronPojoSet = new HashSet<>();
            for (Patron patron : patronList) {
                PatronPojo pojo = new PatronPojo();
                BeanUtils.copyProperties(pojo, patron);
                patronPojoSet.add(pojo);
            }
            return patronPojoSet;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public PatronPojo savePatron(PatronPojo patronPojo) throws LibraryException {
        TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
        Patron patron;
        if (patronPojo.getId() == null) {
            patron = new Patron();
        } else {
            patron = daoPatron.findById(patronPojo.getId());
        }
        patron.setName(patronPojo.getName());
        if (patronPojo.getId() == null) {
            daoPatron.save(patron);
        } else {
            daoPatron.update(patron);
        }
        TransactionFactory.commit();
        LOGGER.debug("Patron " + patronPojo.getName() + " update");
        patronPojo.setId(patron.getId());
        return patronPojo;
    }

    @Override
    public MediaPojo getMedia(Long id) throws LibraryException {
        try {
            TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            Media media = daoMedia.findById(id);
            if (media == null) {
                throw new LibraryException("Cannot find media", null);
            }
            MediaPojo pojo = new MediaPojo();
            BeanUtils.copyProperties(pojo, media);
            return pojo;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public Collection<MediaPojo> findMedia(String search) throws LibraryException {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            List<Media> mediaByTitle = daoMedia.findByTitle(search);
            List<Media> mediaByAuthor = daoMedia.findByAuthor(search);
            Set<Media> mediaSet = new HashSet<>(mediaByTitle);
            mediaSet.addAll(mediaByAuthor);

            Set<MediaPojo> mediaPojoSet = new HashSet<>();
            for (Media media : mediaSet) {
                MediaPojo pojo = new MediaPojo();
                BeanUtils.copyProperties(pojo, media);
                List<MediaCopyPojo> mediaCopyPojoList = new ArrayList<>();
                pojo.setMediaCopies(mediaCopyPojoList);
                mediaPojoSet.add(pojo);
                int available = 0;
                for (MediaCopy mediaCopy : daoMediaCopy.findByMedia(media)) {
                    MediaCopyPojo pojoCopy = new MediaCopyPojo();
                    BeanUtils.copyProperties(pojoCopy, mediaCopy);
                    mediaCopyPojoList.add(pojoCopy);
                    if ("available".equals(pojoCopy.getStatus())) {
                        ++available;
                    }
                }
                pojo.setAvailable(available);
            }
            return mediaPojoSet;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public MediaPojo saveMedia(MediaPojo mediaPojo) throws LibraryException {
        TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
        DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
        Media media;
        List<MediaCopy> mediaCopies;
        int startCopy = 0;
        if (mediaPojo.getId() == null) {
            media = new Media();
            mediaCopies = new ArrayList<>();
        } else {
            media = daoMedia.findById(mediaPojo.getId());
            mediaCopies = daoMediaCopy.findByMedia(media);
            startCopy = Math.min(media.getCopies(), mediaCopies.size());
        }
        media.setTitle(mediaPojo.getTitle());
        media.setAuthor(mediaPojo.getAuthor());
        media.setFormat(mediaPojo.getFormat());
        media.setGenre(mediaPojo.getGenre());
        media.setIsbn(mediaPojo.getIsbn());
        media.setCopies(mediaPojo.getCopies());
        // create copies
        for (int i = startCopy; i < mediaPojo.getCopies(); ++i) {
            MediaCopy mediaCopy = new MediaCopy();
            mediaCopy.setBarcode(UUID.randomUUID().toString());
            mediaCopy.setStatus("available");
            mediaCopy.setParent(media);
            mediaCopies.add(mediaCopy);
            daoMediaCopy.save(mediaCopy);
            LOGGER.debug(mediaPojo.getTitle() + " added 1 more copy barcode " + mediaCopy.getBarcode());
        }
        if (media.getId() == null) {
            daoMedia.save(media);
        } else {
            daoMedia.update(media);
        }
        TransactionFactory.commit();
        LOGGER.debug("Media " + mediaPojo.getTitle() + " added " + mediaPojo.getCopies() + " copies");
        MediaPojo pojo = new MediaPojo();
        try {
            BeanUtils.copyProperties(pojo, media);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
        return pojo;
    }

    @Override
    public RecordPojo checkout(PatronPojo patronPojo, MediaPojo mediaPojo) throws LibraryException {
        if (patronPojo == null || patronPojo.getId() == null
                || mediaPojo == null || mediaPojo.getId() == null) {
            return null;
        }
        try {
            TransactionFactory.getSession(TransactionFactory.TRANSACTION_NOT_REQUIRED);
            // find an available media copy
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            Media media = new Media();
            BeanUtils.copyProperties(media, mediaPojo);
            List<MediaCopy> mediaCopyList = daoMediaCopy.findByMedia(media);
            if (mediaCopyList == null || mediaCopyList.isEmpty()) {
                throw new LibraryException("No available copy", null);
            }
            List<MediaCopy> availableList = new ArrayList<>();
            for (MediaCopy mediaCopy : mediaCopyList) {
                if ("available".equals(mediaCopy.getStatus())) {
                    availableList.add(mediaCopy);
                }
            }
            MediaCopy mediaCopy = availableList.get(0);
            MediaCopyPojo mediaCopyPojo = new MediaCopyPojo();
            BeanUtils.copyProperties(mediaCopyPojo, mediaCopy);
            return checkout(patronPojo, mediaCopyPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public RecordPojo checkout(PatronPojo patronPojo, MediaCopyPojo mediaCopyPojo) throws LibraryException {
        if (patronPojo == null || patronPojo.getId() == null
                || mediaCopyPojo == null || mediaCopyPojo.getId() == null
                || !"available".equals(mediaCopyPojo.getStatus())) {
            return null;
        }
        TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        // load patron from db
        DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
        Patron patron = daoPatron.findById(patronPojo.getId());
        if (patron == null) {
            throw new LibraryException("Cannot find patron", null);
        }
        // load media copy
        DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
        MediaCopy mediaCopy = daoMediaCopy.findById(mediaCopyPojo.getId());
        if (mediaCopy == null) {
            throw new LibraryException("Cannot find media copy", null);
        }
        if (!"available".equals(mediaCopyPojo.getStatus())) {
            throw new LibraryException("Media copy not available", null);
        }
        try {
            // perform check out
            DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
            Record record = new Record();
            record.setMediaCopy(mediaCopy);
            record.setPatron(patron);
            Calendar calendar = Calendar.getInstance();
            record.setCheckout(calendar.getTime());
            calendar.add(Calendar.DATE, 7);
            record.setDue(calendar.getTime());
            daoRecord.save(record);
            LOGGER.debug("media " + record.getMediaCopy().getParent().getTitle() + " checked out to "
                    + record.getPatron().getName() + " on " + record.getCheckout());
            // tie checkout record to media copy
            mediaCopy.setRecord(record);
            mediaCopy.setStatus("out");
            daoMediaCopy.update(mediaCopy);
            TransactionFactory.commit();
            // return record pojo
            RecordPojo recordPojo = new RecordPojo();
            BeanUtils.copyProperties(recordPojo, record);
            BeanUtils.copyProperties(patronPojo, record.getPatron());
            recordPojo.setPatronPojo(patronPojo);
            MediaPojo mediaPojo = new MediaPojo();
            BeanUtils.copyProperties(mediaPojo, mediaCopy.getParent());
            BeanUtils.copyProperties(mediaCopyPojo, mediaCopy);
            mediaPojo.setMediaCopies(Collections.singletonList(mediaCopyPojo));
            recordPojo.setMediaPojo(mediaPojo);
            return recordPojo;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public Collection<MediaPojo> findMediaCopy(String search) throws LibraryException {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            List<MediaCopy> barcodeList = daoMediaCopy.findByBarcode(search);
            List<MediaCopy> statusList = daoMediaCopy.findByStatusTitle(search, "out");

            Set<MediaCopy> mediaCopySet = new HashSet<>(statusList);
            for (MediaCopy mediaCopy : barcodeList) {
                if ("out".equals(mediaCopy.getStatus())) {
                    mediaCopySet.add(mediaCopy);
                }
            }
            Collection<MediaPojo> pojoCollection = new ArrayList<>();
            for (MediaCopy mediaCopy : mediaCopySet) {
                MediaPojo mediaPojo = new MediaPojo();
                MediaCopyPojo mediaCopyPojo = new MediaCopyPojo();
                BeanUtils.copyProperties(mediaPojo, mediaCopy.getParent());
                BeanUtils.copyProperties(mediaCopyPojo, mediaCopy);
                mediaPojo.setMediaCopies(Collections.singletonList(mediaCopyPojo));
                pojoCollection.add(mediaPojo);
            }
            return pojoCollection;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public Collection<MediaCopyPojo> findCheckedoutMediaCopy(String search) throws LibraryException {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            List<MediaCopy> barcodeList = daoMediaCopy.findCheckedoutByBarcode(search);
            List<MediaCopy> titleList = daoMediaCopy.findCheckedoutByTitle(search);

            Set<MediaCopy> mediaCopySet = new HashSet<>(titleList);
            for (MediaCopy mediaCopy : barcodeList) {
                if (mediaCopy.getRecord() != null) {
                    mediaCopySet.add(mediaCopy);
                }
            }
            Collection<MediaCopyPojo> pojoCollection = new ArrayList<>();
            for (MediaCopy mediaCopy : mediaCopySet) {
                MediaPojo mediaPojo = new MediaPojo();
                MediaCopyPojo mediaCopyPojo = new MediaCopyPojo();
                RecordPojo recordPojo = new RecordPojo();
                BeanUtils.copyProperties(mediaPojo, mediaCopy.getParent());
                BeanUtils.copyProperties(mediaCopyPojo, mediaCopy);
                BeanUtils.copyProperties(recordPojo, mediaCopy.getRecord());
                mediaCopyPojo.setMediaPojo(mediaPojo);
                mediaCopyPojo.setRecordPojo(recordPojo);
                pojoCollection.add(mediaCopyPojo);
            }
            return pojoCollection;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }

    @Override
    public RecordPojo checkin(RecordPojo recordPojo) throws LibraryException {
        if (recordPojo == null || recordPojo.getId() == null) {
            throw new LibraryException("Cannot checked in, record id is null", null);
        }
        TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
        // load media copy
        DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
        Record record = daoRecord.findById(recordPojo.getId());
        if (record == null) {
            throw new LibraryException("Cannot find check out record", null);
        }
        // perform check in
        DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
        MediaCopy mediaCopy = daoMediaCopy.findById(record.getMediaCopy().getId());
        mediaCopy.setStatus("available");
        mediaCopy.setRecord(null);
        daoMediaCopy.update(mediaCopy);

        record.setCheckin(new Date());
        record.setReturned(true);
        daoRecord.update(record);
        TransactionFactory.commit();
        LOGGER.debug("media " + record.getMediaCopy().getParent().getTitle() + " checked in on "
                + record.getCheckin());
        // return record pojo
        try {
            MediaPojo mediaPojo = new MediaPojo();
            MediaCopyPojo mediaCopyPojo = new MediaCopyPojo();
            PatronPojo patronPojo = new PatronPojo();
            BeanUtils.copyProperties(mediaPojo, mediaCopy.getParent());
            BeanUtils.copyProperties(mediaCopyPojo, mediaCopy);
            BeanUtils.copyProperties(patronPojo, record.getPatron());
            BeanUtils.copyProperties(recordPojo, record);
            mediaPojo.setMediaCopies(Collections.singletonList(mediaCopyPojo));
            recordPojo.setMediaPojo(mediaPojo);
            recordPojo.setPatronPojo(patronPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
        return recordPojo;
    }

    @Override
    public List<RecordPojo> patronRecord(PatronPojo patronPojo) throws LibraryException {
        if (patronPojo == null || patronPojo.getId() == null) {
            throw new LibraryException("Patron is not provided", null);
        }
        // load patron from db
        DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
        Patron patron = daoPatron.findById(patronPojo.getId());
        if (patron == null) {
            throw new LibraryException("Cannot Find Patron", null);
        }
        try {
            DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
            List<Record> recordList = daoRecord.findByPatron(patron);
            List<RecordPojo> recordPojoList = new ArrayList<>();
            for (Record record : recordList) {
                RecordPojo recordPojo = new RecordPojo();
                MediaPojo mediaPojo = new MediaPojo();
                MediaCopyPojo mediaCopyPojo = new MediaCopyPojo();
                patronPojo = new PatronPojo();
                BeanUtils.copyProperties(mediaPojo, record.getMediaCopy().getParent());
                BeanUtils.copyProperties(mediaCopyPojo, record.getMediaCopy());
                BeanUtils.copyProperties(patronPojo, record.getPatron());
                BeanUtils.copyProperties(recordPojo, record);
                mediaPojo.setMediaCopies(Collections.singletonList(mediaCopyPojo));
                recordPojo.setMediaPojo(mediaPojo);
                recordPojo.setPatronPojo(patronPojo);
                recordPojoList.add(recordPojo);
            }
            return recordPojoList;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LibraryException(e.getMessage(), e);
        }
    }
}
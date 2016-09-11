package com.ohmyapp.library.service;

import com.ohmyapp.library.pojo.MediaCopyPojo;
import com.ohmyapp.library.pojo.MediaPojo;
import com.ohmyapp.library.pojo.PatronPojo;
import com.ohmyapp.library.pojo.RecordPojo;
import com.ohmyapp.library.transaction.TransactionFactory;
import com.ohmyapp.library.transaction.service.TransactionService;

import java.util.Collection;
import java.util.List;

/**
 * Created by Emerald on 8/27/2016.
 * services performed by a librarian
 */
public class LibrarianServiceImpl implements LibrarianService {
    private TransactionService transactionService = TransactionFactory.getTransactionService();

    @Override
    public PatronPojo getPatron(Long id) throws LibraryException {
        return transactionService.getPatron(id);
    }

    @Override
    public Collection<PatronPojo> findPatron(String name) throws LibraryException {
        return transactionService.findPatron(name);
    }

    @Override
    public PatronPojo savePatron(PatronPojo patronPojo) throws LibraryException {
        return transactionService.savePatron(patronPojo);
    }

    @Override
    public MediaPojo getMedia(Long id) throws LibraryException {
        return transactionService.getMedia(id);
    }

    @Override
    public Collection<MediaPojo> findMedia(String search) throws LibraryException {
        return transactionService.findMedia(search);
    }

    @Override
    public MediaPojo saveMedia(MediaPojo mediaPojo) throws LibraryException {
        return transactionService.saveMedia(mediaPojo);
    }

    @Override
    public Collection<MediaPojo> findMediaCopy(String search) throws LibraryException {
        return transactionService.findMediaCopy(search);
    }

    @Override
    public Collection<MediaCopyPojo> findCheckedoutMediaCopy(String search) throws LibraryException {
        return transactionService.findCheckedoutMediaCopy(search);
    }

    @Override
    public RecordPojo checkout(PatronPojo patron, MediaPojo media) throws LibraryException {
        return transactionService.checkout(patron, media);
    }

    @Override
    public RecordPojo checkout(PatronPojo patron, MediaCopyPojo mediaCopy) throws LibraryException {
        if (patron == null || mediaCopy == null || !"available".equals(mediaCopy.getStatus())) {
            return null;
        }
        return transactionService.checkout(patron, mediaCopy);
    }

    @Override
    public RecordPojo checkin(RecordPojo recordPojo) throws LibraryException {
        return transactionService.checkin(recordPojo);
    }

    @Override
    public List<RecordPojo> patronRecord(PatronPojo patron) throws LibraryException {
        return transactionService.patronRecord(patron);
    }
}
package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.TransactionFactory;
import com.ohmyapp.library.transaction.entity.Media;
import com.ohmyapp.library.transaction.entity.MediaCopy;
import com.ohmyapp.library.transaction.entity.Patron;
import com.ohmyapp.library.transaction.entity.Record;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Emerald on 9/4/2016.
 *
 */
public class DaoBaseTest {
    private static final Logger LOGGER = Logger.getLogger(DaoBaseTest.class);

    protected Patron createPatron(String name) throws LibraryException {
        DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
        Patron patron = new Patron();
        patron.setName(name);
        return daoPatron.save(patron);
    }

    protected Media createMedia(DaoMedia daoMedia) {
        Media media = new Media();
        media.setAuthor("Me");
        media.setTitle("My First Book");
        media.setFormat("book");
        media.setGenre("comedy");
        media.setIsbn("1234567890");
        media.setCopies(10);
        Media newMedia = daoMedia.save(media);
        TransactionFactory.commit();
        Long newId = newMedia.getId();
        LOGGER.debug("new id = " + newId);
        assertNotNull(newId);
        return newMedia;
    }

    protected MediaCopy createMediaCopy(DaoMediaCopy daoMediaCopy) {
        MediaCopy mediaCopy = new MediaCopy();
        mediaCopy.setBarcode(String.valueOf(UUID.randomUUID()));
        mediaCopy.setStatus("available");
        Media media = new Media();
        media.setAuthor("Me");
        media.setTitle("My First Book");
        media.setFormat("book");
        media.setGenre("comedy");
        media.setIsbn("1234567890");
        media.setCopies(10);
        DaoMedia daoMedia = new DaoMedia();
        Media newMedia = daoMedia.save(media);
        LOGGER.debug("new id = " + newMedia.getId());
        assertNotNull(newMedia.getId());
        mediaCopy.setParent(media);
        daoMediaCopy.save(mediaCopy);
        TransactionFactory.commit();
        return mediaCopy;
    }

    protected Record createRecord(String suffix) throws LibraryException {
        DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
        Record record = new Record();
        Media media = new Media();
        media.setTitle("Title " + suffix);
        media.setAuthor("Author " + suffix);
        media.setIsbn("ISBN" + suffix);
        media.setFormat("book");
        media.setGenre("action");
        media.setCopies(1);
        DaoMedia daoMedia = new DaoMedia();
        daoMedia.save(media);

        Patron patron = new Patron();
        patron.setName("Patron " + suffix);
        DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
        daoPatron.save(patron);

        MediaCopy mediaCopy = new MediaCopy();
        mediaCopy.setParent(media);
        mediaCopy.setBarcode(UUID.randomUUID().toString());
        mediaCopy.setStatus("available");
        DaoMediaCopy daoMediaCopy = new DaoMediaCopy();
        daoMediaCopy.save(mediaCopy);

        record.setMediaCopy(mediaCopy);
        record.setPatron(patron);
        Calendar calendar = Calendar.getInstance();
        record.setCheckout(calendar.getTime());
        calendar.add(Calendar.DATE, 7);
        record.setDue(calendar.getTime());
        daoRecord.save(record);
        LOGGER.debug("record created " + record.getPatron().getName() + " " + record.getMediaCopy().getParent().getTitle());
        return record;
    }
}

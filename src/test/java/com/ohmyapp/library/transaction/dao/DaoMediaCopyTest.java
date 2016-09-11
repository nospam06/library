package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.TransactionFactory;
import com.ohmyapp.library.transaction.entity.MediaCopy;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Created by Emerald on 8/27/2016.
 * test mediacopy dao
 */
public class DaoMediaCopyTest extends DaoBaseTest {
    private static final Logger LOGGER = Logger.getLogger(DaoMediaCopyTest.class);

    @Test
    public void save() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            MediaCopy mediaCopy = createMediaCopy(daoMediaCopy);
            assertNotNull(mediaCopy);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void delete() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            MediaCopy mediaCopy = createMediaCopy(daoMediaCopy);
            assertNotNull(mediaCopy);
            Long id = mediaCopy.getId();
            LOGGER.debug("id=" + id);
            TransactionFactory.getSession(TransactionFactory.TRANSACTION_REQUIRED);
            daoMediaCopy.delete(mediaCopy);
            TransactionFactory.commit();
            // find it in db
            MediaCopy mediaCopyById = daoMediaCopy.findById(id);
            assertNull("mediaCopy not deleted from db", mediaCopyById);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findById() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            MediaCopy mediaCopy = createMediaCopy(daoMediaCopy);
            assertNotNull(mediaCopy);
            Long id = mediaCopy.getId();
            LOGGER.debug("id=" + id);
            // find it in db
            MediaCopy mediaCopyById = daoMediaCopy.findById(id);
            assertNotNull("mediaCopy not found in db", mediaCopyById);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByBarcode() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            MediaCopy mediaCopy = createMediaCopy(daoMediaCopy);
            assertNotNull(mediaCopy);
            String barcode = mediaCopy.getBarcode();
            LOGGER.debug("barcode=" + barcode);
            // find it in db
            List<MediaCopy> mediaCopyList = daoMediaCopy.findByBarcode(barcode);
            assertNotNull("mediaCopy not found in db", mediaCopyList);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByStatus() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            MediaCopy mediaCopy = createMediaCopy(daoMediaCopy);
            assertNotNull(mediaCopy);
            String status = mediaCopy.getStatus();
            LOGGER.debug("status=" + status);
            // find it in db
            List<MediaCopy> mediaCopyList = daoMediaCopy.findByBarcode(status);
            assertNotNull("mediaCopy not found in db", mediaCopyList);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByStatusTitle() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            String title = "first";
            String status = "out";
            // find it in db
            List<MediaCopy> mediaCopyList = daoMediaCopy.findByStatusTitle(title, status);
            assertNotNull("mediaCopy not found in db", mediaCopyList);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findCheckedoutByTitle() {
        try {
            DaoMediaCopy daoMediaCopy = DaoFactory.getDao(DaoMediaCopy.class);
            String title = "first";
            // find it in db
            List<MediaCopy> mediaCopyList = daoMediaCopy.findCheckedoutByTitle(title);
            assertNotNull("mediaCopy not found in db", mediaCopyList);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }
}
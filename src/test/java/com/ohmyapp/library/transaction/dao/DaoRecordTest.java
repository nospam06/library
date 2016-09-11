package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.TransactionFactory;
import com.ohmyapp.library.transaction.entity.MediaCopy;
import com.ohmyapp.library.transaction.entity.Patron;
import com.ohmyapp.library.transaction.entity.Record;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Emerald on 8/27/2016.
 * test record dao
 */
public class DaoRecordTest extends DaoBaseTest {
    private static final Logger LOGGER = Logger.getLogger(DaoRecordTest.class);

    @Test
    public void save() {
        try {
            String randomString = new SimpleDateFormat("SSS").format(new Date());
            Record record = createRecord(randomString);
            assertNotNull(record);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void delete() {
        try {
            DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
            String randomString = new SimpleDateFormat("SSS").format(new Date());
            Record record = createRecord(randomString);
            assertNotNull(record);
            Long id = record.getId();
            daoRecord.delete(record);
            TransactionFactory.commit();
            // find it back in db
            Record deletedRecord = daoRecord.findById(id);
            assertNull("record did not get deleted from db", deletedRecord);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findById() {
        try {
            DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
            String randomString = new SimpleDateFormat("SSS").format(new Date());
            Record record = createRecord(randomString);
            assertNotNull(record);
            Long id = record.getId();
            // find it back in db
            Record foundRecord = daoRecord.findById(id);
            assertNotNull("record was not found in db", foundRecord);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByPatron() {
        try {
            DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
            String randomString = new SimpleDateFormat("SSS").format(new Date());
            Record record = createRecord(randomString);
            assertNotNull(record);
            Patron id = record.getPatron();
            // find it back in db
            List<Record> foundRecord = daoRecord.findByPatron(id);
            assertTrue("record was not found in db", !foundRecord.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByMediaCopy() {
        try {
            DaoRecord daoRecord = DaoFactory.getDao(DaoRecord.class);
            String randomString = new SimpleDateFormat("SSS").format(new Date());
            Record record = createRecord(randomString);
            assertNotNull(record);
            MediaCopy id = record.getMediaCopy();
            // find it back in db
            List<Record> foundRecord = daoRecord.findByMediaCopy(id);
            assertTrue("record was not found in db", !foundRecord.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }
}
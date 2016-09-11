package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.entity.Patron;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Emerald on 8/27/2016.
 * test Patron Dao
 */
public class DaoPatronTest extends DaoBaseTest {
    private static final Logger LOGGER = Logger.getLogger(DaoPatronTest.class);

    @Test
    public void save() {
        try {
            Patron newPatron = createPatron("Peter Pan");

            LOGGER.debug("new id = " + newPatron.getId());
            assertNotNull(newPatron.getId());
            assertEquals("Name must be Peter Pan", "Peter Pan", newPatron.getName());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void update() {
        try {
            DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
            Patron newPatron = createPatron("Peter");

            LOGGER.debug("new id = " + newPatron.getId());
            assertNotNull(newPatron.getId());
            // do update
            newPatron.setName("Paul");
            daoPatron.update(newPatron);
            assertEquals("Name must be updated", "Paul", newPatron.getName());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void delete() {
        try {
            DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
            Patron mary = createPatron("Mary");
            // find by id
            Patron patron = daoPatron.findById(mary.getId());
            assertNotNull("patron must be found in db", patron);
            daoPatron.delete(patron);
            // find by id
            Patron patron2 = daoPatron.findById(mary.getId());
            assertNull("patron must not be found in db", patron2);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findById() {
        try {
            DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
            Patron newPatron = createPatron("Peter");

            Long newId = newPatron.getId();
            LOGGER.debug("new id = " + newId);
            assertNotNull(newId);
            // find by id
            Patron foundPatron = daoPatron.findById(newId);
            assertNotNull("patron must be found in db", foundPatron);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByName() {
        try {
            DaoPatron daoPatron = DaoFactory.getDao(DaoPatron.class);
            // find by name
            List<Patron> partonList = daoPatron.findByName("Mary Lou");
            assertTrue("patron must not be found in db", partonList.isEmpty());

            Patron newPatron = createPatron("Mary");

            Long newId = newPatron.getId();
            LOGGER.debug("new id = " + newId);
            assertNotNull(newId);
            // find by name
            List<Patron> partonList2 = daoPatron.findByName("Mary");
            assertTrue("patron must be found in db", !partonList2.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }
}
package com.ohmyapp.library.transaction.dao;

import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.TransactionFactory;
import com.ohmyapp.library.transaction.entity.Media;
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
 * dao media test
 */
public class DaoMediaTest extends DaoBaseTest {
    private static final Logger LOGGER = Logger.getLogger(DaoMediaTest.class);

    @Test
    public void testSave() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            // test insert
            Media newMedia = createMedia(daoMedia);
            LOGGER.debug("new id = " + newMedia.getId());
            assertNotNull(newMedia.getId());
            // test update
            newMedia.setAuthor("you, me");
            newMedia.setTitle("Our Second Book");
            daoMedia.save(newMedia);
            TransactionFactory.commit();
            assertEquals("Author should be updated", newMedia.getAuthor(), "you, me");
            assertEquals("Title should be updated", newMedia.getTitle(), "Our Second Book");
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void testDelete() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            // test insert
            Media newMedia = createMedia(daoMedia);
            // delete it
            daoMedia.deleteBase(newMedia);
            TransactionFactory.commit();
            // find again, it should be null
            Media deletedMedia = daoMedia.findById(newMedia.getId());
            assertNull("media must not exists in db", deletedMedia);
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void testFindById() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            // test insert
            Media newMeida = createMedia(daoMedia);
            // find by id
            Media mediaById = daoMedia.findById(newMeida.getId());
            assertEquals("Id must the same", newMeida.getId(), mediaById.getId());
            assertEquals("Title must the same", "My First Book", mediaById.getTitle());
            assertEquals("Author must the same", "Me", mediaById.getAuthor());
            assertEquals("Format must the same", "book", mediaById.getFormat());
            assertEquals("Genre must the same", "comedy", mediaById.getGenre());
            assertEquals("Isbn must the same", "1234567890", mediaById.getIsbn());
            assertEquals("Copies must the same", 10, mediaById.getCopies());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByTitle() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            List<Media> mediaList = daoMedia.findByTitle("Star Trek");
            assertTrue("No media should be found", mediaList.isEmpty());

            List<Media> mediaList2 = daoMedia.findByTitle("Book");
            assertTrue("media should be found", !mediaList2.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByAuthor() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);

            List<Media> mediaList = daoMedia.findByAuthor("Star Wars");
            assertTrue("No media should be found", mediaList.isEmpty());

            List<Media> mediaList2 = daoMedia.findByAuthor("Me");
            assertTrue("media should be found", !mediaList2.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByFormat() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            List<Media> mediaList = daoMedia.findByFormat("Star Wars");
            assertTrue("No media should be found", mediaList.isEmpty());

            List<Media> mediaList2 = daoMedia.findByFormat("Book");
            assertTrue("media should be found", !mediaList2.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }

    @Test
    public void findByGenre() {
        try {
            DaoMedia daoMedia = DaoFactory.getDao(DaoMedia.class);
            List<Media> mediaList = daoMedia.findByGenre("Star Wars");
            assertTrue("No media should be found", mediaList.isEmpty());

            List<Media> mediaList2 = daoMedia.findByGenre("comedy");
            assertTrue("media should be found", !mediaList2.isEmpty());
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage(), e);
            fail();
        }
    }
}

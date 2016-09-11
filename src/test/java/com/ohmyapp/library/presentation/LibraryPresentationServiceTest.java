package com.ohmyapp.library.presentation;

import com.ohmyapp.library.presentation.service.LibraryPresentationService;
import com.ohmyapp.library.presentation.service.LibraryPresentationServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Emerald on 8/28/2016.
 * presentation layer test
 */
public class LibraryPresentationServiceTest {
    @Test
    public void findMedia() throws Exception {
        LibraryPresentationService service = new LibraryPresentationServiceImpl();
        String media = service.findMedia("Author 641");
        assertTrue(!media.isEmpty());
    }

    @Test
    public void findPatron() throws Exception {
        LibraryPresentationService service = new LibraryPresentationServiceImpl();
        String patron = service.findPatron("Peter");
        assertTrue(!patron.isEmpty());
    }
}
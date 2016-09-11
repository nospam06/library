package com.ohmyapp.library.presentation;

import com.ohmyapp.library.presentation.service.LibraryPresentationService;
import com.ohmyapp.library.presentation.service.LibraryPresentationServiceImpl;

/**
 * Created by Emerald on 8/28/2016.
 *
 */
public class PresentationFactory {
    private static final com.ohmyapp.library.presentation.service.LibraryPresentationService LibraryPresentationService = new LibraryPresentationServiceImpl();

    /**
     * Library Presentation Service Factory
     *
     * @return Library Presentation Service
     */
    public static LibraryPresentationService getLibraryPresentationService() {
        return LibraryPresentationService;
    }
}

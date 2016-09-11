package com.ohmyapp.library.service;

/**
 * Created by Emerald on 8/28/2016.
 * Librarian service factory
 */
public class LibraryServiceFactory {
    private static final LibrarianService librarianService = new LibrarianServiceImpl();

    /**
     * Library Service Factory
     *
     * @return Library Service
     */
    public static LibrarianService getLibrarianService() {
        return librarianService;
    }
}

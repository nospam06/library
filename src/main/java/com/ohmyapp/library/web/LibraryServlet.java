package com.ohmyapp.library.web;

import com.ohmyapp.library.presentation.PresentationFactory;
import com.ohmyapp.library.presentation.service.LibraryPresentationService;
import com.ohmyapp.library.service.LibraryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LibraryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LibraryPresentationService presentationService = PresentationFactory.getLibraryPresentationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        String search = req.getParameter("search");
        String pathInfo = req.getPathInfo();
        try {
            if (search != null && !search.trim().isEmpty()) {
                if ("/checkout/patron".equals(pathInfo)) {
                    json = presentationService.findPatron(search);
                }
                if ("/checkout/media".equals(pathInfo)) {
                    json = presentationService.findMedia(search);
                }
                if ("/checkin/mediacopy".equals(pathInfo)) {
                    json = presentationService.findCheckedoutMediaCopy(search);
                }
            } else {
                String[] paths = pathInfo.split("/");
                String id = paths[paths.length - 1];
                id = "undefined".equals(id) || "null".equals(id) ? "" : id;
                if (pathInfo.startsWith("/librarian/patron")) {
                    json = presentationService.getPatron(id);
                }
                if (pathInfo.startsWith("/librarian/media")) {
                    json = presentationService.getMedia(id);
                }
                if (pathInfo.startsWith("/librarian/record")) {
                    json = presentationService.getPatronRecord(id);
                }
            }
        } catch (LibraryException e) {
            throw new ServletException(e.getMessage(), e);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String json = "";
        String patronId = req.getParameter("patronId");
        String mediaId = req.getParameter("mediaId");
        String patron = req.getParameter("patron");
        String media = req.getParameter("media");
        try {
            String pathInfo = req.getPathInfo();
            if ("/checkout/record".equals(pathInfo) && patronId != null && !patronId.trim().isEmpty()
                    && mediaId != null && !mediaId.isEmpty()) {
                json = presentationService.checkout(patronId, mediaId);
            }
            if ("/librarian/savepatron".equals(pathInfo) && patron != null && !patron.trim().isEmpty()) {
                json = presentationService.savePatron(patron);
            }
            if ("/librarian/savemedia".equals(pathInfo) && media != null && !media.trim().isEmpty()) {
                json = presentationService.saveMedia(media);
            }
        } catch (LibraryException e) {
            throw new ServletException(e.getMessage(), e);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String json = "";
        String patron = req.getParameter("patron");
        String media = req.getParameter("media");
        String record = req.getParameter("record");
        try {
            String pathInfo = req.getPathInfo();
            if ("/librarian/savepatron".equals(pathInfo) && patron != null && !patron.trim().isEmpty()) {
                json = presentationService.savePatron(patron);
            }
            if ("/librarian/savemedia".equals(pathInfo) && media != null && !media.trim().isEmpty()) {
                json = presentationService.saveMedia(media);
            }
            if ("/checkin/record".equals(pathInfo) && record != null && !record.trim().isEmpty()) {
                json = presentationService.checkin(record);
            }
        } catch (LibraryException e) {
            throw new ServletException(e.getMessage(), e);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}

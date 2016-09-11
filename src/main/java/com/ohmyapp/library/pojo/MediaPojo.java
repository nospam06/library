package com.ohmyapp.library.pojo;

import java.util.List;

/**
 * Created by Emerald on 8/26/2016.
 * Media pojo
 */
public class MediaPojo implements Pojo {
    private Long id;
    private String title = "";
    private String author = "";
    private String format = "";
    private String genre = "";
    private String isbn = "";
    private int copies;
    private int available;

    private List<MediaCopyPojo> mediaCopies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public List<MediaCopyPojo> getMediaCopies() {
        return mediaCopies;
    }

    public void setMediaCopies(List<MediaCopyPojo> mediaCopies) {
        this.mediaCopies = mediaCopies;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}

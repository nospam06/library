package com.ohmyapp.library.transaction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by Emerald on 8/26/2016.
 * Media entity
 */
@NamedQueries
        ({
                @NamedQuery(name = "Media.findById", query = "select m from Media m where id=:id"),
                @NamedQuery(name = "Media.findByTitle", query = "select m from Media m where title like :title"),
                @NamedQuery(name = "Media.findByAuthor", query = "select m from Media m where author like :author"),
                @NamedQuery(name = "Media.findByFormat", query = "select m from Media m where format like :format"),
                @NamedQuery(name = "Media.findByGenre", query = "select m from Media m where genre like :genre")
        })
@Entity
public class Media implements Serializable {
    public static final String FIND_BY_ID = "Media.findById";
    public static final String FIND_BY_TITLE = "Media.findByTitle";
    public static final String FIND_BY_AUTHOR = "Media.findByAuthor";
    public static final String FIND_BY_FORMAT = "Media.findByFormat";
    public static final String FIND_BY_GENRE = "Media.findByGenre";
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private String format;
    private String genre;
    private String isbn;
    private int copies;

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
}

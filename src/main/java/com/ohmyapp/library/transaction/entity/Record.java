package com.ohmyapp.library.transaction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Emerald on 8/26/2016.
 * Check in / out record
 */@NamedQueries
        ({
                @NamedQuery(name = "Record.findById", query = "select m from Record m where id=:id"),
                @NamedQuery(name = "Record.findByPatron", query = "select m from Record m where patron = :patron"),
                @NamedQuery(name = "Record.findByMediaCopy", query = "select m from Record m where mediaCopy = :mediaCopy and returned = :returned")
        })
@Entity
public class Record implements Serializable {
    public static final String FIND_BY_ID = "Record.findById";
    public static final String FIND_BY_PATRON = "Record.findByPatron";
    public static final String FIND_BY_MEDIACOPY = "Record.findByMediaCopy";
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patronId", referencedColumnName = "id")
    private Patron patron;
    @ManyToOne
    @JoinColumn(name = "mediaCopyId", referencedColumnName = "id")
    private MediaCopy mediaCopy;
    private Date checkout;
    private Date due;
    private Date checkin;
    private boolean returned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public MediaCopy getMediaCopy() {
        return mediaCopy;
    }

    public void setMediaCopy(MediaCopy mediaCopy) {
        this.mediaCopy = mediaCopy;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}

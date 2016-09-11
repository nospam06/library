package com.ohmyapp.library.pojo;

import java.util.Date;

/**
 * Created by Emerald on 8/26/2016.
 * Check in / out record
 */
public class RecordPojo implements Pojo {
    private Long id;
    private PatronPojo patronPojo;
    private MediaPojo mediaPojo;
    private Date checkout;
    private Date due;
    private Date checkin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatronPojo getPatronPojo() {
        return patronPojo;
    }

    public void setPatronPojo(PatronPojo patron) {
        this.patronPojo = patron;
    }

    public MediaPojo getMediaPojo() {
        return mediaPojo;
    }

    public void setMediaPojo(MediaPojo mediaPojo) {
        this.mediaPojo = mediaPojo;
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
}

package com.ohmyapp.library.pojo;

/**
 * Created by Emerald on 8/26/2016.
 * Media Copy pojo
 */
public class MediaCopyPojo implements Pojo {
    private Long id;
    private String barcode;
    private String status;
    private MediaPojo mediaPojo;
    private RecordPojo recordPojo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MediaPojo getMediaPojo() {
        return mediaPojo;
    }

    public void setMediaPojo(MediaPojo mediaPojo) {
        this.mediaPojo = mediaPojo;
    }

    public RecordPojo getRecordPojo() {
        return recordPojo;
    }

    public void setRecordPojo(RecordPojo recordPojo) {
        this.recordPojo = recordPojo;
    }
}

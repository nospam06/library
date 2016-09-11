package com.ohmyapp.library.transaction.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Emerald on 8/26/2016.
 * Media Copy
 */
@NamedQueries
        ({
                @NamedQuery(name = "MediaCopy.findById", query = "select m from MediaCopy m where id=:id"),
                @NamedQuery(name = "MediaCopy.findByBarcode", query = "select m from MediaCopy m where barcode like :barcode"),
                @NamedQuery(name = "MediaCopy.findByMedia", query = "select m from MediaCopy m where parent = :parent"),
                @NamedQuery(name = "MediaCopy.findByStatusTitle", query = "select m from MediaCopy m where status = :status and parent.title like :title"),
                @NamedQuery(name = "MediaCopy.findCheckedoutByBarcode", query = "select m from MediaCopy m where record is not null and barcode like :barcode"),
                @NamedQuery(name = "MediaCopy.findCheckedoutByTitle", query = "select m from MediaCopy m where record is not null and parent.title like :title")
        })
@Entity
public class MediaCopy implements Serializable {
    public static final String FIND_BY_ID = "MediaCopy.findById";
    public static final String FIND_BY_BARCODE = "MediaCopy.findByBarcode";
    public static final String FIND_BY_MEDIA = "MediaCopy.findByMedia";
    public static final String FIND_BY_STATUS_TITLE = "MediaCopy.findByStatusTitle";
    public static final String FIND_CHECKEDOUT_BY_BARCODE = "MediaCopy.findCheckedoutByBarcode";
    public static final String FIND_CHECKEDOUT_BY_TITLE = "MediaCopy.findCheckedoutByTitle";
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private Media parent;
    private String barcode;
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recordId", referencedColumnName = "id")
    private Record record;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Media getParent() {
        return parent;
    }

    public void setParent(Media parent) {
        this.parent = parent;
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

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}

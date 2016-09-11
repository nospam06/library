package com.ohmyapp.library.transaction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by Emerald on 8/26/2016.
 * library Patron
 */
@NamedQueries
        ({
                @NamedQuery(name = "Patron.findById", query = "select m from Patron m where id=:id"),
                @NamedQuery(name = "Patron.findByName", query = "select m from Patron m where name like :name")
        })
@Entity
public class Patron implements Serializable {
    public static final String FIND_BY_ID = "Patron.findById";
    public static final String FIND_BY_NAME = "Patron.findByName";
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

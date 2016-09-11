package com.ohmyapp.library.pojo;

/**
 * Created by Emerald on 8/26/2016.
 * library Patron
 */

public class PatronPojo implements Pojo {
    private Long id;
    private String name = "";

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

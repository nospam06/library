package com.ohmyapp.library.presentation.data;

import com.ohmyapp.library.pojo.Pojo;

import java.util.List;

/**
 * Created by Emerald on 8/28/2016.
 * response
 */
public class Response implements Pojo {
    private List<Pojo> data;

    public List<Pojo> getData() {
        return data;
    }

    public void setData(List<Pojo> data) {
        this.data = data;
    }
}

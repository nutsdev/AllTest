package com.nutsdev.alltest.data.entities;

import java.io.Serializable;

public class Media implements Serializable {

    public static final int MEDIA_TYPE_PHOTO = 0;
    public static final int MEDIA_TYPE_VIDEO = 1;

    public Integer mediaType;

    public String url;

    public Media(Integer mediaType, String url) {
        this.mediaType = mediaType;
        this.url = url;
    }

}

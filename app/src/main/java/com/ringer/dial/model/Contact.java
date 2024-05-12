package com.ringer.dial.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;


public class Contact extends BaseObservable {

    private String name;
    private String phoneNumber;
    private String photoUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public static void loadImage(ImageView view, String imageUrl) {

    }
}

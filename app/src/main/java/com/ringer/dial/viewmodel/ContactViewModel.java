package com.ringer.dial.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;

import com.ringer.dial.model.Contact;
import com.ringer.dial.model.ContactRepository;

import java.util.List;

public class ContactViewModel extends BaseObservable {

    private ObservableArrayList<Contact> contacts;
    private ContactRepository repository;

    public ContactViewModel(Context context) {
        contacts = new ObservableArrayList<>();
        repository = new ContactRepository(context);
    }

    public List<Contact> getContacts() {
        contacts.addAll(repository.fetchContacts());
        return contacts;
    }
}

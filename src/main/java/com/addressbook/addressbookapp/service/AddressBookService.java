package com.addressbook.addressbookapp.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.addressbook.addressbookapp.model.Contact;
import com.addressbook.addressbookapp.repository.ContactRepository;
@Service
public class AddressBookService {

    @Autowired
    private ContactRepository repository;
    //UC-2
    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }
    
    public List<Contact> getContacts() {
        return repository.findAll();
    }
     //UC-3
    public Contact editContact(Integer id, Contact updatedContact) {

        Contact contact = repository.findById(id).orElse(null);

        if(contact != null) {
            contact.setFirstName(updatedContact.getFirstName());
            contact.setLastName(updatedContact.getLastName());
            contact.setAddress(updatedContact.getAddress());
            contact.setCity(updatedContact.getCity());
            contact.setState(updatedContact.getState());
            contact.setZip(updatedContact.getZip());
            contact.setPhone(updatedContact.getPhone());
            contact.setEmail(updatedContact.getEmail());

            return repository.save(contact);
        }

        return null;
    }
    //UC-4
    public void deleteContact(Integer id) {
        repository.deleteById(id);
    }
}
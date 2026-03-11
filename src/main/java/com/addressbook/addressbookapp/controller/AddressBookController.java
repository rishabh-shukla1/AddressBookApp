package com.addressbook.addressbookapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.addressbookapp.model.Contact;
import com.addressbook.addressbookapp.service.AddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/home")
    public String greet() {
        return "Welcome To Address Book";
    }

    @PostMapping("/add")
    public Contact addContact(@RequestBody Contact contact) {
        return service.addContact(contact);
    }

    @GetMapping("/all")
    public List<Contact> getContacts() {
        return service.getContacts();
    }

    @PutMapping("/edit/{id}")
    public Contact editContact(@PathVariable Integer id,
                               @RequestBody Contact contact) {

        return service.editContact(id, contact);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Integer id) {

        service.deleteContact(id);
    }
}
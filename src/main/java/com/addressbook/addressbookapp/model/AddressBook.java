package com.addressbook.addressbookapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Contact> contacts;

    public AddressBook(){}

    public Integer getId() 
    {
    	return id; 
    }
    public void setId(Integer id) 
    { 
    	this.id = id; 
    }

    public String getName() 
    { 
    	return name; 
    }
    public void setName(String name) 
    { 
    	this.name = name;
    }

    public List<Contact> getContacts()
    { 
    	return contacts;
    }
    public void setContacts(List<Contact> contacts) 
    {
    	this.contacts = contacts;
    }
}
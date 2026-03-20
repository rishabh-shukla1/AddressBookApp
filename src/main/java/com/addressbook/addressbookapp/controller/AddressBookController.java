package com.addressbook.addressbookapp.controller;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.model.Contact;
import com.addressbook.addressbookapp.service.AddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/home")
    public String greet(){
        return "Welcome To Address Book";
    }

    //UC-5 Create AddressBook
    @PostMapping("/createBook")
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook){
        return service.createAddressBook(addressBook);
    }

    //UC-1 Add Contact
    @PostMapping("/{bookId}/addContact")
    public Contact addContact(@PathVariable Integer bookId,
                              @RequestBody Contact contact){

        return service.addContact(bookId, contact);
    }
    // Get Contacts according to the addressbook type
    @GetMapping("/{bookId}/contacts")
    public List<Contact> getContactsByAddressBook(@PathVariable Integer bookId){
        return service.getContactsByAddressBook(bookId);
    }

    //UC-2 Get All Contacts
    @GetMapping("/all")
    public List<Contact> getContacts(){
        return service.getContacts();
    }

    //UC-3 Edit Contact
    @PutMapping("/edit/{id}")
    public Contact editContact(@PathVariable Integer id,
                               @RequestBody Contact contact){

        return service.editContact(id, contact);
    }

    // UC-4 Delete Contact
    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Integer id){
        service.deleteContact(id);
    }

    //UC-6 Add Multiple Contacts
    @PostMapping("/{bookId}/addMultiple")
    public List<Contact> addMultipleContacts(@PathVariable Integer bookId,@RequestBody List<Contact> contacts){
        return service.addMultipleContacts(contacts);
    }
    
    //UC-8 search by city
    @GetMapping("/search/city/{city}")
    public List<Contact> searchByCity(@PathVariable String city){
        return service.searchByCity(city);
    }
    
    //UC-8 search by state
    @GetMapping("/search/state/{state}")
    public List<Contact> searchByState(@PathVariable String state){
        return service.searchByState(state);
    }
    //UC-9
    @GetMapping("/view/city")
    public Map<String, List<Contact>> getContactsByCity(){
        return service.getContactsByCity();
    }
    //UC-9
    @GetMapping("/view/state")
    public Map<String, List<Contact>> getContactsByState(){
        return service.getContactsByState();
    }
    
    //UC-10 Number of contact person according to city 
    @GetMapping("/count/city")
    public Map<String, Long> countByCity(){
        return service.countByCity();
    }
    
    //UC-10 Number of contact person according to state
    @GetMapping("/count/state")
    public Map<String, Long> countByState(){
        return service.countByState();
    }
    
    //UC-11 sort by first name
    @GetMapping("/sort/name")
    public List<Contact> sortByName(){
        return service.sortByName();
    }
    //UC-11 sort by full name
    @GetMapping("/sort/fullname")
    public List<Contact> sortByFullName(){
        return service.sortByFullName();
    }
    //UC-12
    @GetMapping("/sort/city")
    public List<Contact> sortByCity(){
        return service.sortByCity();
    }
    //UC-12
    @GetMapping("/sort/state")
    public List<Contact> sortByState(){
        return service.sortByState();
    }
    //UC-12
    @GetMapping("/sort/zip")
    public List<Contact> sortByZip(){
        return service.sortByZip();
    }
    
    
    
    
}
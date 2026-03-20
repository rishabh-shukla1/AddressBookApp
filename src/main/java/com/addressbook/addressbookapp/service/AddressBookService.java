package com.addressbook.addressbookapp.service;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressbook.addressbookapp.exception.ContactNotFoundException;
import com.addressbook.addressbookapp.exception.DuplicateContactException;
import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.model.Contact;
import com.addressbook.addressbookapp.repository.AddressBookRepository;
import com.addressbook.addressbookapp.repository.ContactRepository;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactRepository contactRepository;

    // UC6 Create AddressBook
    public AddressBook createAddressBook(AddressBook addressBook){
        return addressBookRepository.save(addressBook);
    }

    // UC2 Add Contact
    public Contact addContact(Integer bookId, Contact contact){

        AddressBook addressBook = addressBookRepository.findById(bookId)
                .orElseThrow(() -> new ContactNotFoundException("AddressBook not found"));

        //UC7 Duplicate Check using Streams
        boolean isDuplicate = addressBook.getContacts().stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase(contact.getFirstName())
                        && c.getLastName().equalsIgnoreCase(contact.getLastName()));

        if(isDuplicate){
            throw new DuplicateContactException("Duplicate contact found in this AddressBook");
        }

        contact.setAddressBook(addressBook);

        return contactRepository.save(contact);
    }
    
    //get contact according to addressbook id
    
    public List<Contact> getContactsByAddressBook(Integer bookId){

        return contactRepository.findByAddressBookId(bookId);

    }

    // Get All Contacts
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    // UC3 Edit Contact
    public Contact editContact(Integer id, Contact updatedContact){

        Optional<Contact> optionalContact = contactRepository.findById(id);

        if(optionalContact.isEmpty()){
            throw new ContactNotFoundException("Contact not found with id: "+id);
        }

        Contact contact = optionalContact.get();

        contact.setFirstName(updatedContact.getFirstName());
        contact.setLastName(updatedContact.getLastName());
        contact.setAddress(updatedContact.getAddress());
        contact.setCity(updatedContact.getCity());
        contact.setState(updatedContact.getState());
        contact.setZip(updatedContact.getZip());
        contact.setPhone(updatedContact.getPhone());
        contact.setEmail(updatedContact.getEmail());

        return contactRepository.save(contact);
    }

    // UC4 Delete Contact
    public void deleteContact(Integer id){

        if(!contactRepository.existsById(id)){
            throw new ContactNotFoundException("Contact not found with id: "+id);
        }

        contactRepository.deleteById(id);
    }

    // UC5 Add Multiple Contacts
    public List<Contact> addMultipleContacts(List<Contact> contacts){
        return contactRepository.saveAll(contacts);
    }
    // UC8- search by city
    public List<Contact> searchByCity(String city){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .toList();
    }
    //UC8- search by state
    public List<Contact> searchByState(String state){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .toList();
    }
    
    // UC-10
    public Map<String, Long> countByCity(){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .collect(Collectors.groupingBy(
                        Contact::getCity,
                        Collectors.counting()
                ));
    }
    
    //UC-9
    public Map<String, List<Contact>> getContactsByCity(){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .collect(Collectors.groupingBy(Contact::getCity));
    }
    
    //UC-9
    
    public Map<String, List<Contact>> getContactsByState(){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .collect(Collectors.groupingBy(Contact::getState));
    }
   
    //UC-10
    public Map<String, Long> countByState(){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .collect(Collectors.groupingBy(
                        Contact::getState,
                        Collectors.counting()
                ));
    }
    
    //UC-11 sort by first name
    public List<Contact> sortByName(){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName))
                .toList();
    }
    
    //UC-11 sort by full name
    public List<Contact> sortByFullName(){

        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .sorted(Comparator.comparing(
                        c -> c.getFirstName() + " " + c.getLastName()
                ))
                .toList();
    }
    
    //UC-12 
    public List<Contact> sortByCity(){

        return contactRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(
                        Contact::getCity,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .toList();
    }
    //UC-12
    public List<Contact> sortByState(){

        return contactRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(
                        Contact::getState,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .toList();
    }
    //UC-12
    public List<Contact> sortByZip(){

        return contactRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .toList();
    }
    
    
}
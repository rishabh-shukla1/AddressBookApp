package com.addressbook.addressbookapp.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.addressbook.addressbookapp.model.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer>{

    List<Contact> findByAddressBookId(Integer bookId);
    
    boolean existsByFirstNameAndLastNameAndAddressBookId(String firstName, String lastName, Integer addressBookId);

}
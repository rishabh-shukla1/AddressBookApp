package com.addressbook.addressbookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addressbook.addressbookapp.model.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer>{

}

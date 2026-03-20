package com.addressbook.addressbookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.addressbook.addressbookapp.model.AddressBook;

public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {

}
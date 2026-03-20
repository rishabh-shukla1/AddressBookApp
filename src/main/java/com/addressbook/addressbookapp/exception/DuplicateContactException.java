package com.addressbook.addressbookapp.exception;

public class DuplicateContactException extends RuntimeException {

    public DuplicateContactException(String message){
        super(message);
    }
}

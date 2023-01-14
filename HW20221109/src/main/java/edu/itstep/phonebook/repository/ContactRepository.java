package edu.itstep.phonebook.repository;

import edu.itstep.phonebook.entity.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> getAllContacts_Repository();

    void saveOrUpdateContact_Repository(Contact contact);

    void deleteContactById_Repository(int id);

    Contact getContactById_Repository(int id);
}

package edu.itstep.phonebook.repository;

import edu.itstep.phonebook.entity.Phone;

import java.util.List;

public interface PhoneRepository {

// БЛОК методів, що працюють виключно з 1 таблицею Phone - вихід на інф про пов'язані контакти неможливий
    List<Phone> getAllPhones_Repository();

// НЕКОРЕКТНИЙ МЕТОД - додає телефон без прив'язки до контакта, при редагуванні у поле з id контакта вносить null
//    void saveOrUpdatePhone_Repository(Phone phone);

    void deletePhoneById_Repository(int id);

    Phone getPhoneById_Repository(int id);


// БЛОК - методів, що забезпечують коректну роботу БД --------------------------
    List<Phone> getAllContactPhones_Repository(int contactId);
    Phone getContactsPhoneById_Repository(int contactId, int phoneId);
    void saveContactsPhone_Repository(int contactId, Phone phone);
    void deleteContactsPhoneById_Repository(int contactId, int phoneId);
    void updateContactsPhone_Repository(int contactId, Phone phone);

}

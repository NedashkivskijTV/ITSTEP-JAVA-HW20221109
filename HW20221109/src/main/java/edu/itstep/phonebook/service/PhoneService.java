package edu.itstep.phonebook.service;

import edu.itstep.phonebook.entity.Phone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // спеціалізація анотації @Component – Spring @Service використовується з класами, що надають певні бізнес-функції. SpringContext автоматично визначає ці класи при використанні конфігурації на основі анотацій та сканнування шляху до класів
public interface PhoneService {

    //Методи працюють з даними лише таблиці телефонів !!! -------
    List<Phone> getAllPhones();

    // НЕКОРЕКТНИЙ МЕТОД - додає телефон без прив'язки до контакта, при редагуванні у поле з id контакта вносить null
    //void saveOrUpdatePhone(Phone phone);

    void deletePhoneById(int id);

    Phone getPhoneById(int id);

    //Методи працюють з даними усіх таблиць !!! -------
    List<Phone> getAllContactPhones(int contactId);
    Phone getContactsPhoneById(int contactId, int phoneId);
    void saveContactsPhone(int contactId, Phone phone);
    void deleteContactsPhoneById(int contactId, int phoneId);
    void updateContactsPhone(int contactId, Phone phone);

}

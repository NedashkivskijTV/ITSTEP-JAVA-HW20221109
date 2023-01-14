package edu.itstep.phonebook.service;

import edu.itstep.phonebook.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // спеціалізація анотації @Component (простір імен import org.springframework.stereotype.Service) – Spring @Service використовується з класами, що надають певні бізнес-функції. SpringContext автоматично визначає ці класи при використанні конфігурації на основі анотацій та сканнування шляху до класів
public interface ContactService {

    List<Contact> getAllContacts();

    void saveOrUpdateContact(Contact contact);

    void deleteContactById(int id);

    Contact getContactById(int id);
}

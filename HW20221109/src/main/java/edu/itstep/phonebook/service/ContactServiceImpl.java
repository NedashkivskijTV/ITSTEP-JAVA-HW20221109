package edu.itstep.phonebook.service;

import edu.itstep.phonebook.entity.Contact;
import edu.itstep.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service // спеціалізація анотації @Component (простір імен import org.springframework.stereotype.Service) – Spring @Service використовується з класами, що надають певні бізнес-функції. SpringContext автоматично визначає ці класи при використанні конфігурації на основі анотацій та сканнування шляху до класів
public class ContactServiceImpl implements ContactService {

    @Autowired // на сетер/конструктор/поле, вказує на створення залежності за допомогою даного сетера/конструктора/поля.
    private ContactRepository contactRepository; // впровадження залежності ContactRepository (створюється фреймворком – створено відповідний бін, але потрібно впровадити) – потрібно додати відповідне поле класу до якого додати анотацію @Autowired


    /**
     * Отримання усіх контактів
     * @return
     */
    @Override
    @Transactional
    public List<Contact> getAllContacts() {
        return contactRepository.getAllContacts_Repository();
    }


    /**
     * Додавання / редагування контакта
     * @param contact
     */
    @Override
    @Transactional
    public void saveOrUpdateContact(Contact contact) {
        contactRepository.saveOrUpdateContact_Repository(contact);
    }


    /**
     * Видалення контакта за id
     * @param id
     */
    @Override
    @Transactional
    public void deleteContactById(int id) {
        contactRepository.deleteContactById_Repository(id);
    }


    /**
     * Отримання контакту за id
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Contact getContactById(int id) {
        return contactRepository.getContactById_Repository(id);
    }
}

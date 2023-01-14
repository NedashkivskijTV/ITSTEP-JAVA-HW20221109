package edu.itstep.phonebook.service;

import edu.itstep.phonebook.entity.Phone;
import edu.itstep.phonebook.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service // спеціалізація анотації @Component (простір імен import org.springframework.stereotype.Service) – Spring @Service використовується з класами, що надають певні бізнес-функції. SpringContext автоматично визначає ці класи при використанні конфігурації на основі анотацій та сканнування шляху до класів
public class PhoneServiceImpl implements PhoneService{

    @Autowired // на сетер/конструктор/поле, вказує на створення залежності за допомогою даного сетера/конструктора/поля.
    private PhoneRepository phoneRepository; // впровадження залежності PhoneRepository (створюється фреймворком – створено відповідний бін, але потрібно впровадити)

    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public List<Phone> getAllPhones() {
        return phoneRepository.getAllPhones_Repository();
    }

    // Метод працює з даними усіх таблиць !!! -------
    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public List<Phone> getAllContactPhones(int contactId) {
        return phoneRepository.getAllContactPhones_Repository(contactId);
    }

// НЕКОРЕКТНИЙ МЕТОД - додає телефон без прив'язки до контакта, при редагуванні у поле з id контакта вносить null
//    @Override
//    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
//    public void saveOrUpdatePhone(Phone phone) {
//        phoneRepository.saveOrUpdatePhone_Repository(phone);
//    }

    // Метод працює з даними усіх таблиць !!! -------
    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public void saveContactsPhone(int contactId, Phone phone) {
        phoneRepository.saveContactsPhone_Repository(contactId, phone);
    }
    // Метод працює з даними усіх таблиць !!! -------
    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public void updateContactsPhone(int contactId, Phone phone) {
        phoneRepository.updateContactsPhone_Repository(contactId, phone);
    }

    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public void deletePhoneById(int id) {
        phoneRepository.deletePhoneById_Repository(id);
    }


    // Метод працює з даними усіх таблиць !!! -------
    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public void deleteContactsPhoneById(int contactId, int phoneId) {
        phoneRepository.deleteContactsPhoneById_Repository(contactId, phoneId);
    }

    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public Phone getPhoneById(int id) {
        return phoneRepository.getPhoneById_Repository(id);
    }

    @Override
    @Transactional // анотація для підключення біна HibernateTransactionManager що здійснюватиме відкриття та закриття сесії - додається простір імен javax.transaction.Transactional
    public Phone getContactsPhoneById(int contactId, int phoneId) {
        return phoneRepository.getContactsPhoneById_Repository(contactId, phoneId);
    }
}

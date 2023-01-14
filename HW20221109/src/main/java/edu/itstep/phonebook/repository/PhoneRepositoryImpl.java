package edu.itstep.phonebook.repository;

import edu.itstep.phonebook.entity.Contact;
import edu.itstep.phonebook.entity.Phone;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // – вказує на конкретний клас, який взаємодіятиме з БД
public class PhoneRepositoryImpl implements PhoneRepository{

    @Autowired // на сетер/конструктор/поле, вказує на створення залежності за допомогою даного сетера/конструктора/поля
    private SessionFactory sessionFactory; // впровадження залежність SessionFactory - За допомогою DI & IoC (створюється фреймворком – створено відповідний бін, але потрібно впровадити)

    // Метод праює лише з таблицею Phone
    /**
     * Репозиторій - Отримання усіх об'єктів з таблиці phones / Phone
     * @return
     */
    @Override
    public List<Phone> getAllPhones_Repository() {
        return sessionFactory
                .getCurrentSession() // отримання сесії (відкриття та закриття сесії здійснюватиме бін HibernateTransactionManager (описаний у applicationContext.xml, якщо кеонфігураційний файл налаштовується за допомогою xml) для підключення якого потрібно у класі-шмплементації сервісу додати до відповідного методу аномацію @Transactional)
                .createQuery("from Phone", Phone.class) // запит на отримання усіх даних з таблиці - стврюється об'єкт Query<Phone> = createQuery("from Phone", Phone.class) -спеціальний об'єкт для створення запиту
                .getResultList(); // директива на отримання результатів запиту у вигляді списку
    }


    // Метод працює з даними усіх таблиць !!! ------------------------------------
    /**
     * Репозиторій - Отримання усіх телефонів, що пов'язані з конкретним контактом (за id)
     * @param contactId
     * @return
     */
    @Override
    public List<Phone> getAllContactPhones_Repository(int contactId) {
        return sessionFactory
                .getCurrentSession() // отримання сесії (відкриття та закриття сесії здійснюватиме бін HibernateTransactionManager (описаний у applicationContext.xml, якщо кеонфігураційний файл налаштовується за допомогою xml) для підключення якого потрібно у класі-шмплементації сервісу додати до відповідного методу аномацію @Transactional)
                .createQuery("from Phone where contact.id = " + contactId, Phone.class) // запит на отримання усіх даних з таблиці - стврюється об'єкт Query<Phone> = createQuery("from Phone", Phone.class) -спеціальний об'єкт для створення запиту
                .getResultList(); // директива на отримання результатів запиту у вигляді списку
    }


// НЕКОРЕКТНИЙ МЕТОД - додає телефон без прив'язки до контакта, при редагуванні у поле з id контакта вносить null
//    @Override
//    public void saveOrUpdatePhone_Repository(Phone phone) {
//        System.out.println(phone);
//        sessionFactory
//                .getCurrentSession()
//                .saveOrUpdate(phone); // метод, що забезпечує збереження або оновлення даних у БД в залежності від наявності у об'єкта поля id - якщо ідентифікатор присутній, значить об'єкт вже існує у БД та потрібно його оновити
//    }


    // Метод працює з даними усіх таблиць !!! ---------------------------------------------------------
    /**
     * Репозиторій - Додавання телефону до списку конкретного контакта (за id)
     * @param contactId
     * @param phone
     */
    @Override
    public void saveContactsPhone_Repository(int contactId, Phone phone) {
        System.out.println(phone);
        Contact contact = sessionFactory.getCurrentSession().get(Contact.class, contactId);
        contact.addPhone(phone);
        phone.setContact(contact);

        sessionFactory
                .getCurrentSession()
                //.createQuery("insert into Contact (phones) select " + phone + " from Phone where id = " + contactId)
                //.createQuery("insert into Phone (phoneNumber) select phoneNumber from Phone")
                //.executeUpdate();
                .saveOrUpdate(contact);
    }


    // Метод працює з даними усіх таблиць !!! ---------------------------------------------------------
    /**
     * Репозиторій - Редагування телефону, пов'язаного з конкретним контактом (за id)
     * @param contactId
     * @param phone
     */
    @Override
    public void updateContactsPhone_Repository(int contactId, Phone phone) {
        System.out.println(phone);
        Contact contact = sessionFactory.getCurrentSession().get(Contact.class, contactId);
        for (Phone contactPhone : contact.getPhones()) {
            if(contactPhone.getId() == phone.getId()){
                contactPhone.setPhoneNumber(phone.getPhoneNumber());
            }
        }

        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(contact);
    }


    // Метод праює лише з таблицею Phone
    /**
     * Репозиторій - Видалення телефону за id
     * @param id
     */
    @Override
    public void deletePhoneById_Repository(int id) {
        sessionFactory
                .getCurrentSession()
                .createQuery("delete from Phone where id = " + id)
                .executeUpdate();
    }


    // Метод працює з даними усіх таблиць !!! -------------------------------------------------
    /**
     * Репозиторій - Видалення телефону (за id), через список телефонів конкретного контакта (за id)
     * @param contactId
     * @param phoneId
     */
    @Override
    public void deleteContactsPhoneById_Repository(int contactId, int phoneId) {
        sessionFactory
                .getCurrentSession()
                //.createQuery("delete from Phone where id = " + phoneId)  // також працює коректно, однак не має зв'язку з пов'язаним контактом
                .createQuery("delete from Phone where id = " + phoneId + "and contact.id = " + contactId)
                .executeUpdate();
    }


    // Метод праює лише з таблицею Phone
    /**
     * Репозиторій - Отримання телефону за id (без виходу на контакт, що з ним пов'язаний)
     * @param id
     * @return
     */
    @Override
    public Phone getPhoneById_Repository(int id) {
        return sessionFactory
                .getCurrentSession()
                .get(Phone.class, id);
    }


    // Метод працює з даними усіх таблиць !!! ------------------------------------------------------
    /**
     * Репозиторій - Отримання телефону (за id) з числа пов'язаних з конкретним контактом (за id)
     * @param contactId
     * @param phoneId
     * @return
     */
    @Override
    public Phone getContactsPhoneById_Repository(int contactId, int phoneId) {
        //System.out.println(contactId);
        //System.out.println(phoneId);
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Phone where contact.id = " + contactId + "and id = " + phoneId, Phone.class)
                .getSingleResult();
    }
}

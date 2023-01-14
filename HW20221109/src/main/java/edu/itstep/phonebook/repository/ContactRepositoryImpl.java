package edu.itstep.phonebook.repository;

import edu.itstep.phonebook.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired // на сетер/конструктор/поле, вказує на створення залежності за допомогою даного сетера/конструктора/поля
    private SessionFactory sessionFactory; // впровадження залежність SessionFactory - За допомогою DI & IoC (створюється фреймворком – створено відповідний бін, але потрібно впровадити)

    /**
     * Репозиторій - Виведення усіх контактів
     *
     * @return
     */
    @Override
    public List<Contact> getAllContacts_Repository() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Contact", Contact.class)
                .getResultList();
    }

    /**
     * Репозиторій - Додавання / редагування контакту
     *
     * @param contact
     */
    @Override
    public void saveOrUpdateContact_Repository(Contact contact) {
        System.out.println(contact);
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(contact);
    }

    /**
     * Репозиторій - Видалення контакта за id
     *
     * @param id
     */
    @Override
    public void deleteContactById_Repository(int id) {
        Contact contact = sessionFactory.getCurrentSession().get(Contact.class, id);
        if (contact != null) { // перевірка наявності об'єкта у БД (видаляється лише існуючий об'єкт)
            sessionFactory
                    .getCurrentSession()
                    .delete(contact);
        }

        // Інший варіант реалізації алгоритму видалення - через 1 сесію (теж потребує перевірок наявності об'єкта у БД)
//        Session session = sessionFactory.getCurrentSession();
//        Contact contact1 = session.get(Contact.class, id);
//        session.delete(contact1);
    }

    /**
     * Репозиторій - Отримання контакта за id
     *
     * @param id
     * @return
     */
    @Override
    public Contact getContactById_Repository(int id) {
        return sessionFactory.getCurrentSession().get(Contact.class, id);
    }
}

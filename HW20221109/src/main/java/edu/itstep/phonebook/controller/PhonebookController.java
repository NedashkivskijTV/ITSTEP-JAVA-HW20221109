package edu.itstep.phonebook.controller;

import edu.itstep.phonebook.entity.Contact;
import edu.itstep.phonebook.entity.Phone;
import edu.itstep.phonebook.service.ContactService;
import edu.itstep.phonebook.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// спеціальний контроллер, використовуваний у веб-сервисах RESTFul, и еквівалентний @Controller + @ResponseBody
public class PhonebookController {

    @Autowired
    // на сетер/конструктор/поле, вказує на створення залежності (зависимости) за допомогою даного сетера/конструктора/поля
    public PhoneService phoneService; // впровадження залежність PhoneService За допомогою DI & IoC (створюється фреймворком – створено відповідний бін, але потрібно впровадити) – додати відповідне поле класу до якого додати анотацію @Autowired

    @Autowired
    // на сетер/конструктор/поле, вказує на створення залежності (зависимости) за допомогою даного сетера/конструктора/поля
    public ContactService contactService; // впровадження залежність ContactService За допомогою DI & IoC (створюється фреймворком – створено відповідний бін, але потрібно впровадити) – додати відповідне поле класу до якого додати анотацію @Autowired


    // ============ Phone entity methods =====================================================================================================

    /**
     * Отримання усіх об'єктів з таблиці phones (без інф про пов'язані контакти)
     * Потреба у подібних методах під питанням
     *
     * @return
     */
    @GetMapping("/phones")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу GET
    public List<Phone> getAllPhones() {
        List<Phone> phones = phoneService.getAllPhones();
        return phones;
    }


    // Метод працює з даними усіх таблиць !!! -------
    /**
     * Отримання списку телефонів, пов'язаних з конкретним контактом (за id контакта)
     *
     * @param contactId
     * @return
     */
    @GetMapping("/contacts/{contactId}/phones")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу GET
    public List<Phone> getAllContactPhones(@PathVariable int contactId) {
        List<Phone> phones = phoneService.getAllContactPhones(contactId);
        return phones;
    }


    /**
     * Отримання телефону за id (без інф про пов'язані контакти)
     * Потреба у подібних методах під питанням
     *
     * @param phoneId
     * @return
     */
    @GetMapping("/phones/{phoneId}")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу GET
    public Phone getPhoneById(@PathVariable int phoneId) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах PATH у запиті
        Phone phone = phoneService.getPhoneById(phoneId);
        return phone;
    }


    // Метод працює з даними усіх таблиць !!! -------
    /**
     * Отримання телефона (за id телефону) з числа телефонів пов'язаних з конкретним контактом (за id контакта)
     *
     * @param contactId
     * @param phoneId
     * @return
     */
    @GetMapping("/contacts/{contactId}/phones/{phoneId}")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу GET
    public Phone getContactsPhoneById(@PathVariable int contactId, @PathVariable int phoneId) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах PATH у запиті
        Phone phone = phoneService.getContactsPhoneById(contactId, phoneId);
        return phone;
    }


//    /**
//     * Додавання телефону - НЕКОРЕКТНИЙ МЕТОД - додає телефон без прив'язки до контакта
//     * @param phone
//     * @return
//     */
//    @PostMapping("/phones") // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу POST
//    public Phone addPhone(@RequestBody Phone phone){
//        // @RequestBody – анотація, що дозволяє отримати дані з ТІЛА запиту (при POST запиті)
//        phoneService.saveOrUpdatePhone(phone);
//        return phone; // оскільки зберагіється екземпляр ссилочного типу даних та збереження відбуватиметься за посиланням, Hibernate додасть за цим посиланням id - повернений об'єкт вже матиме id за яким він збережений до БД
//    }


    // Метод працює з даними усіх таблиць !!! -------
    /**
     * Додавання телефону да списку конкретного контакта (за id контакту)
     *
     * @param contactId
     * @param phone
     * @return
     */
    @PostMapping("/contacts/{contactId}/phones")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу POST
    public Phone addContactsPhone(@PathVariable int contactId, @RequestBody Phone phone) {
        // @RequestBody – анотація, що дозволяє отримати дані з ТІЛА запиту (при POST запиті)
        phoneService.saveContactsPhone(contactId, phone);
        return phone; // оскільки зберагіється екземпляр ссилочного типу даних та збереження відбуватиметься за посиланням, Hibernate додасть за цим посиланням id - повернений об'єкт вже матиме id за яким він збережений до БД
    }


// НЕКОРЕКТНИЙ МЕТОД - при редагуванні у поле з id контакта вносить null
//    /**
//     * Оновлення телефону
//     * @param phone
//     * @return
//     */
//    @PutMapping("/phones") // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу PUT
//    public Phone updatePhone(@RequestBody Phone phone){
//        // @RequestBody – анотація, що дозволяє отримати дані з ТІЛА запиту (при POST запиті)
//        phoneService.saveOrUpdatePhone(phone);
//        return phone; // оскільки зберагіється екземпляр ссилочного типу даних та збереження відбуватиметься за посиланням, Hibernate додасть за цим посиланням id - повернений об'єкт вже матиме id за яким він збережений до БД
//    }


    // Метод працює з даними усіх таблиць !!! -------
    /**
     * Редагування телефону, пов'язаного з конкретним контактом (за id контакту)
     *
     * @param contactId
     * @param phone
     * @return
     */
    @PutMapping("/contacts/{contactId}/phones")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу PUT
    public Phone updateContactsPhone(@PathVariable int contactId, @RequestBody Phone phone) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах path у запиті
        // @RequestBody – анотація, що дозволяє отримати дані з ТІЛА запиту (при POST запиті)
        phoneService.updateContactsPhone(contactId, phone);
        return phone; // оскільки зберагіється екземпляр ссилочного типу даних та збереження відбуватиметься за посиланням, Hibernate додасть за цим посиланням id - повернений об'єкт вже матиме id за яким він збережений до БД
    }


    /**
     * Видалення телефону за id (без видалення контакта)
     * Потреба у подібних методах під питанням
     *
     * @param phoneId
     */
    @DeleteMapping("phones/{phoneId}")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу DELETE
    public void deletePhoneById(@PathVariable int phoneId) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах path у запиті
        phoneService.deletePhoneById(phoneId);
    }


    // Метод працює з даними усіх таблиць !!! -------
    /**
     * Видалення телефона (за id тел), що пов'язаний з конкретним контактом (за id контакту)
     *
     * @param contactId
     * @param phoneId
     */
    @DeleteMapping("/contacts/{contactId}/phones/{phoneId}")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу DELETE
    public void deleteContactPhoneById(@PathVariable int contactId, @PathVariable int phoneId) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах path у запиті
        phoneService.deleteContactsPhoneById(contactId, phoneId);
    }


    // ============ Contact entity methods =====================================================================================================

    /**
     * Отримання усіх об'єктів Contact
     *
     * @return
     */
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return contacts;
    }


    /**
     * Отримання контакту за id
     *
     * @param id
     * @return
     */
    @GetMapping("/contacts/{id}")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу GET
    public Contact getContactById(@PathVariable int id) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах  path у запиті
        Contact contact = contactService.getContactById(id);
        return contact;
    }


    /**
     * Додавання контакту - БЕЗ СПИСКА ТЕЛЕФОНІВ
     * телефони до контакта додаються у методі addContactsPhone()
     *
     * @param contact
     * @return
     */
    @PostMapping("/contacts")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу POST
    public Contact addContact(@RequestBody Contact contact) {
        // @RequestBody – анотація, що дозволяє отримати дані з тіла запиту (при POST запиті)
        contactService.saveOrUpdateContact(contact);
        return contact; // оскільки зберагіється екземпляр ссилочного типу даних та збереження відбуватиметься за посиланням, Hibernate додасть за цим посиланням id - повернений об'єкт вже матиме id за яким він збережений до БД
    }


    /**
     * Редагування контакту - БЕЗ редагування телефонів у списку
     *
     * @param contact
     * @return
     */
    @PutMapping("/contacts")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу PUT
    public Contact updateContact(@RequestBody Contact contact) {
        // @RequestBody – анотація, що дозволяє отримати дані з тіла запиту (при PUT запиті)
        contactService.saveOrUpdateContact(contact); // метод saveOrUpdateEmployee працює відповідно  до наявності/відсутності параметра id - у разі наявності -оновлює дані, у разі відсутності - зберігає новий об'єкт
        return contact; // повертається об'єкт після внесення змін
    }


    /**
     * Видалення контакту за id
     *
     * @param id
     */
    @DeleteMapping("/contacts/{id}")
    // спеціалізований варіант анотації @RequestMapping - забезпечує спрацювання методу при відправленні на вказану у атрибутах URL-адресу запиту типу DELETE
    //public void deleteContactById(@PathVariable int id) { // сигнатури методу без реалізації перевірок наявності/відсутності об'єкта в БД
    public String deleteContactById(@PathVariable int id) {
        // @PathVariable int id – анотація дозволяє отримати дані, що передаються у параметрах path у запиті

        String result; // змінна, що зберігає текст, який повертатиметься/відображатиметься у браузері
        if (getContactById(id) != null) { // перевірка наявності об'єкта з вказаним id у БД
            contactService.deleteContactById(id); // видалення об'єкта за id
            if (getContactById(id) == null) { // перевірка чи вдалим було видалення
                result = "Contact was deleted"; // повідомлення при вдалому видаленні
            } else {
                result = "Contact was not deleted"; // повідомлення при НЕвдалому видаленні
            }
        } else {
            result = "The id is not in the database"; // повідомлення у разі відсутності об'єкта з вказаним id у БД
        }
        return result;
    }

}

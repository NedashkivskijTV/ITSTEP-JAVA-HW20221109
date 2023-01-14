package edu.itstep.phonebook.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // позначає сутність - на основі даного об'єкта буде створено бін
@Table(name = "contacts") // зв'язок сутності з таблицею БД
public class Contact {

    @Id // позначає первинний ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // визначає стратегію генерації унікального ідентифікатора - id
    @Column(name = "id") // зв'язок поля з колонкою таблиці
    private int id;

    @Column(name = "first_name") // зв'язок поля з колонкою таблиці
    private String firstName;

    @Column(name = "last_name") // зв'язок поля з колонкою таблиці
    private String lastName;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "contact") // анотація встановлює вид зв'язків,
    // що використовуються між таблицями Contact та Phone -  слід розуміти, що має бути обрана анотація,
    // що відображає зв'язок від поточного класу (Contact) до пов'язаного класу (Phone),
    // тобто у одного контакта можуть бути декілька телефонів (у класі Phone буде обрано @ManyToOne)
    // ––– АТРИБУТ mappedBy = " contact" пов'язує даний клас з класом у якому зберігається
    // вторинний ключ - Phone та передається назва поля, у атрибуті @JoinColumn якого описується
    // принцип об'єднання таблиць (вторинний ключ)
    // ––– КАСКАДНІСТЬ - CascadeType.ALL - при видаленні контакта буде видалено усі пов'язані телефони
    // (загалом, при визначенні даної властивості потрібно виходити від ЗАДАЧІ!!!)
    // ––– АТРИБУТ fetch = FetchType.EAGER – встановлює підхід при спробі отримання об’єкта Contact
    // з пов’язаними сутностями Phone (за замовчуванням Hibernate використовує лінивий підхід до
    // виборки, тобто він не завантажує дочірні сутності при спробі отримання батьківської сутності –
    // генерується помилка LazyInitializationException),
    // одним зі шляхів виправлення проблеми є встановлення стратегії виборки разом з анотацією @OneToMany
    private List<Phone> phones = new ArrayList<>(); // поле для зберігання інф про пов'язані з даним класом сутності - у контакта може бути багато телефонів

    public Contact() {
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(Contact contact) {
        this.id = contact.getId();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * метод додавання телефону до контакта, також додає телефону інф про контакт
     * @param phone
     */
    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setContact(this);
    }

    public void addPhones(Phone... newPhones) {
        for (Phone phone : newPhones) {
            addPhone(phone);
        }
    }
}

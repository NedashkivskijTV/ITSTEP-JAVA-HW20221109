package edu.itstep.phonebook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity // – позначає сутність - на основі даного об'єкта буде створено бін
@Table(name = "phones") // зв'язок сутності з таблицею
public class Phone {

    @Id // позначає первинний ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // визначає стратегію генерації унікального ідентифікатора - id
    @Column(name = "id") // зв'язок поля з колонкою таблиці
    private int id;

    @Column(name = "phone_number") // зв'язок поля з колонкою таблиці
    private String phoneNumber;

    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) // вид зв'язків, що використовуються між таблицями
    //@JoinColumn(name = "contact_id") // принцип об'єднання таблиць (вторинний ключ) - по якому полю (колонка в таблиці phones в БД) таблиця Phone зв'язується з табл contacts (атрибут name = "contact_id" приймає назву колонки з БД)
    //@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) // вид зв'язків, що використовуються між таблицями
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) // вид зв'язків, що використовуються між таблицями
    //@JoinColumn(name = "contact_id", nullable = false) // принцип об'єднання таблиць (вторинний ключ) - по якому полю (колонка в таблиці phones в БД) таблиця Phone зв'язується з табл contacts (атрибут name = "contact_id" приймає назву колонки з БД)
    @JoinColumn(name = "contact_id") // принцип об'єднання таблиць (вторинний ключ) - по якому полю (колонка в таблиці phones в БД) таблиця Phone зв'язується з табл contacts (атрибут name = "contact_id" приймає назву колонки з БД)
    @JsonIgnore // використовується для уникнення появи при запиті нескінченного циклу (у телефона є контакт у якого є список телефонів у кожного з яких є ...)
    private Contact contact;

    public Phone() {
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

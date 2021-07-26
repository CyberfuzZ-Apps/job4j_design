package ru.job4j.serialization.jsonjava;

import org.json.JSONObject;

import java.util.Objects;

public class Contact {
    private String name;
    private String soname;
    private int age;
    private String phone;

    public Contact(String name, String soname, int age, String phone) {
        this.name = name;
        this.soname = soname;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return age == contact.age
                && Objects.equals(name, contact.name)
                && Objects.equals(soname, contact.soname)
                && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, soname, age, phone);
    }

    public String getName() {
        return name;
    }

    public String getSoname() {
        return soname;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "name='" + name + '\''
                + ", soname='" + soname + '\''
                + ", age=" + age
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) {
        final Contact contact = new Contact(
                "Evgeniy",
                "Zaytsev",
                20,
                "+7(926) 555-55-55");
        JSONObject jsonObject = new JSONObject(contact);
        System.out.println(jsonObject);
    }
}

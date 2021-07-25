package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String soname;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private String phone;

    public Contact() {
    }

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

    @Override
    public String toString() {
        return "Contact{"
                + "name='" + name + '\''
                + ", soname='" + soname + '\''
                + ", age=" + age
                + ", phone='" + phone + '\''
                + '}';
    }
}

package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {
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

    @Override
    public String toString() {
        return "Contact{"
                + "name='" + name + '\''
                + ", soname='" + soname + '\''
                + ", age=" + age
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact contact = new Contact(
                "Evgeniy",
                "Zaytsev",
                39,
                "+7 (926) 555-55-55");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(tempFile));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(tempFile))) {
            out.writeObject(contact);
            Contact contactFromFile = (Contact) in.readObject();
            System.out.println("\"contact\" equals \"contactFromFile\" = "
                    + contact.equals(contactFromFile));
        }
    }
}
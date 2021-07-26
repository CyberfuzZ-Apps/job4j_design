package ru.job4j.serialization.jsonjava;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;

public class Student {
    private boolean fullTime;
    private int yearOfStudy;
    private String name;
    private Contact contact;
    private String[] interests;

    public Student(boolean fullTime, int yearOfStudy, String name,
                   Contact contact, String[] interests) {
        this.fullTime = fullTime;
        this.yearOfStudy = yearOfStudy;
        this.name = name;
        this.contact = contact;
        this.interests = interests;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getInterests() {
        return interests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return fullTime == student.fullTime
                && yearOfStudy == student.yearOfStudy
                && Objects.equals(name, student.name)
                && Objects.equals(contact, student.contact)
                && Arrays.equals(interests, student.interests);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fullTime, yearOfStudy, name, contact);
        result = 31 * result + Arrays.hashCode(interests);
        return result;
    }

    @Override
    public String toString() {
        return "Student{"
                + "fullTime=" + fullTime
                + ", yearOfStudy=" + yearOfStudy
                + ", name='" + name + '\''
                + ", contact=" + contact
                + ", interests=" + Arrays.toString(interests)
                + '}';
    }

    public static void main(String[] args) {
        final Student student = new Student(
                true,
                3,
                "Evgeniy",
                new Contact(
                        "Evgeniy",
                        "Zaytsev",
                        20,
                        "+7(926) 555-55-55"),
                new String[]{"Coding", "Gaming"}
        );
        JSONObject jsonObject = new JSONObject(student);
        System.out.println(jsonObject);
    }
}

package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Contact contact = new Contact(
                "Evgeniy",
                "Zaytsev",
                20,
                "+7(926) 555-55-55");
        String[] interests = {"Coding", "Gaming"};
        Student student = new Student(
                true,
                3,
                "Evgeniy",
                contact, interests);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));
        String studentJson =
                           "{"
                                + "\"fullTime\":true,"
                                + "\"yearOfStudy\":3,"
                                + "\"name\":\"Evgeniy\","
                                + "\"contact\":"
                                    + "{"
                                       + "\"name\":\"Evgeniy\","
                                       + "\"soname\":\"Zaytsev\","
                                       + "\"age\":20,"
                                       + "\"phone\":\"+7(926) 555-55-55\""
                                    + "},"
                                + "\"interests\":"
                                    + "[\"Coding\",\"Gaming\"]"
                        + "}";
        Student studentFromJson = gson.fromJson(studentJson, Student.class);
        System.out.println(studentFromJson);
        System.out.println("\"student\" equals \"studentFromJson\" = "
                + student.equals(studentFromJson));
    }
}
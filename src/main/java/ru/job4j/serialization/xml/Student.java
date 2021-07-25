package ru.job4j.serialization.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private boolean fullTime;

    @XmlAttribute
    private int yearOfStudy;

    @XmlAttribute
    private String name;

    @XmlElement
    private Contact contact;

    @XmlElementWrapper(name = "interests")
    @XmlElement(name = "interest")
    private String[] interests;

    public Student() {
    }

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

    public static void main(String[] args) throws JAXBException, IOException {
        Student student = new Student(
                true,
                3,
                "Evgeniy",
                new Contact("Evgeniy", "Zaytsev", 20,
                        "+7(926) 555-55-55"),
                new String[]{"Coding", "Gaming"});
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Student result = (Student) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

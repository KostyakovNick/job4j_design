package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Contact {

    @XmlAttribute
    private String phone;

    public Contact() { }

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
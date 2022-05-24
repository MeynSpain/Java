package com.example.databasetest;

public class Enterpise  {
    Integer id;
    String name;
    String banking_details;
    String contact_person;

    public Enterpise(Integer id, String name, String banking_details, String contact_person) {
        this.id = id;
        this.name = name;
        this.banking_details = banking_details;
        this.contact_person = contact_person;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBanking_details() {
        return banking_details;
    }

    public String getContact_person() {
        return contact_person;
    }

    @Override
    public String toString() {
        return "Enterpise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", banking_details='" + banking_details + '\'' +
                ", contact_person='" + contact_person + '\'' +
                '}';
    }
}

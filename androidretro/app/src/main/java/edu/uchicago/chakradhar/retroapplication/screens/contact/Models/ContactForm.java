package edu.uchicago.chakradhar.retroapplication.screens.contact.Models;

public class ContactForm {

    private String name = "";
    private String body = "";
    private String email = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactForm() {
    }

    public ContactForm(String name, String body, String email) {
        this.name = name;
        this.body = body;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.softserve.academy.model.client;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String email;

    public Client(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Client() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("\nClient id: %d\nName: %s\nSurname: %s\nEmail: %s", this.getId(), this.getName(),
                this.getSurname(), this.getEmail());
    }
}

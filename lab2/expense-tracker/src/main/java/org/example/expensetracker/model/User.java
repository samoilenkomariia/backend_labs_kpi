package org.example.expensetracker.model;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class User {

    private UUID id;
    private String name;

    public User() {}

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return false;
        }
        User user;
        if (obj instanceof User) {
            user = (User) obj;
        } else return false;

        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "User" + getId() + ": " + this.name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.epam.trainings.mentoring.jpa.domain;

import javax.persistence.Entity;

@Entity
public class Author extends AbstractIdentified {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

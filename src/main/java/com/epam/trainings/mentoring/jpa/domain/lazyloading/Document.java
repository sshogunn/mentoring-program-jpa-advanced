package com.epam.trainings.mentoring.jpa.domain.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Document extends AbstractIdentified {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)//just to show EAGER
    @JoinColumn(name = "OWNER")
    private LazyPoliceman owner;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

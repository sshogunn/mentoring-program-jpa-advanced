package com.epam.trainings.mentoring.jpa.domain.orphanremoval;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TravelItem extends AbstractIdentified {
    private String name;
    @ManyToOne
    @JoinColumn(name = "OWNER")
    private Traveler owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Traveler getOwner() {
        return owner;
    }

    public void setOwner(Traveler owner) {
        this.owner = owner;
    }
}

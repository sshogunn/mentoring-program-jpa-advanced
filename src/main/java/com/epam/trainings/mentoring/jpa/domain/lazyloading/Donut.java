package com.epam.trainings.mentoring.jpa.domain.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Donut extends AbstractIdentified {
    @ManyToOne
    @JoinColumn(name = "OWNER")
    private LazyPoliceman owner;

    public LazyPoliceman getOwner() {
        return owner;
    }

    public void setOwner(LazyPoliceman owner) {
        this.owner = owner;
    }
}

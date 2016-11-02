package com.epam.trainings.mentoring.jpa.domain.lazyloading;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SUPER")
public class SuperPolicemanEquipment extends PolicemanEquipment {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

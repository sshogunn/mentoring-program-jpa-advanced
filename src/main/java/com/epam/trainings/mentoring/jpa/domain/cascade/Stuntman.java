package com.epam.trainings.mentoring.jpa.domain.cascade;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Stuntman extends AbstractIdentified {
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "stuntman", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})//we can use ALL, but it's demo
    private Set<EquipmentItem> equipment = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EquipmentItem> getEquipment() {
        return equipment;
    }

    public void setEquipment(Set<EquipmentItem> equipment) {
        this.equipment = equipment;
    }
}

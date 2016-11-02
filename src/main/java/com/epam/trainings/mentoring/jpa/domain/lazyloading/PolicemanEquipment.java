package com.epam.trainings.mentoring.jpa.domain.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "POLICEMAN_EQUIPMENT")
public class PolicemanEquipment extends AbstractIdentified {
    @ManyToOne
    @JoinColumn(name = "OWNER")
    private LazyPoliceman lazyPoliceman;

    public LazyPoliceman getLazyPoliceman() {
        return lazyPoliceman;
    }

    public void setLazyPoliceman(LazyPoliceman lazyPoliceman) {
        this.lazyPoliceman = lazyPoliceman;
    }
}

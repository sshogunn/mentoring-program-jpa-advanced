package com.epam.trainings.mentoring.jpa.domain.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LazyPoliceman extends AbstractIdentified {
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)//Lazy is default, just for demo
    private List<Document> documents = new ArrayList<>();
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Donut> donuts = new ArrayList<>();

    @OneToOne(mappedBy = "lazyPoliceman", fetch = FetchType.LAZY)
    private PolicemanEquipment policemanEquipment;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Donut> getDonuts() {
        return donuts;
    }

    public void setDonuts(List<Donut> donuts) {
        this.donuts = donuts;
    }

    public PolicemanEquipment getPolicemanEquipment() {
        return policemanEquipment;
    }

    public void setPolicemanEquipment(PolicemanEquipment policemanEquipment) {
        this.policemanEquipment = policemanEquipment;
    }
}

package com.epam.trainings.mentoring.jpa.domain.orphanremoval;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Traveler extends AbstractIdentified {
    private String name;
    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<TravelItem> travelItems = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TravelItem> getTravelItems() {
        return travelItems;
    }

    public void setTravelItems(List<TravelItem> travelItems) {
        this.travelItems = travelItems;
    }
}

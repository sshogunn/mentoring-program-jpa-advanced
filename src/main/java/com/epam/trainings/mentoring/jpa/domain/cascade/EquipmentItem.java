package com.epam.trainings.mentoring.jpa.domain.cascade;

import com.epam.trainings.mentoring.jpa.domain.AbstractIdentified;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EquipmentItem extends AbstractIdentified {
    @Column(name = "TITLE")
    private String tile;
    @ManyToOne
    @JoinColumn(name = "OWNER")
    private Stuntman stuntman;

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public Stuntman getStuntman() {
        return stuntman;
    }

    public void setStuntman(Stuntman stuntman) {
        this.stuntman = stuntman;
    }
}

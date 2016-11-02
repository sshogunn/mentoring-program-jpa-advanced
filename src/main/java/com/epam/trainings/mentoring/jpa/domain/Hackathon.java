package com.epam.trainings.mentoring.jpa.domain;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "getAllHackathons", query = "SELECT h from Hackathon h"),
        @NamedQuery(name = Hackathon.FIND_ALL_BY_NAME, query = "SELECT h from Hackathon h WHERE h.name = :name")
})
@NamedNativeQueries({//TRY TO AVOID USING NATIVE SQL
        @NamedNativeQuery(name = Hackathon.FIND_ALL_NAMES, query = "SELECT NAME FROM HACKATHON")
})
@Entity
@Table(name = "HACKATHON")
public class Hackathon extends AbstractIdentified {
    public static final String FIND_ALL_BY_NAME = "findAllByName";
    public static final String FIND_ALL_NAMES = "findAllNames";
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany
    private List<ProjectIdea> projectIdeas;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectIdea> getProjectIdeas() {
        return this.projectIdeas;
    }

    public void setProjectIdeas(List<ProjectIdea> projectIdeas) {
        this.projectIdeas = projectIdeas;
    }

}

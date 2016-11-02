package com.epam.trainings.mentoring.jpa.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROJECT_IDEA")
public class ProjectIdea extends AbstractIdentified {

	@Column(name = "NAME")
	private String name;

	@ManyToMany(mappedBy = "projectIdeas")
	private List<Hackathon> hackathons;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR")
	private Author author;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Hackathon> getHackathons() {
		return this.hackathons;
	}

	public void setHackathons(List<Hackathon> hackathons) {
		this.hackathons = hackathons;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}

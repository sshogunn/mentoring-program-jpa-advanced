package com.epam.trainings.mentoring.jpa.repository;

import java.util.List;

import com.epam.trainings.mentoring.jpa.domain.Hackathon;

public interface HackathonRepository {

	List<Hackathon> findAll();

	Hackathon findBy(long id);

	void save(Hackathon newInstance);

	void delete(long id);

	void delete(Hackathon hackathon);

	Hackathon update(Hackathon hackathon);
}

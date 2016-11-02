package com.epam.trainings.mentoring.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.trainings.mentoring.jpa.domain.Hackathon;

import org.springframework.stereotype.Repository;

@Repository
public class HackathonRepositoryImpl implements HackathonRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Hackathon> findAll() {
		return this.entityManager.createQuery("SELECT h FROM Hackathon h", Hackathon.class)
				.getResultList();
	}

	@Override
	public Hackathon findBy(long id) {
		return entityManager.find(Hackathon.class, id);
	}

	@Override
	public void save(Hackathon newInstance) {
		entityManager.persist(newInstance);
	}

	@Override
	public void delete(long id) {
		entityManager.remove(findBy(id));
	}

	@Override
	public void delete(Hackathon hackathon) {
		Hackathon managed = entityManager.merge(hackathon);
		entityManager.detach(managed);
	}

	@Override
	public Hackathon update(Hackathon hackathon) {
		return entityManager.merge(hackathon);
	}
}

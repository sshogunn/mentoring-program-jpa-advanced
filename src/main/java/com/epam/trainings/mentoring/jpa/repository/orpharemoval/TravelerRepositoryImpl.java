package com.epam.trainings.mentoring.jpa.repository.orpharemoval;

import com.epam.trainings.mentoring.jpa.domain.orphanremoval.Traveler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TravelerRepositoryImpl implements TravelerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void cleanTravelerItems(long id) {
        Traveler traveler = entityManager.find(Traveler.class, id);
        traveler.getTravelItems().clear();
        traveler.getTravelItems().forEach(i -> i.setOwner(null));
    }
}

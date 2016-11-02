package com.epam.trainings.mentoring.jpa.repository.cascade;

import com.epam.trainings.mentoring.jpa.domain.cascade.Stuntman;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class StuntmanRepositoryImpl implements StuntmanRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Stuntman newInstance) {
        entityManager.persist(newInstance);
    }

    @Override
    public void delete(Stuntman stuntman) {
        Stuntman managed = entityManager.merge(stuntman);
        entityManager.remove(managed);
    }

    @Override
    public Stuntman update(Stuntman stuntman) {
        return entityManager.merge(stuntman);
    }
}

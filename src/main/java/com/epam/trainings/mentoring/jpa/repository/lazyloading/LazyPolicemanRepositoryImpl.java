package com.epam.trainings.mentoring.jpa.repository.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.lazyloading.LazyPoliceman;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LazyPolicemanRepositoryImpl implements LazyPolicemanRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LazyPoliceman findBy(long id) {
        return entityManager.find(LazyPoliceman.class, id);
    }
}

package com.epam.trainings.mentoring.jpa.repository;

import com.epam.trainings.mentoring.jpa.domain.Hackathon;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class HackathonRepositoryImpl implements HackathonRepository {

    private static final String FIND_BY_NAME_AND_DESC = "SELECT h from Hackathon h WHERE h.name = :name AND h.description = :descr";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hackathon> findAll() {
        return this.entityManager.createQuery("SELECT h FROM Hackathon h", Hackathon.class)
                .getResultList();
    }

    //CRUD
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

    //JPQL
    @Override
    public List<Hackathon> findByNameAndDescription(String name, String description) {
        return entityManager.createQuery(FIND_BY_NAME_AND_DESC, Hackathon.class)
                .setParameter("name", name)
                .setParameter("descr", description)
                .getResultList();
    }

    @Override
    public List<Hackathon> findByName(String name) {
        return entityManager.createNamedQuery(Hackathon.FIND_ALL_BY_NAME, Hackathon.class)
                .setParameter("name", name)
                .getResultList();
    }

    @PostConstruct
    public void initQueries() {
        entityManagerFactory.addNamedQuery("NEW QUERY", entityManager.createQuery(FIND_BY_NAME_AND_DESC));
    }
}

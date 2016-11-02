package com.epam.trainings.mentoring.jpa.repository.cascade;

import com.epam.trainings.mentoring.jpa.domain.cascade.Stuntman;

public interface StuntmanRepository {
    void save(Stuntman newInstance);

    void delete(Stuntman stuntman);

    Stuntman update(Stuntman stuntman);
}

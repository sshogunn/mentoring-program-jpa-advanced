package com.epam.trainings.mentoring.jpa.repository.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.lazyloading.LazyPoliceman;

public interface LazyPolicemanRepository {
    LazyPoliceman findBy(long id);
}

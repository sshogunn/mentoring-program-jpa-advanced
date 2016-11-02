package com.epam.trainings.mentoring.jpa.repository.orpharemoval;

public interface TravelerRepository {
    void cleanTravelerItems(long id);
}

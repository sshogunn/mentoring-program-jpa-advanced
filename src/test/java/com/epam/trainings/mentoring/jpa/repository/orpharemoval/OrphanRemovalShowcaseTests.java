package com.epam.trainings.mentoring.jpa.repository.orpharemoval;


import com.epam.trainings.mentoring.jpa.domain.orphanremoval.TravelItem;
import com.epam.trainings.mentoring.jpa.repository.AbstractIntegrationTests;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrphanRemovalShowcaseTests extends AbstractIntegrationTests {
    @Autowired
    private TravelerRepository travelerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldRemoveAllTravelItemsWhenTheyRemovedFromTraveler() {
        //GIVEN
        Operation operation = sequenceOf(
                insertInto("TRAVELER")
                        .columns("ID", "Name")
                        .values(1, "Boris Zhilko")
                        .build(),
                insertInto("TRAVEL_ITEM")
                        .columns("ID", "NAME", "OWNER")
                        .values(1, "Wallet", 1)
                        .values(2, "Water", 1)
                        .build()
        );
        new DbSetup(destination, operation).launch();
        long id = 1;
        //WHEN
        travelerRepository.cleanTravelerItems(id);
        //THEN
        List<TravelItem> travelItems = (List<TravelItem>) entityManager.createNativeQuery("SELECT * FROM TRAVEL_ITEM", TravelItem.class).getResultList();
        assertThat(travelItems).hasSize(0);

    }
}

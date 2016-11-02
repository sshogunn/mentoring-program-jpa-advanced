package com.epam.trainings.mentoring.jpa.repository.cascade;

import com.epam.trainings.mentoring.jpa.domain.cascade.EquipmentItem;
import com.epam.trainings.mentoring.jpa.domain.cascade.Stuntman;
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
public class CascadeShowcaseTests extends AbstractIntegrationTests {
    @Autowired
    private StuntmanRepository stuntmanRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldDeleteEquipmentByCascadeWhenStuntmanIsDeleted() {
        //GIVEN
        Operation operation = sequenceOf(
                insertInto("STUNTMAN")
                        .columns("ID", "NAME")
                        .values(1, "Anuar Nurmakanov")
                        .build(),
                insertInto("EQUIPMENT_ITEM")
                        .columns("ID", "TITLE", "OWNER")
                        .values(1, "Hamlet", 1)
                        .values(2, "TT", 1)
                        .build()
        );
        new DbSetup(destination, operation).launch();
        long id = 1;
        Stuntman stuntman = entityManager.find(Stuntman.class, id);
        //WHEN
        stuntmanRepository.delete(stuntman);
        //THEN
        assertThat(entityManager.find(Stuntman.class, id)).isNull();
        List<EquipmentItem> equipment = entityManager.createQuery("SELECT e FROM EquipmentItem e", EquipmentItem.class).getResultList();
        assertThat(equipment).hasSize(0);
    }

    @Test
    public void shouldSaveEquipmentByCascadeWhenStuntmanIsSaved() {
        //GIVEN
        EquipmentItem hamlet = new EquipmentItem();
        hamlet.setTile("Hamlet");

        Stuntman stuntman = new Stuntman();
        stuntman.setName("Almas Doskozhin");
        stuntman.getEquipment().add(hamlet);
        hamlet.setStuntman(stuntman);
        //WHEN
        stuntmanRepository.save(stuntman);
        //THEN
        Stuntman saved = (Stuntman) entityManager.createNativeQuery("SELECT * FROM Stuntman s", Stuntman.class).getSingleResult();
        assertThat(saved.getEquipment().iterator().next().getTile()).isEqualTo("Hamlet");
    }
}

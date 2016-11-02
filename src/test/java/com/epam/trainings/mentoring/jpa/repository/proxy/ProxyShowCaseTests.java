package com.epam.trainings.mentoring.jpa.repository.proxy;

import com.epam.trainings.mentoring.jpa.domain.lazyloading.LazyPoliceman;
import com.epam.trainings.mentoring.jpa.domain.lazyloading.SuperPolicemanEquipment;
import com.epam.trainings.mentoring.jpa.repository.AbstractIntegrationTests;
import com.epam.trainings.mentoring.jpa.repository.lazyloading.LazyPolicemanRepository;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyShowCaseTests extends AbstractIntegrationTests {
    @Autowired
    private LazyPolicemanRepository lazyPolicemanRepository;

    @Test
    public void objectInstanceOfShouldNotWorkWhenProxyIsUsed() {
        //GIVEN
        prepareData();
        long id = 1;
        //WHEN
        LazyPoliceman lazyPoliceman = lazyPolicemanRepository.findBy(id);
        //THEN
        assertThat(lazyPoliceman.getPolicemanEquipment()).isInstanceOf(SuperPolicemanEquipment.class);
    }

    @Test
    public void collectionsInstanceOfShouldNotWorkWhenProxyIsUsed() {
        //GIVEN
        prepareData();
        long id = 1;
        //WHEN
        LazyPoliceman lazyPoliceman = lazyPolicemanRepository.findBy(id);
        //THEN
        assertThat(lazyPoliceman.getDocuments()).isNotInstanceOf(ArrayList.class);
        assertThat(lazyPoliceman.getDocuments()).isInstanceOf(PersistentBag.class);
    }

    private void prepareData() {
        Operation operation = sequenceOf(
                insertInto("LAZY_POLICEMAN")
                        .columns("ID", "FIRST_NAME", "LAST_NAME")
                        .values(1, "Yerlan", "Yerlanov")
                        .build(),
                insertInto("DOCUMENT")
                        .columns("ID", "NAME", "OWNER")
                        .values(1, "Unique id", 1)
                        .values(2, "Id card", 1)
                        .build(),
                insertInto("POLICEMAN_EQUIPMENT")
                        .columns("ID", "TYPE", "NAME", "OWNER")
                        .values(1, "SUPER", "New Equipment", 1)
                        .build()
        );
        new DbSetup(destination, operation).launch();
    }
}

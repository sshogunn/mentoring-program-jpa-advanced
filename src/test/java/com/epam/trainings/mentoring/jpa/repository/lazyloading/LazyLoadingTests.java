package com.epam.trainings.mentoring.jpa.repository.lazyloading;

import com.epam.trainings.mentoring.jpa.domain.lazyloading.LazyPoliceman;
import com.epam.trainings.mentoring.jpa.repository.AbstractIntegrationTests;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import org.hibernate.LazyInitializationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LazyLoadingTests extends AbstractIntegrationTests {
    @Autowired
    private LazyPolicemanRepository lazyPolicemanRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    @Transactional
    public void shouldLoadDocumentsAndDountsWithPoliceman() {
        //GIVEN
        prepareData();
        //WHEN
        LazyPoliceman policeman = lazyPolicemanRepository.findBy(1);
        //THEN
        assertThat(policeman.getDocuments()).hasSize(2);
        assertThat(policeman.getDonuts()).hasSize(2);
    }

    @Test
    public void shouldThrowLazyInitExceptionWhenContextIsClosed() {
        //GIVEN
        prepareData();
        exception.expect(LazyInitializationException.class);
        //WHEN
        LazyPoliceman policeman = lazyPolicemanRepository.findBy(1);
        //THEN
        assertThat(policeman.getDocuments()).hasSize(2);
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
                insertInto("DONUT")
                        .columns("ID", "OWNER")
                        .values(1, 1)
                        .values(2, 1)
                        .build()
        );
        new DbSetup(destination, operation).launch();
    }
}

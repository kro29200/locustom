package com.kro.persistence.service;

import com.google.common.collect.Lists;
import com.kro.LocustomApplication;
import com.kro.persistence.dao.PersonRepository;
import com.kro.persistence.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * locustom - com.kro.persistence.service
 * <p>
 * Created by Thomas Croguennec on 06/01/17.
 * On 06/01/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LocustomApplication.class)
@IntegrationTest("server.port=0")
@DataJpaTest
@Slf4j
public class PersonServiceTest {

    @Autowired
    private PersonRepository repo;

    @Before
    public void setUp() throws Exception {
        log.info("Setup test");
        Person user1 = new Person();
        user1.setLastname("First");
        user1.setFirstname("User");
        user1.setAddress("First user address");
        Person user2 = new Person();
        user2.setLastname("Second");
        user2.setFirstname("User");
        user2.setAddress("Second user address");
        repo.save(user1);
        repo.save(user2);
    }

    @Test
    public void testSuite() throws Exception {
        findOne();
        log.info("PersonRepository::findOne() => OK");
        findAll();
        log.info("PersonRepository::findAll() => OK");
        create();
        log.info("PersonRepository::create() => OK");
        update();
        log.info("PersonRepository::update() => OK");
        delete();
        log.info("PersonRepository::delete() => OK");
        deleteById();
        log.info("PersonRepository::deleteById() => OK");
        getTotal();
        log.info("PersonRepository::getTotal() => OK");
        isExisting();
        log.info("PersonRepository::isExisting() => OK");
    }

    public void findOne() throws Exception {
        List<Person> personList = repo.findByLastName("First");
        Person person1 = repo.findOne(personList.get(0).getId());
        Assert.assertEquals("Found wrong number of user !", 1, personList.size());
        Assert.assertEquals("Found wrong number of user !", "First", person1.getLastname());
    }

    public void findAll() throws Exception {
        List<Person> personList = Lists.newArrayList(repo.findAll());
        Assert.assertEquals("Found wrong number of users ! ", 2, personList.size());
    }

    public void create() throws Exception {
        Person user3 = new Person();
        user3.setLastname("Third");
        user3.setFirstname("User");
        user3.setAddress("Third user address");
        repo.save(user3);
        List<Person> personList = repo.findByLastName("Third");
        Person personSaved = repo.findOne(personList.get(0).getId());
        Assert.assertEquals("User not saved !", "Third", personSaved.getLastname());
    }

    public void update() throws Exception {
        List<Person> personList = repo.findByLastName("Third");
        Person personToUpdate = repo.findOne(personList.get(0).getId());
        personToUpdate.setFirstname("UserUpdated");
        repo.save(personToUpdate);
        Person personUpdated = repo.findOne(personList.get(0).getId());
        Assert.assertEquals("Found wrong number of user !", 1, personList.size());
        Assert.assertEquals("User not updated !", "UserUpdated", personUpdated.getFirstname());
    }

    public void delete() throws Exception {
        List<Person> personList = repo.findByLastName("Third");
        Person personToDelete = repo.findOne(personList.get(0).getId());
        repo.delete(personToDelete);
        personList = Lists.newArrayList(repo.findAll());
        Assert.assertEquals("Found wrong number of users !", 2, personList.size());
    }

    public void deleteById() throws Exception {
        List<Person> personList = repo.findByLastName("First");
        Person personToDelete = repo.findOne(personList.get(0).getId());
        repo.delete(personToDelete.getId());
        personList = Lists.newArrayList(repo.findAll());
        Assert.assertEquals("Found wrong number of users !", 1, personList.size());
    }

    public void getTotal() throws Exception {
        long total = repo.count();
        Assert.assertEquals("Found wrong number of user !", 1, total);
    }

    public void isExisting() throws Exception {
        List<Person> personList = repo.findByLastName("Second");
        Boolean isExisting = repo.exists(personList.get(0).getId());
        Assert.assertEquals("User not found", true, isExisting);
    }

}
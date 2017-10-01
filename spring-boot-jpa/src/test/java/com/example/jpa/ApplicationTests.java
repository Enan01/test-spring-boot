package com.example.jpa;

import com.example.jpa.entity.Person;
import com.example.jpa.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by doublesouth on 2017/10/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test() throws Exception {
        personRepository.save(new Person("AAA", 10));
        personRepository.save(new Person("BBB", 20));
        personRepository.save(new Person("CCC", 30));
        personRepository.save(new Person("DDD", 40));
        personRepository.save(new Person("EEE", 50));
        personRepository.save(new Person("FFF", 60));
        personRepository.save(new Person("GGG", 70));

        Assert.assertEquals(7, personRepository.findAll().size());

        Assert.assertEquals(60, personRepository.findByName("FFF").getAge().intValue());

        Assert.assertEquals(60, personRepository.findPerson("FFF").getAge().intValue());

        Assert.assertEquals("FFF", personRepository.findByNameAndAge("FFF", 60).getName());

        personRepository.delete(personRepository.findByName("AAA"));

        Assert.assertEquals(6, personRepository.findAll().size());
    }
}

package com.example.jpa.repository;

import com.example.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by doublesouth on 2017/10/1.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);

    Person findByNameAndAge(String name, Integer age);

    @Query("from Person p where p.name = :name")
    Person findPerson(@Param("name") String name);
}

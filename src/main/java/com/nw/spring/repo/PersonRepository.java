package com.nw.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.spring.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}

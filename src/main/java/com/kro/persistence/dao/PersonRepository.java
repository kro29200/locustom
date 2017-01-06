package com.kro.persistence.dao;

import com.kro.persistence.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * locustom - com.kro.persistence.dao
 * <p>
 * Created by Thomas Croguennec on 06/01/17.
 * On 06/01/17
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("select p from Person p where p.lastname=?1")
    List<Person> findByLastName(String lastname);
}

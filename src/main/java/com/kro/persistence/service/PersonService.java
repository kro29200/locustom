package com.kro.persistence.service;

import com.kro.persistence.dao.PersonRepository;
import com.kro.persistence.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * locustom - com.kro.persistence.service
 * <p>
 * Created by Thomas Croguennec on 06/01/17.
 * On 06/01/17
 */
@Service
public class PersonService extends AbstractService<Person> {

    @Autowired
    private PersonRepository service;

    @Override
    protected CrudRepository<Person, Integer> getDaoIntID() {
        return service;
    }


    public List<Person> findByLastName(String lastname){
        return service.findByLastName(lastname);
    };
}

package com.kro.persistence.service;

import com.google.common.collect.Lists;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * locustom - com.kro.persistence.service
 * <p>
 * Created by Thomas Croguennec on 06/01/17.
 * On 06/01/17
 */
public abstract class AbstractService<T extends Serializable> {

    /**
     * @return The DAO for the object that have an integer ID
     */
    protected abstract CrudRepository<T, Integer> getDaoIntID();

    /**
     * Find a T object by int id
     *
     * @param id The id
     * @return The object
     */
    public T findOne(int id) {
        return getDaoIntID().findOne(id);
    }

    /**
     * Find all T object
     *
     * @return A list of T object
     */
    public List<T> findAll() {
        return Lists.newArrayList(getDaoIntID().findAll());
    }

    /**
     * Insert a T object
     *
     * @param entity The T object
     * @return The T ojbect created
     */
    public T create(T entity) {
        return getDaoIntID().save(entity);
    }

    /**
     * Update the T object specified
     *
     * @param entity The T object to update
     * @return The T object updated
     */
    public T update(T entity) {
        return getDaoIntID().save(entity);
    }

    /**
     * Delete the T object specified
     *
     * @param entity The T object
     */
    public void delete(T entity) {
        getDaoIntID().delete(entity);
    }

    /**
     * Delete the T object specified by its int id
     *
     * @param id The id of the T object
     */
    public void deleteById(int id) {
        getDaoIntID().delete(id);
    }

    /**
     * Get the total number of the T object
     * @return The total
     */
    public Integer getTotal() {
        return Math.toIntExact(getDaoIntID().count());
    }

    /**
     * Check if the entity exist
     * @param id The entity ID
     * @return true id exist ; false if not
     */
    public boolean isExisting(int id){
        return getDaoIntID().exists(id);
    }

}

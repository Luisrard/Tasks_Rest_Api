package com.nextline.services;

import java.util.List;

/**
 * Interface to make the CRUD services of the entity.<br>
 * As the {@link #getObjects()} to get a list.<br>
 * <b>find</b> to find the object. {@link #find(Integer)}<br>
 * <b>save</b> to save the object. {@link #save(Object)}<br>
 * <b>update</b> to update the object. {@link #update(Object)}<br>
 * <b>delete</b> to delete the object. {@link #delete(Integer)}<br>
 * @param <D> The Data dto type
 * @param <C> The CriteriaSearch type
 * @author Luis
 */
public interface CrudService<D,C> {
    /**
     * Function to <b>get</b> the all the objects in a list in the database
     * @return The list of the objects
     */
    default List<D> getObjects(){return  null;}

    /**
     * Function to <b>get</b> the all the objects in a list in the database, whit a filter
     * @param criteria the filters of the search.
     * @return The list of the objects
     */
    default List<D> getObjects(C criteria){return null;}

    /**
     * Function to find one object or the first object found with a criteria filter
     * @param criteria the criteria
     * @return the object found
     */
    default D findOne(C criteria){return null;};
    /**
     * Function to <b>find</b> the object by their id in the database
     * @param id Id of the object to search
     * @return The object result or {@code null} if not found
     */
    D find(Integer id);

    /**
     * Function to <b>save</b> the object in the database
     * @param request The object to save
     * @return The object saved or {@code null} if happened an error
     */
    D save(D request)  throws RuntimeException;

    /**
     * Function to <b>update</b> the object in the database
     * @param request The object to update
     * @return The object saved or {@code null} if happened an error
     */
    D update(D request)  throws RuntimeException;

    /**
     * Function to <b>delete</b> the product in the database
     * @param id The id of the object to delete
     * @return The result of the request EITHER was deleted {@code true} or not {@code false}
     */
    boolean delete(Integer id) throws RuntimeException;
}

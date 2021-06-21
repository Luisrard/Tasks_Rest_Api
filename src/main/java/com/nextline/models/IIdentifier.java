package com.nextline.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Interface to get {@link #getId()}  and set {@link #setId(Object)} the id of the object.
 * Also if you implement this interface is recommended in the case that the id has the same name as "id" use the @JsonProperty("id")
 * @param <I>The id type of the object
 * @author Luis
 */
public interface IIdentifier<I> {
    /**
     * Function to know the id the object.
     * Also is recommended to add the Annotation {@code @JsonIgnore} in the case to override this method
     * @return The id of the object.
     */
    I getId();


    /**
     * Function to set the id of the object, this is used at the time to save the list of this object to clear this field.
     * @param id The id of the object.
     */
    @JsonIgnore
    void setId(I id);
}

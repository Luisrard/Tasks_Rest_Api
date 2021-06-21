package com.nextline.models;

/**
 * Interface to know when a object has to be deleted {@link #isToDelete()}, and also the functions of {@link IIdentifier}.
 * if the id field has the same name as id is recommended to use @JsonProperty("id")
 * <pre>{@code
    public class ObjectChangeableDto  {
        private idObject;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private boolean toDelete = false;

        @JsonIgnore
        @Override
        public Integer getId() {
         return idObject;
        }

        @Override
        public void setId(Integer id) {
         idObject=id;
        }
         //All the another methods and fields
    }
 * }<pre>
 * @param <I>The id type of the object
 * @author Luis
 */
public interface IChangeable<I> extends IIdentifier<I> {
    /**
     * Function to know if the object has to be deleted.
     *
     * @return If this object has to be deleted {@code true} or not {@code false}
     */
    boolean isToDelete();
}

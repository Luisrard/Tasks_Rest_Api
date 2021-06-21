package com.nextline.tools;

import com.nextline.models.IChangeable;

import java.util.*;

/**
 * Generic class to manage the list of objects changeable that implements the interface {@link IChangeable}
 * @param <D> The object.
 * @param <I> The id of the object.
 * @author Luis
 */
public class ChangeableTools<D extends IChangeable<I>,I> {
    /**
     * Function to get the object that must to be deleted and remove those to the provided list.
     * @param changesList The list that contains the changes
     * @return The list of ids that must to be deleted.
     */
    public static <D extends IChangeable<I> ,I> List<I> getItemsToDelete(List<D> changesList) {
        List<I> idsToRemove = new ArrayList<>();
        if(changesList != null) {
            List<D> itemsToRemove = new ArrayList<>();
            for (D item : changesList) {
                if(item.isToDelete()){//Delete the items of the list that must to be deleted
                    idsToRemove.add(item.getId());
                    itemsToRemove.add(item);
                }
            }
            //And remove the request list
            changesList.removeAll(itemsToRemove);
        }
        return idsToRemove;
    }

    /**
     * Function to <b>clear {@code null}</b> the id field of the objects of a list
     * @param listToClear The list to clear.
     */
    public static <D extends IChangeable<I>, I> void clearIds(Collection<D> listToClear)
    {
        if(listToClear != null)
        {
            for(D item : listToClear){
                item.setId(null);
            }
        }
    }
}

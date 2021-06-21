package com.nextline.controllers;

import com.nextline.exception.ApiRequestException;
import com.nextline.services.CrudService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Abstract class to manage the request of the clients in a generic form and works as template for a <b>Controller Class</b>,<br>
 * As the <b>GET</b> to get a list, list with filters and find one object.  {@link #findObject(int)}.<br>
 * <b>POST</b> to save one object. {@link #saveObject(Object)}.<br>
 * <b>PUT</b> to update one object. {@link #updateObject(Object)}.<br>
 * <b>DELETE</b> to delete one object. {@link #deleteObject(int)}.<br>
 * Also to use those methods and appears in the controllers must to override, add the correct beans and in the constructor must to call the super
 * constructor and initialize the logger e.g.<br>
 * <pre>{@code
 *      //The controller beans
 *      @RestController
 *      @RequestMapping("/example")
 *      public class ExampleController extends ControllerApi<ExampleDto, ExampleCriteria, ExampleService>{
 *          public ExampleController(IServiceApi<ExampleDto, ExampleCriteria> service) {
 *              super(service);
 *              //The initialization of the logger
 *              this.logger = LoggerFactory.getLogger(ExampleController.class);
 *          }
 *          //The get mapping bean
 *          @GetMapping
 *          @Override
 *          public ResponseEntity<Page<ExampleDto>> getPage(PageProp pageProp, ExampleCriteria criteria) {
 *              return super.getPage(pageProp, criteria);
 *          }
 *     }
 * }</pre>
 * @param <D> The Data dto type
 * @param <C> The CriteriaSearch type
 * @param <S> The Service type with the previous types
 * @author Luis
 * @since BACKEND_BD-0.1
 * @version 1.0
 */
public abstract class CrudController<D,C, S extends CrudService<D,C>> {
    protected final Logger logger;
    protected final CrudService<D,C> service;

    protected CrudController(CrudService<D,C> service, Class<?> controllerClass) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(controllerClass);
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain all the objects in a list.
     * @return The list obtained
     */
    public ResponseEntity<List<D>> getObjects()
    {
        logger.info("Obtaining All "+ getEntityPluralName()+ "...");
        ResponseEntity<List<D>> response = ResponseEntity.ok(this.service.getObjects());
        logger.info(getEntityPluralName()+" obtained...");
        return response;
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain all the objects in a list with a criteria field.
     * @return The list obtained
     */
    public ResponseEntity<List<D>> getObjects(C criteria)
    {
        logger.info("Obtaining All "+ getEntityPluralName()+ ", with this " + criteria+ "...");
        ResponseEntity<List<D>> response = ResponseEntity.ok(this.service.getObjects(criteria));
        logger.info(getEntityPluralName()+" obtained...");
        return response;
    }

    /**
     * Function to obtain one object with a filter.
     * @param criteria the criteria filter
     * @return The object obtained
     */
    public ResponseEntity<D> findOne(C criteria)
    {
        logger.info("Find " + getEntityName() + " "+ criteria +"...");
        D response = this.service.findOne(criteria);
        if(response == null)
        {
            logger.info(getEntityName() + " " + criteria + " not found...");
            throw new ApiRequestException("No " + getEntityName() + " " + criteria + " exist", HttpStatus.NOT_FOUND);
        }
        logger.info(getEntityName() + " found...");
        return ResponseEntity.ok(response);
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain the object with their own id.
     * @param id The id of the object
     * @return The object obtained
     */
    public ResponseEntity<D> findObject(int id)
    {
        logger.info("Find " + getEntityName() + " "+ id +"...");
        D response = this.service.find(id);
        if(response == null)
        {
            logger.info(getEntityName() + " " + id + " not found...");
            throw new ApiRequestException("No " + getEntityName() + " " + id + " exist", HttpStatus.NOT_FOUND);
        }
        logger.info(getEntityName() + " found...");
        return ResponseEntity.ok(response);
    }

    /**
     * Function to get the request of the client in a <b>POST</b> form.<br>
     * This request is to <b>save</b> the object in the database.
     * @param requestObject The object to save
     * @return The result and the returned object of the database
     */
    public ResponseEntity<D> saveObject(@RequestBody D requestObject)
    {
        logger.info("Save " + getEntityName() + "" + requestObject + "...");
        try{
            D response = this.service.save(requestObject);
            if(response == null)
            {
                logger.warn(getEntityName() + " " + requestObject + " has not been saved");
                throw new ApiRequestException("Invalid fields", HttpStatus.BAD_REQUEST);
            }
            logger.info(getEntityName() + " " + response + " saved...");
            return ResponseEntity.ok(response);
        }catch (ApiRequestException e){
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + requestObject + " has not been saved");
            throw e;
        }catch (DataIntegrityViolationException e){
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + requestObject + " has not been saved");
            if(e.getCause() instanceof ConstraintViolationException){
                ConstraintViolationException cause = (ConstraintViolationException) e.getCause();
                if(cause.getErrorCode() == 1062){
                    throw new ApiRequestException("Duplicate value: This " + getEntityName() + " already exist", HttpStatus.BAD_REQUEST);
                }
            }
            throw new ApiRequestException("Invalid fields", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + requestObject + " has not been saved");
            throw new ApiRequestException("Invalid fields", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Function to get the request of the client in a <b>PUT</b> form.<br>
     * This request is to <b>update</b> the object in the database.
     * @param requestObject The object to update
     * @return The result and the returned object of the database
     */
    public ResponseEntity<D> updateObject(@RequestBody D requestObject)
    {
        logger.info("Update " + getEntityName() + " " + requestObject + "...");
        try{
            D response = this.service.update(requestObject);
            if(response == null)
            {
                logger.warn(getEntityName() + " " + requestObject + "has not been updated");
                throw new ApiRequestException("Invalid fields", HttpStatus.BAD_REQUEST);
            }
            logger.info(getEntityName() + " " + response + " updated...");
            return ResponseEntity.ok(response);
        }catch (ApiRequestException e){
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + requestObject + " has not been updated");
            throw e;
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + requestObject + " has not been updated");
            throw new ApiRequestException("Invalid fields", HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * Function to get the request of the client in a <b>DELETE</b> form.<br>
     * This request is to <b>delete</b> the object in the database.
     * @param id The id of the obeject to delete
     * @return The result of the request EITHER was deleted or not
     */
    public ResponseEntity<?> deleteObject(int id)
    {
        logger.info("Delete " + getEntityName() + " " + id+ "...");
        try {
            this.service.delete(id);
        }
        catch (ApiRequestException e){
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + id + " has not been deleted");
            throw e;
        }
        catch (EmptyResultDataAccessException e){
            logger.warn(e.getMessage());
            String msg = "The " + getEntityName() + "with id " + id + "does not exist";
            logger.warn(msg);
            logger.warn(getEntityName() + " " + id + " has not been deleted");
            throw new ApiRequestException(msg, HttpStatus.NOT_FOUND);
        }
        catch (DataIntegrityViolationException e){
            logger.warn(e.getMessage());
            String msg = "Existent relations that use this " + getEntityName();
            logger.warn(msg);
            logger.warn(getEntityName() + " " + id + " has not been deleted");
            throw new ApiRequestException(msg, HttpStatus.PRECONDITION_FAILED);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() + " " + id + " has not been deleted");
            throw new ApiRequestException("No " + getEntityName() + " " + id + " exist", HttpStatus.NOT_FOUND);
        }

        logger.info(getEntityName() + " " + id +" deleted...");
        return ResponseEntity.ok().build();
    }

    /**
     * Function to get the name of the entity
     * @return The Entity name
     */
    protected abstract String getEntityName();
    /**
     * Function to get the name of the entity in plural form
     * @return The Entity plural name
     */
    protected abstract String getEntityPluralName();
}

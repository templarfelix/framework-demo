package io.easycm.projects.rest.dynamic;

import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.facade.DynamicEntityFacade;
import io.easycm.projects.filter.DynamicEntityFilterData;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import io.easycm.framework.base.security.LazyAware;
import io.easycm.framework.form.rest.BaseFormRest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dynamic/dynamicEntity")
@RequestScoped
@LazyAware
//@Security
public class DynamicEntityRestService extends BaseFormRest<DynamicEntity> {

  @Inject
  private Logger log;

  @Inject
  private DynamicEntityFacade facade;

  @Inject
  private Validator validator;

  @GET
  @Path("/findById/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public DynamicEntity findById(@PathParam("id") Long id) {
    log.info("findById");

    // FIXME CORRIGIR
    DynamicEntityFilterData filter = new DynamicEntityFilterData(UUID.randomUUID());

    DynamicEntity item = facade.findByFilter(filter);

    if (item == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    return item;
  }

  @GET
  @Path("/findByFilter")
  @Produces(MediaType.APPLICATION_JSON)
  public DataPage<DynamicEntity> findByFilter(@QueryParam("description") String description,
      // default page fields
      @QueryParam("startRow") int startRow,
      @QueryParam("pageSize") int pageSize) {

    DynamicEntityFilterData filter = new DynamicEntityFilterData(description);
    filter.setOrderBy(DynamicEntityFilterData.BY_ID_ASC);
    return facade.findByFilter(filter, new Page((startRow - 1) * pageSize, pageSize));
  }

  @GET
  @Path("/zoom")
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed("Admin")
  public DataPage<DynamicEntity> zoom(@QueryParam("DynamicEntity") String DynamicEntity,
      // default page fields
      @QueryParam("startRow") int startRow,
      @QueryParam("pageSize") int pageSize) {

    DynamicEntityFilterData filter = new DynamicEntityFilterData(DynamicEntity);

    return facade.zoom(filter, new Page((startRow - 1) * pageSize, pageSize));
  }

  @POST
  @Path("/save")
  //@Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed("Admin")
  public Response save(DynamicEntity item) {
    Response.ResponseBuilder builder = null;
    try {
      // NOVO RESETA ID
      item.setId(null);

      validate(item, CREATE);
      facade.persist(item);

      // Create an "ok" response
      builder = Response.ok();
    } catch (ConstraintViolationException ce) {
      // Handle bean validation issues
      builder = createViolationResponse(ce.getConstraintViolations());
    } catch (ValidationException e) {
      // Handle the unique constrain violation
      Map<String, String> responseObj = new HashMap<String, String>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.CONFLICT).entity(
          responseObj);
    } catch (Exception e) {
      // Handle generic exceptions
      Map<String, String> responseObj = new HashMap<String, String>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.BAD_REQUEST).entity(
          responseObj);
    }

    return builder.build();
  }

  @PUT
  @Path("/update/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed("Admin")
  public Response update(@PathParam("id") Long id, DynamicEntity item) {
    Response.ResponseBuilder builder = null;
    try {
      validate(item, UPDATE);
      facade.merge(item);
      // Create an "ok" response
      builder = Response.ok();
    } catch (ConstraintViolationException ce) {
      // Handle bean validation issues
      builder = createViolationResponse(ce.getConstraintViolations());
    } catch (ValidationException e) {
      // Handle the unique constrain violation
      Map<String, String> responseObj = new HashMap<String, String>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.CONFLICT).entity(
          responseObj);
    } catch (Exception e) {

      // Handle generic exceptions
      Map<String, String> responseObj = new HashMap<String, String>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.BAD_REQUEST).entity(
          responseObj);
    }

    return builder.build();
  }

  @DELETE
  @Path("/remove/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed("Admin")
  public Response remove(@PathParam("id") Long id) {
    Response.ResponseBuilder builder = null;

    try {
      // FIXME CORRIGIR
      DynamicEntity item = facade.findById(UUID.randomUUID());
      facade.remove(item);
      // Create an "ok" response
      builder = Response.ok();
    } catch (ConstraintViolationException ce) {
      // Handle bean validation issues
      builder = createViolationResponse(ce.getConstraintViolations());
    } catch (ValidationException e) {
      // Handle the unique constrain violation
      Map<String, String> responseObj = new HashMap<String, String>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.CONFLICT).entity(
          responseObj);
    } catch (Exception e) {

      // Handle generic exceptions
      Map<String, String> responseObj = new HashMap<String, String>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.BAD_REQUEST).entity(
          responseObj);
    }

    return builder.build();
  }

  /**
   *
   * */
  private void validate(DynamicEntity DynamicEntity, int operationType)
      throws ConstraintViolationException, ValidationException {

    if (operationType == CREATE || operationType == UPDATE) {
      Set<ConstraintViolation<DynamicEntity>> violations = validator.validate(DynamicEntity);

      if (!violations.isEmpty()) {
        throw new ConstraintViolationException(
            new HashSet<ConstraintViolation<?>>(violations));
      }
    }
  }

}

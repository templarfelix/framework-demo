package io.easycm.projects.rest.sample;

import io.easycm.projects.entity.Sample;
import io.easycm.projects.facade.SampleFacade;
import io.easycm.projects.filter.SampleFilterData;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import io.easycm.framework.base.security.LazyAware;
import io.easycm.framework.form.rest.BaseFormRest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

@Path("/sample/sample")
@RequestScoped
@LazyAware
//@Security

// FIXME DESCOMENTAR

//@TenantAware
public class SampleRestService extends BaseFormRest<Sample> {

  @Inject
  private Logger log;

  @Inject
  private SampleFacade facade;

  @Inject
  private Validator validator;

  @GET
  @Path("/findById/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Sample findById(@PathParam("id") Long id) {
    log.info("findById");

    SampleFilterData filter = new SampleFilterData(id);

    Sample item = facade.findByFilter(filter);

    if (item == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    return item;
  }

  @GET
  @Path("/findByFilter")
  @Produces(MediaType.APPLICATION_JSON)
  public DataPage<Sample> findByFilter(@QueryParam("Sample") String Sample,
      // default page fields
      @QueryParam("startRow") int startRow,
      @QueryParam("pageSize") int pageSize) {

    SampleFilterData filter = new SampleFilterData(Sample);
    filter.setOrderBy(SampleFilterData.BY_ID_ASC);
    return facade.findByFilter(filter, new Page((startRow - 1) * pageSize, pageSize));
  }

  @GET
  @Path("/zoom")
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed("Admin")
  public DataPage<Sample> zoom(@QueryParam("Sample") String Sample,
      // default page fields
      @QueryParam("startRow") int startRow,
      @QueryParam("pageSize") int pageSize) {

    SampleFilterData filter = new SampleFilterData(Sample);

    return facade.zoom(filter, new Page((startRow - 1) * pageSize, pageSize));
  }

  @POST
  @Path("/save")
  //@Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed("Admin")
  public Response save(Sample item) {
    Response.ResponseBuilder builder = null;
    try {
      System.out.println(item.toString());

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
  public Response update(@PathParam("id") Long id, Sample item) {
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
      Sample item = facade.findById(id);
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
  private void validate(Sample Sample, int operationType)
      throws ConstraintViolationException, ValidationException {

    if (operationType == CREATE || operationType == UPDATE) {
      Set<ConstraintViolation<Sample>> violations = validator.validate(Sample);

      if (!violations.isEmpty()) {
        throw new ConstraintViolationException(
            new HashSet<ConstraintViolation<?>>(violations));
      }
    }
  }

}

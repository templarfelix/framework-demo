package io.easycm.projects.rest.security;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import io.easycm.framework.base.rest.BaseREST;
import io.easycm.framework.base.security.Security;
import io.easycm.framework.security.entity.Plan;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fernando <fernando@mksdev.com>
 * @version 0.0.1-SNAPSHOT
 */
@Path("/security/status/pool")
@RequestScoped
@Security
public class PoolStatsREST extends BaseREST {

  @Inject
  private Logger log;

  //@Inject
  //private MetricRegistry metricRegistry;

  //@Inject
  //private HealthCheckRegistry healthCheckRegistry;

  @GET
  @Path("/findById/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  //@RolesAllowed({"Admin"})
  public Plan findById(@PathParam("id") Long id) {
    // FIXME REFATORAR
    //Plan plan = facade.findById(id);
    Plan plan = null;
    if (plan == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    return plan;
  }

}

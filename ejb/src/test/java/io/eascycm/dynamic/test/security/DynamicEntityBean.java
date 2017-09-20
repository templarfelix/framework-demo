package io.eascycm.dynamic.test.security;

import io.easycm.projects.facade.DynamicEntityFacade;
import java.util.concurrent.Callable;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.ejb3.annotation.RunAsPrincipal;

@Stateless
// CREATE FAKE LOGIN
@RunAs("Admin")
@RunAsPrincipal("test@mksdev.com")
public class DynamicEntityBean {

  @Inject
  private DynamicEntityFacade facade;

  @PermitAll
  public DynamicEntityFacade getFacade() {
    return facade;
  }

  @PermitAll
  public <V> V call(final Callable<V> callable) throws Exception {
    return callable.call();
  }
}

package io.easycm.projects.multitenancy;

import io.easycm.framework.base.security.TenantAware;
import java.util.Map;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.xml.ws.BindingProvider;

/**
 * Created by Fernando on 29/03/2016.
 */
@TenantAware
@Interceptor
public class TenantInterceptor {

  @Inject
  private TenantRegistry tenantRegistry;

  @AroundInvoke
  public Object wrapWithTenant(final InvocationContext ctx) throws Exception {

    Map<String, Object> msgContext = ctx.getContextData();
    String tenantName = (String) msgContext.get(BindingProvider.USERNAME_PROPERTY);

    // FIXME REMOVER E PEGAR CURRENT USER
    {
      tenantName = "test";
    }

    verifyCredentials(tenantName);

    final String oldValue = TenantHolder.getCurrentTenant();
    try {
      TenantHolder.setTenant(tenantName);
      // executes the real webservice method. Tenant is already set.
      return ctx.proceed();
    } finally {
      if (oldValue != null) {
        TenantHolder.setTenant(oldValue);
      } else {
        TenantHolder.cleanupTenant();
      }
    }
  }

  private void verifyCredentials(final String tenantName)
      throws InvalidCredentialsException {
    tenantRegistry.getTenant(tenantName)
        .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password!"));
  }
}

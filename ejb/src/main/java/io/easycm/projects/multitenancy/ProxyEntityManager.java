package io.easycm.projects.multitenancy;

import io.easycm.framework.security.entity.Tenant;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * Created by Fernando on 29/03/2016.
 */

public class ProxyEntityManager {

  private final EntityManager proxy;
  /**
   * Inject the default EntityManager, operated by a application container. Serves as a fallback, if
   * there is no tenant logged in and we are asked to return a EntityManager instance.
   */
  @PersistenceContext(unitName = "primary")
  private EntityManager entityManager;
  /**
   * Provider of EntityManagerFactory.
   *
   * @see TenantRegistry#getTenant(String)
   * @see TenantRegistry#createEntityManagerFactory(Tenant)
   */
  @Inject
  private TenantRegistry tenantRegistry;

  @Inject
  private Logger logger;

  private ProxyEntityManager() {
    proxy = (EntityManager) Proxy.newProxyInstance(this.getClass().getClassLoader(),
        new Class<?>[]{EntityManager.class},
        (proxy, method, args) -> method.invoke(getCurrentEntityManager(), args));
  }

  /**
   * CDI Producer. Checks if there is a tenant name in ThreadLocal storage {@link TenantHolder}. If
   * yes, load tenant from {@link TenantRegistry}, get its EntityManagerFactory. From the factory
   * create new EntityManager, join JTA transaction and return this EntityManager.
   *
   * @return EntityManager for Tenant or default EntityManager, if no tenant logged in.
   */
  @Produces
  private EntityManager getEntityManager() {
    return proxy;
  }

  private EntityManager getCurrentEntityManager() {
    final String currentTenant = TenantHolder.getCurrentTenant();
    if (currentTenant != null) {
      logger.info("Returning connection for tenant " + currentTenant);
      EntityManagerFactory factory = tenantRegistry.getEntityManagerFactory(currentTenant);
      final EntityManager em = factory.createEntityManager();
      return em;
    }
    return entityManager;
  }
}

package io.easycm.projects.multitenancy;

import io.easycm.projects.dynamic.DynamicFactory;
import io.easycm.framework.security.entity.Tenant;
import io.easycm.framework.security.facade.TenantFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.jpa.AvailableSettings;
import org.reflections.Reflections;

/**
 * Created by Fernando on 29/03/2016.
 */
@Singleton
@Startup

// FIXME CARREGAR SOB DEMANDA
// ATUALMENTE SOBE TUDO NO START DO CONTAINER
//
// FIXME CRIAR INTERFACE
public class TenantRegistry {

  private final Set<Tenant> tenants = new HashSet<>();
  private final Map<String, EntityManagerFactory> entityManagerFactories = new HashMap<>();
  @Inject
  private TenantFacade tenantFacade;
  @Inject
  private Logger logger;

  @Inject
  private DynamicFactory dynamicFactory;

  @PostConstruct
  protected void startupTenants() {
    try {

      final List<Tenant> tenants = loadTenantsFromDB();
      logger.info(String.format("Loaded %d tenants from DB.", tenants.size()));
      tenants.forEach(tenant -> {
        this.tenants.add(tenant);
        try {
          EntityManagerFactory emf = createEntityManagerFactory(tenant);
          entityManagerFactories.put(tenant.getName(), emf);
          logger.info("Tenant " + tenant.getName() + " loaded.");
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
      this.tenants.addAll(tenants);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @PreDestroy
  protected void shutdownTenants() {
    entityManagerFactories
        .forEach((tenantName, entityManagerFactory) -> entityManagerFactory.close());
    entityManagerFactories.clear();
    tenants.clear();
  }

  private List<Tenant> loadTenantsFromDB() {
    return tenantFacade.findAll();
  }

  /**
   * Create new {@link EntityManagerFactory} using this tenant's schema.
   *
   * @param tenant Tenant used to retrieve schema name
   * @return new EntityManagerFactory
   */
  private EntityManagerFactory createEntityManagerFactory(final Tenant tenant) throws Exception {
    final Map<String, Object> props = new TreeMap<>();
    logger.info(
        "Creating entity manager factory on schema '" + tenant.getSchemaName() + "' for tenant '"
            + tenant.getName() + "'.");

    props.put("hibernate.default_schema", tenant.getSchemaName());
    props.put("hibernate.hikari.poolName", tenant.getSchemaName());

    // load de classes
    // FIXME VIR DE PROPRIEDADES
    Reflections reflections = new Reflections("io.easycm");
    Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);

    List<Class> classToLoad = new ArrayList<Class>();
    for (Class<?> clazz : classes) {
      classToLoad.add(clazz);
    }

    classToLoad.addAll(dynamicFactory.makeTenantDynamicClasses(tenant));

    props.put(AvailableSettings.LOADED_CLASSES, classToLoad);
    return Persistence.createEntityManagerFactory("primary", props);
  }

  public Optional<Tenant> getTenant(final String tenantName) {
    return tenants.stream().filter(tenant -> tenant.getName().equals(tenantName)).findFirst();
  }

  /**
   * Returns EntityManagerFactory from the cache. EMF is created during tenant registration and
   * initialization.
   *
   * @see #startupTenants()
   */
  public EntityManagerFactory getEntityManagerFactory(final String tenantName) {
    return entityManagerFactories.get(tenantName);
  }
}

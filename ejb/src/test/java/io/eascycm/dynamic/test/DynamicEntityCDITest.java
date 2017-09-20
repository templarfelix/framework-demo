package io.eascycm.dynamic.test;

import io.easycm.projects.dao.DynamicEntityDAO;
import io.easycm.projects.dao.impl.DynamicEntityDAOImpl;
import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.entity.DynamicField;
import io.easycm.projects.entity.enums.FieldType;
import io.easycm.projects.facade.DynamicEntityFacade;
import io.easycm.projects.facade.impl.DynamicEntityFacadeImpl;
import io.eascycm.dynamic.test.base.BaseCDITest;
import io.easycm.framework.base.resources.Resources;
import io.easycm.framework.base.security.Transactional;
import io.easycm.framework.security.dao.TenantDAO;
import io.easycm.framework.security.dao.impl.TenantDAOImpl;
import io.easycm.framework.security.entity.Tenant;
import io.easycm.framework.security.filter.TenantFilterData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.jboss.arquillian.junit.InSequence;
import org.jglue.cdiunit.AdditionalPackages;
import org.junit.Test;

/**
 * Testa a classe de Roles do framework
 *
 * @author Fernando <fernando@mksdev.com>
 * @version 0.0.1-SNAPSHOT
 */


@AdditionalPackages({TenantDAO.class, DynamicEntityDAO.class, DynamicEntityFacade.class,
    DynamicEntityFacadeImpl.class, DynamicEntityDAOImpl.class, TenantDAO.class, TenantDAOImpl.class,
    Resources.class, DynamicEntity.class})
public class DynamicEntityCDITest extends BaseCDITest {

  @Inject
  private Logger log;

  @Inject
  private DynamicEntityDAO dynamicEntityBean;

  @Inject
  private TenantDAO tenantBean;

  @Test
  @InSequence(1)
  @Transactional
  public void testPersist() throws Exception {

    /**
     delete from dynamic.poolstatus;
     delete from dynamic.dynamicentity_dynamicfield;
     delete from dynamic.dynamicentity;
     delete from dynamic.dynamicfield;
     delete from security.tenant;
     */

    Tenant tenant = new Tenant();
    tenant.setName("test" + 0);
    tenant.setSchemaName("test" + 0);
    tenant.setId(null);

    tenantBean.persist(tenant);

    DynamicEntity newEntity = new DynamicEntity();
    newEntity.setName("Testes" + 0);
    newEntity.setDescription("Testes" + 0);

    newEntity.setTenant(tenantBean.findByFilterQ(new TenantFilterData("test" + 0)));
    newEntity.setId(null);

    List<DynamicField> fieldList = new ArrayList<DynamicField>();
    DynamicField id = new DynamicField();
    id.setName("id");
    id.setAsId(true);
    id.setFieldType(FieldType.LONG);
    id.setDescription("id field");
    fieldList.add(id);

    DynamicField description = new DynamicField();
    description.setName("description");
    description.setFieldType(FieldType.STRING);
    description.setDescription("description field");
    fieldList.add(description);

    DynamicField testInteger = new DynamicField();
    testInteger.setName("integer");
    testInteger.setFieldType(FieldType.INTEGER);
    testInteger.setDescription("integer field");
    fieldList.add(testInteger);

    // seta field list
    newEntity.setFields(fieldList);

    dynamicEntityBean.persist(newEntity);
  }

}

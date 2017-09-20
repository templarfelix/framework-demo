package io.eascycm.dynamic.test;

import io.eascycm.dynamic.test.security.FrameworkRoleBean;
import io.easycm.framework.security.entity.Role;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testa a classe de Roles do framework
 *
 * @author Fernando <fernando@mksdev.com>
 * @version 0.0.1-SNAPSHOT
 */
@RunWith(Arquillian.class)
public class FrameworkRoleTest {

  @Inject
  private Logger log;

  @Inject
  private FrameworkRoleBean roleBean;

  /**
   * Create a test.war file for deploy in JBoss
   *
   * @return Archive<?>
   */
  @Deployment
  public static Archive<?> createTestArchive() {
    return ShrinkWrap.create(WebArchive.class, "test.war")
        .addPackages(true, "br.com.streamsoft.projects.test.security")
        .addPackages(true, "br.com.streamsoft.projects.dao")
        .addPackages(true, "br.com.streamsoft.projects.dynamic")
        .addPackages(true, "br.com.streamsoft.projects.entity")
        .addPackages(true, "br.com.streamsoft.projects.facade")
        .addPackages(true, "br.com.streamsoft.projects.filter")
        .addPackages(true, "br.com.streamsoft.projects.job")
        .addPackages(true, "br.com.streamsoft.projects.multitenancy").addAsLibraries(
            Maven.resolver().loadPomFromFile("pom.xml")
                .importDependencies(ScopeType.COMPILE, ScopeType.PROVIDED,
                    ScopeType.TEST).resolve().withTransitivity().asFile())

        .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
        //.addAsWebInfResource("jboss-web.xml", "jboss-web.xml")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

  }

  //@Test
  //@InSequence(1)
  public void testPersist() throws Exception {
    roleBean.call(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        Role entity = getEntity();
        entity = roleBean.getFacade().persist(entity);
        Assert.assertNotNull(entity.getId());
        log.info(entity.getDescription() + " was persisted with id " + entity.getId());
        return null;
      }

    });
  }

  //@Test
  //@InSequence(2)
  public void testMerge() throws Exception {
    roleBean.call(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        Role entity = getEntity();
        entity = roleBean.getFacade().findById(entity.getId());
        Assert.assertNotNull(entity.getId());
        log.info(entity.getDescription() + " was persisted with id " + entity.getId());
        return null;
      }

    });
  }

  //@Test
  //@InSequence(3)
  public void testRemove() throws Exception {
    roleBean.call(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        Role entity = getEntity();
        entity = roleBean.getFacade().findById(entity.getId());
        roleBean.getFacade().remove(entity);

        Assert.assertNotNull(entity.getId());
        log.info(entity.getDescription() + " was persisted with id " + entity.getId());
        return null;
      }

    });
  }

  //@Test
  //@InSequence(4)
  public void testStress() throws Exception {
    roleBean.call(new Callable<Object>() {
      @Override
      public Object call() throws Exception {

        Role stressRole = null;
        for (int i = 0; i < 10000; i++) {
          stressRole = new Role();
          stressRole.setId("UnitTest" + i);
          stressRole.setDescription("UnitTestDesc" + i);
          roleBean.getFacade().save(stressRole);
        }
        return null;
      }
    });
  }

  @Test
  @InSequence(5)
  public void testFindAll() throws Exception {
    roleBean.call(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        List<Role> entityList = roleBean.getFacade().findAll();
        log.info("entityList.size() =" + entityList.size());
        Assert.assertNotNull(entityList);
        return null;
      }

    });
  }

  /**
   * Get Entity For Test
   */
  private Role getEntity() {
    Role entity = new Role();
    entity.setId("UnitTest");
    entity.setDescription("UnitTestDesc");

    return entity;
  }

}

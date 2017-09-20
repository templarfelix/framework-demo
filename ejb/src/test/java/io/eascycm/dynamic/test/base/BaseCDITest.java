package io.eascycm.dynamic.test.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.hibernate.jpa.AvailableSettings;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.reflections.Reflections;

/**
 * Created by felix on 01/03/2017.
 */

@RunWith(CdiRunner.class)
public class BaseCDITest {

  private static EntityManager em;

  @Produces
  public EntityManager doMakeEntitymanager() {

    if (em == null) {
      Reflections reflections = new Reflections("br.com.streamsoft");
      Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);

      List<Class> classToLoad = new ArrayList<Class>();
      for (Class<?> clazz : classes) {
        classToLoad.add(clazz);
      }
      final Map<String, Object> props = new TreeMap<>();
      props.put(AvailableSettings.LOADED_CLASSES, classToLoad);

      em = Persistence.createEntityManagerFactory("primary", props)
          .createEntityManager();
    }

    return em;
  }

  @Before
  public void before() {
    em.getTransaction().begin();
  }

  @After
  public void after() {
    em.getTransaction().commit();
  }
}

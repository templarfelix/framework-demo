package io.easycm.projects.dynamic;

import io.easycm.projects.entity.DynamicBaseEntity;
import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.entity.DynamicField;
import io.easycm.projects.facade.DynamicEntityFacade;
import io.easycm.projects.filter.DynamicEntityFilterData;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import io.easycm.framework.security.entity.Tenant;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default;

/**
 * Created by Fernando on 05/04/2016.
 */
@Named
//http://stackoverflow.com/questions/30859419/display-generated-bytebuddy-bytecode

// TODO EFORM DYNAMIC
public class DynamicFactory {

  @Inject
  private Logger logger;

  @Inject
  private DynamicEntityFacade dynamicEntityFacade;

  /**
   *
   * */
  // FIXME fazer salvar em arquivo & carregar de arquivos & somente regerar apos necessidades
  // .saveIn(File.createTempFile("",""))
  public List<Class> makeTenantDynamicClasses(Tenant tenant) throws Exception {

    List<Class> classesMaked = new ArrayList<>();

    DynamicEntityFilterData filterData = new DynamicEntityFilterData(tenant);
    filterData.setJoinFields(true);
    // FIXME NAO PODE SER PAGINADO TEM Q CARREGAR TUDO
    Page page = new Page(0, 50);
    DataPage<DynamicEntity> pagesOfDynamicEntities = dynamicEntityFacade
        .findByFilter(filterData, page);

    for (DynamicEntity dynamicEntity : pagesOfDynamicEntities.getData()) {
      logger.info("Creating dynamicEntity = " + dynamicEntity.getName());

      DynamicType.Builder builder = new ByteBuddy(ClassFileVersion.JAVA_V8)
          .subclass(DynamicBaseEntity.class, Default.DEFAULT_CONSTRUCTOR).serialVersionUid(-1L);

      // cria classe no package
      builder = builder.name(
          "br.com.dynamic." + dynamicEntity.getTenant().getSchemaName().toLowerCase() + "."
              + dynamicEntity.getName());

      builder = builder.annotateType(AnnotationDescription.Builder.ofType(Entity.class)
          .define("name", dynamicEntity.getName().toLowerCase())
          .build());
      builder = builder.annotateType(AnnotationDescription.Builder.ofType(Table.class)
          .define("name", dynamicEntity.getName().toLowerCase())
          .define("schema", dynamicEntity.getTenant().getSchemaName())
          .build());

      builder = makeFields(builder, dynamicEntity.getFields());
      Class<?> dynamicType = builder.make()
          .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
          .getLoaded();

      builder.make().saveIn(new File("c:/temp/classesmaked/", dynamicEntity.getName()));

      classesMaked.add(dynamicType);

    }

    return classesMaked;

  }

  /**
   * cria campos
   */
  private DynamicType.Builder makeFields(DynamicType.Builder builder, List<DynamicField> fields) {
    for (DynamicField field : fields) {
      builder = field.getFieldType().getType().makeField(builder, field);
    }
    return builder;
  }
}

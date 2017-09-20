package io.easycm.projects.facade;

import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.entity.QDynamicEntity;
import io.easycm.projects.filter.DynamicEntityFilterData;
import io.easycm.framework.base.facade.CrudFacade;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import java.util.UUID;

public interface DynamicEntityFacade extends CrudFacade<UUID, DynamicEntity, QDynamicEntity> {

  public DataPage<DynamicEntity> findByFilter(DynamicEntityFilterData filter, Page page);

  public DynamicEntity findByFilter(DynamicEntityFilterData filter);

  public DynamicEntity findById(UUID id);

  public DataPage<DynamicEntity> zoom(DynamicEntityFilterData filter, Page page);

}

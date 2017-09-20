package io.easycm.projects.dao;

import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.entity.QDynamicEntity;
import io.easycm.projects.filter.DynamicEntityFilterData;
import io.easycm.framework.base.dao.CrudDAO;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import java.util.UUID;

public interface DynamicEntityDAO extends CrudDAO<UUID, DynamicEntity, QDynamicEntity> {

  public DynamicEntity findByFilterQ(DynamicEntityFilterData filter);

  public DataPage<DynamicEntity> findByFilterQ(DynamicEntityFilterData filter, Page page);

  public DataPage<DynamicEntity> zoomQ(DynamicEntityFilterData filter, Page page);
}

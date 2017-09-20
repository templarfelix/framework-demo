package io.easycm.projects.dao.impl;

import io.easycm.projects.dao.DynamicEntityDAO;
import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.entity.QDynamicEntity;
import io.easycm.projects.filter.DynamicEntityFilterData;
import io.easycm.framework.base.dao.impl.CrudDAOImpl;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import io.easycm.framework.base.security.Log;
import java.util.UUID;
import javax.ejb.Stateless;

@Stateless
@Log
public class DynamicEntityDAOImpl extends
    CrudDAOImpl<UUID, DynamicEntity, QDynamicEntity> implements DynamicEntityDAO {

  private static final long serialVersionUID = 1L;

  QDynamicEntity qDynamicEntity = QDynamicEntity.dynamicEntity;

  @Override
  public DataPage<DynamicEntity> findByFilterQ(DynamicEntityFilterData filter, Page page) {
    return super.findQ(qDynamicEntity, filter, page);
  }

  public DynamicEntity findByFilterQ(DynamicEntityFilterData filter) {
    return super.findSingleQ(qDynamicEntity, filter);
  }

  public DataPage<DynamicEntity> zoomQ(DynamicEntityFilterData filter, Page page) {
    return super.findQ(qDynamicEntity, filter, page);
  }
}

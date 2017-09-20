package io.easycm.projects.dao;

import io.easycm.projects.entity.PoolStatus;
import io.easycm.projects.entity.QPoolStatus;
import io.easycm.projects.filter.PoolStatusFilterData;
import io.easycm.framework.base.dao.CrudDAO;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;

public interface PoolStatusDAO extends CrudDAO<Long, PoolStatus, QPoolStatus> {

  public PoolStatus findByFilterQ(PoolStatusFilterData filter);

  public DataPage<PoolStatus> findByFilterQ(PoolStatusFilterData filter, Page page);

  public DataPage<PoolStatus> zoomQ(PoolStatusFilterData filter, Page page);
}

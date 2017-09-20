package io.easycm.projects.facade;

import io.easycm.projects.entity.PoolStatus;
import io.easycm.projects.entity.QPoolStatus;
import io.easycm.projects.filter.PoolStatusFilterData;
import io.easycm.framework.base.facade.CrudFacade;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;

public interface PoolStatusFacade extends CrudFacade<Long, PoolStatus, QPoolStatus> {

  public DataPage<PoolStatus> findByFilter(PoolStatusFilterData filter, Page page);

  public PoolStatus findByFilter(PoolStatusFilterData filter);

  public PoolStatus findById(Long id);

  public DataPage<PoolStatus> zoom(PoolStatusFilterData filter, Page page);

}

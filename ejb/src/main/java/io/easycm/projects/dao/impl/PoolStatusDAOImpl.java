package io.easycm.projects.dao.impl;

import io.easycm.projects.dao.PoolStatusDAO;
import io.easycm.projects.entity.PoolStatus;
import io.easycm.projects.entity.QPoolStatus;
import io.easycm.projects.filter.PoolStatusFilterData;
import io.easycm.framework.base.dao.impl.CrudDAOImpl;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import io.easycm.framework.base.security.Log;
import javax.ejb.Stateless;

@Stateless
@Log
public class PoolStatusDAOImpl extends CrudDAOImpl<Long, PoolStatus, QPoolStatus> implements
    PoolStatusDAO {

  private static final long serialVersionUID = 1L;

  QPoolStatus qPoolStatus = QPoolStatus.poolStatus;

  @Override
  public DataPage<PoolStatus> findByFilterQ(PoolStatusFilterData filter, Page page) {
    return super.findQ(qPoolStatus, filter, page);
  }

  public PoolStatus findByFilterQ(PoolStatusFilterData filter) {
    return super.findSingleQ(qPoolStatus, filter);
  }

  public DataPage<PoolStatus> zoomQ(PoolStatusFilterData filter, Page page) {
    return super.findQ(qPoolStatus, filter, page);
  }
}

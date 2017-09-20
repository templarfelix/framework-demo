package io.easycm.projects.facade.impl;

import io.easycm.projects.dao.PoolStatusDAO;
import io.easycm.projects.entity.PoolStatus;
import io.easycm.projects.entity.QPoolStatus;
import io.easycm.projects.facade.PoolStatusFacade;
import io.easycm.projects.filter.PoolStatusFilterData;
import io.easycm.framework.base.facade.impl.CrudFacadeImpl;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PoolStatusFacadeImpl extends CrudFacadeImpl<Long, PoolStatus, QPoolStatus>
    implements PoolStatusFacade {

  private static final long serialVersionUID = 1L;

  @Inject
  public PoolStatusFacadeImpl(PoolStatusDAO dao) {
    super(dao);
  }

  @Override
  public DataPage<PoolStatus> findByFilter(PoolStatusFilterData filter, Page page) {
    return ((PoolStatusDAO) super.getDAO()).findByFilterQ(filter, page);
  }

  public PoolStatus findByFilter(PoolStatusFilterData filter) {
    return ((PoolStatusDAO) super.getDAO()).findByFilterQ(filter);
  }

  @Override
  public PoolStatus findById(Long id) {
    PoolStatusFilterData filter = new PoolStatusFilterData();
    filter.setId(id);

    return ((PoolStatusDAO) super.getDAO()).findByFilterQ(filter);
  }

  public DataPage<PoolStatus> zoom(PoolStatusFilterData filter, Page page) {
    return ((PoolStatusDAO) super.getDAO()).zoomQ(filter, page);
  }
}

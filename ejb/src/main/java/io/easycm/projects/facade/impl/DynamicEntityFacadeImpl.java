package io.easycm.projects.facade.impl;

import io.easycm.projects.dao.DynamicEntityDAO;
import io.easycm.projects.entity.DynamicEntity;
import io.easycm.projects.entity.QDynamicEntity;
import io.easycm.projects.facade.DynamicEntityFacade;
import io.easycm.projects.filter.DynamicEntityFilterData;
import io.easycm.framework.base.facade.impl.CrudFacadeImpl;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DynamicEntityFacadeImpl extends CrudFacadeImpl<UUID, DynamicEntity, QDynamicEntity>
    implements DynamicEntityFacade {

  private static final long serialVersionUID = 1L;

  @Inject
  public DynamicEntityFacadeImpl(DynamicEntityDAO dao) {
    super(dao);
  }

  @Override
  public DataPage<DynamicEntity> findByFilter(DynamicEntityFilterData filter, Page page) {
    return ((DynamicEntityDAO) super.getDAO()).findByFilterQ(filter, page);
  }

  public DynamicEntity findByFilter(DynamicEntityFilterData filter) {
    return ((DynamicEntityDAO) super.getDAO()).findByFilterQ(filter);
  }

  @Override
  public DynamicEntity findById(UUID id) {
    DynamicEntityFilterData filter = new DynamicEntityFilterData();
    filter.setId(id);

    // FIXME TIRAR LAZYS
    return ((DynamicEntityDAO) super.getDAO()).findByFilterQ(filter);
  }

  public DataPage<DynamicEntity> zoom(DynamicEntityFilterData filter, Page page) {
    return ((DynamicEntityDAO) super.getDAO()).zoomQ(filter, page);
  }
}

package io.easycm.projects.facade.impl;

import io.easycm.projects.dao.SampleDAO;
import io.easycm.projects.entity.QSample;
import io.easycm.projects.entity.Sample;
import io.easycm.projects.facade.SampleFacade;
import io.easycm.projects.filter.SampleFilterData;
import io.easycm.framework.base.facade.impl.CrudFacadeImpl;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SampleFacadeImpl extends CrudFacadeImpl<Long, Sample, QSample>
    implements SampleFacade {

  private static final long serialVersionUID = 1L;

  @Inject
  public SampleFacadeImpl(SampleDAO dao) {
    super(dao);
  }

  @Override
  public DataPage<Sample> findByFilter(SampleFilterData filter, Page page) {
    return ((SampleDAO) super.getDAO()).findByFilterQ(filter, page);
  }

  public Sample findByFilter(SampleFilterData filter) {
    return ((SampleDAO) super.getDAO()).findByFilterQ(filter);
  }

  @Override
  public Sample findById(Long id) {
    SampleFilterData filter = new SampleFilterData();
    filter.setId(id);

    // FIXME TIRAR LAZYS
    return ((SampleDAO) super.getDAO()).findByFilterQ(filter);
  }

  public DataPage<Sample> zoom(SampleFilterData filter, Page page) {
    return ((SampleDAO) super.getDAO()).zoomQ(filter, page);
  }
}

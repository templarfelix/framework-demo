package io.easycm.projects.dao.impl;

import io.easycm.projects.dao.SampleDAO;
import io.easycm.projects.entity.QSample;
import io.easycm.projects.entity.Sample;
import io.easycm.projects.filter.SampleFilterData;
import io.easycm.framework.base.dao.impl.CrudDAOImpl;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;
import io.easycm.framework.base.security.Log;
import javax.ejb.Stateless;

@Stateless
@Log
public class SampleDAOImpl extends CrudDAOImpl<Long, Sample, QSample> implements SampleDAO {

  private static final long serialVersionUID = 1L;

  QSample qSample = QSample.sample;

  @Override
  public DataPage<Sample> findByFilterQ(SampleFilterData filter, Page page) {
    return super.findQ(qSample, filter, page);
  }

  public Sample findByFilterQ(SampleFilterData filter) {
    return super.findSingleQ(qSample, filter);
  }

  public DataPage<Sample> zoomQ(SampleFilterData filter, Page page) {
    return super.findQ(qSample, filter, page);
  }
}

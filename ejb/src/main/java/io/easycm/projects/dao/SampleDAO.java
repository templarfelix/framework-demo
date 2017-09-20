package io.easycm.projects.dao;

import io.easycm.projects.entity.QSample;
import io.easycm.projects.entity.Sample;
import io.easycm.projects.filter.SampleFilterData;
import io.easycm.framework.base.dao.CrudDAO;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;

public interface SampleDAO extends CrudDAO<Long, Sample, QSample> {

  public Sample findByFilterQ(SampleFilterData filter);

  public DataPage<Sample> findByFilterQ(SampleFilterData filter, Page page);

  public DataPage<Sample> zoomQ(SampleFilterData filter, Page page);
}

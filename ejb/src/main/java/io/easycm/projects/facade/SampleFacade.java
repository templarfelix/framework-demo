package io.easycm.projects.facade;

import io.easycm.projects.entity.QSample;
import io.easycm.projects.entity.Sample;
import io.easycm.projects.filter.SampleFilterData;
import io.easycm.framework.base.facade.CrudFacade;
import io.easycm.framework.base.paging.DataPage;
import io.easycm.framework.base.paging.Page;

public interface SampleFacade extends CrudFacade<Long, Sample, QSample> {

  public DataPage<Sample> findByFilter(SampleFilterData filter, Page page);

  public Sample findByFilter(SampleFilterData filter);

  public Sample findById(Long id);

  public DataPage<Sample> zoom(SampleFilterData filter, Page page);

}

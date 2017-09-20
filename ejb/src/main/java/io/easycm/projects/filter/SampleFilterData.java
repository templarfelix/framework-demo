package io.easycm.projects.filter;

import io.easycm.projects.entity.QSample;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import io.easycm.framework.base.filter.FilterData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleFilterData implements FilterData<QSample> {

  public static final int BY_ID_ASC = 1;
  public static final int BY_ID_DESC = 2;

  private Long id;
  private String description;

  private Integer orderBy;

  public SampleFilterData() {
  }

  public SampleFilterData(Long id) {
    setId(id);
  }

  public SampleFilterData(String description) {
    setDescription(description);
  }

  public SampleFilterData(Long id, String description) {
    setId(id);
    setDescription(description);
  }

  @Override
  public Map<String, Object> getFilterData() {
    Map<String, Object> filter = new HashMap<String, Object>();
    if (getId() != null) {
      filter.put("id", getId());
    }
    if (getDescription() != null && !getDescription().isEmpty()) {
      filter.put("description", '%' + getDescription() + '%');
    }
    return filter;
  }

  @Override
  public Predicate getParamsQ(QSample entity) {
    BooleanBuilder builder = new BooleanBuilder();

    if (id != null) {
      builder.and(entity.id.eq(id));
    }
    if (getDescription() != null && !getDescription().isEmpty()) {
      builder.and(entity.description.eq(description));
    }

    return builder;

  }

  @Override
  public List<Expression<?>> getJoinFetchQ(QSample entity) {

    List<Expression<?>> fetchLst = new ArrayList<Expression<?>>();

    return fetchLst;
  }

  @Override
  public OrderSpecifier<?> getOrderByQ(QSample entity) {
    if (orderBy == null) {
      return null;
    }

    switch (orderBy) {
      case BY_ID_ASC:
        return entity.id.asc();
      case BY_ID_DESC:
        return entity.id.desc();
      default:
        return null;
    }
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Integer orderBy) {
    this.orderBy = orderBy;
  }

}

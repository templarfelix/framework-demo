package io.easycm.projects.filter;

import io.easycm.projects.entity.QPoolStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import io.easycm.framework.base.filter.FilterData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoolStatusFilterData implements FilterData<QPoolStatus> {

  public static final int BY_ID_ASC = 1;
  public static final int BY_ID_DESC = 2;

  private Long id;

  private Integer orderBy;

  public PoolStatusFilterData() {
  }

  public PoolStatusFilterData(Long id) {
    setId(id);
  }

  @Override
  public Map<String, Object> getFilterData() {
    Map<String, Object> filter = new HashMap<String, Object>();
    if (getId() != null) {
      filter.put("id", getId());
    }
    return filter;
  }

  @Override
  public Predicate getParamsQ(QPoolStatus entity) {
    BooleanBuilder builder = new BooleanBuilder();

    if (id != null) {
      builder.and(entity.id.eq(id));
    }

    return builder;

  }

  @Override
  public List<Expression<?>> getJoinFetchQ(QPoolStatus entity) {

    List<Expression<?>> fetchLst = new ArrayList<Expression<?>>();

    return fetchLst;
  }

  @Override
  public OrderSpecifier<?> getOrderByQ(QPoolStatus entity) {
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

  public Integer getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Integer orderBy) {
    this.orderBy = orderBy;
  }

}

package io.easycm.projects.filter;

import io.easycm.projects.entity.QDynamicEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import io.easycm.framework.base.filter.FilterData;
import io.easycm.framework.security.entity.Tenant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DynamicEntityFilterData implements FilterData<QDynamicEntity> {

  public static final int BY_ID_ASC = 1;
  public static final int BY_ID_DESC = 2;

  private UUID id;
  private String name;
  private String description;
  private Tenant tenant;

  private Integer orderBy;

  // FIXME FAZER JOIN DE FIELDS
  private Boolean joinFields = false;

  public DynamicEntityFilterData() {

  }

  public DynamicEntityFilterData(Tenant tenant) {
    setTenant(tenant);
  }

  public DynamicEntityFilterData(UUID id) {
    setId(id);
  }

  public DynamicEntityFilterData(String description) {
    setDescription(description);
  }

  @Override
  public Map<String, Object> getFilterData() {
    Map<String, Object> filter = new HashMap<String, Object>();
    if (getId() != null) {
      filter.put("id", getId());
    }
    if (getName() != null && !getName().isEmpty()) {
      filter.put("name", '%' + getName() + '%');
    }
    if (getDescription() != null && !getDescription().isEmpty()) {
      filter.put("description", '%' + getDescription() + '%');
    }
    // todo sem tenant
    return filter;
  }

  @Override
  public Predicate getParamsQ(QDynamicEntity entity) {
    BooleanBuilder builder = new BooleanBuilder();

    if (getId() != null) {
      builder.and(entity.id.eq(getId()));
    }
    if (getName() != null && !getName().isEmpty()) {
      builder.and(entity.description.eq(getName()));
    }
    if (getDescription() != null && !getDescription().isEmpty()) {
      builder.and(entity.description.eq(getDescription()));
    }
    if (getTenant() != null) {
      builder.and(entity.tenant.eq(getTenant()));
    }

    return builder;

  }

  @Override
  public List<Expression<?>> getJoinFetchQ(QDynamicEntity entity) {

    List<Expression<?>> fetchLst = new ArrayList<Expression<?>>();
    if (getJoinFields()) {
      fetchLst.add(entity.fields);
    }

    return fetchLst;
  }

  @Override
  public OrderSpecifier<?> getOrderByQ(QDynamicEntity entity) {
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


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

  public Integer getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Integer orderBy) {
    this.orderBy = orderBy;
  }

  public Boolean getJoinFields() {
    return joinFields;
  }

  public void setJoinFields(Boolean joinFields) {
    this.joinFields = joinFields;
  }
}

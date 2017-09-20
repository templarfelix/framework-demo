package io.easycm.projects.entity;

import io.easycm.framework.base.form.FormColumn;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Fernando on 09/04/2016.
 */
@Entity
@Table(name = "dynamicFieldEntity", schema = "dynamic")
public class DynamicFieldEntity extends DynamicField implements Serializable {

  @FormColumn(description = "dynamicfield_entity", label = "dynamicfield_entity_label", placeholder = "dynamicfield_entity_placeholder")
  @Getter
  @Setter
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
  private DynamicEntity entity;

}

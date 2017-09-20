package io.easycm.projects.entity;

import io.easycm.projects.entity.enums.FieldType;
import io.easycm.framework.base.entity.impl.LogBaseEntityImpl;
import io.easycm.framework.base.form.FormColumn;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Fernando on 09/04/2016.
 */
@Entity
@Table(name = "dynamicField", schema = "dynamic")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DynamicField extends LogBaseEntityImpl<Long> implements Serializable {

  @FormColumn(description = "dynamicfield_id", label = "dynamicfield_id_label", placeholder = "dynamicfield_id_placeholder")
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Getter
  @Setter
  private Long id;

  @FormColumn(description = "dynamicfield_description", label = "dynamicfield_description_label", placeholder = "dynamicfield_description_placeholder")
  @NotEmpty
  @Size(min = 2, max = 100)
  @Getter
  @Setter
  private String name;

  @FormColumn(description = "dynamicfield_description", label = "dynamicfield_description_label", placeholder = "dynamicfield_description_placeholder")
  @NotEmpty
  @Size(min = 0, max = 250)
  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private FieldType fieldType;

  @Getter
  @Setter
  private Boolean asId;

  @Getter
  @Setter
  private Boolean isArray;

  public Boolean isId() {
    return (asId != null && asId);
  }

}

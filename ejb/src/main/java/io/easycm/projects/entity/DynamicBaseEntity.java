package io.easycm.projects.entity;

import io.easycm.framework.base.entity.impl.LogBaseEntityImpl;
import io.easycm.framework.base.form.FormColumn;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by felix on 20/04/2017.
 */
@MappedSuperclass
public class DynamicBaseEntity extends LogBaseEntityImpl<UUID> {

  public DynamicBaseEntity() {
  }

  @FormColumn(description = "sample_id", label = "sample_id_label", placeholder = "sample_id_placeholder")
  @Id
  @Column(name = "id")
  @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
  @GeneratedValue(generator = "uuid-gen")
  //@Type(type="pg-uuid")
  private UUID uuid;

  @Override
  public UUID getId() {
    return uuid;
  }

  @Override
  public void setId(UUID uuid) {
    this.uuid = uuid;
  }
}

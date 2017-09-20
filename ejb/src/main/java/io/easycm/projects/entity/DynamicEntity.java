package io.easycm.projects.entity;

import io.easycm.framework.base.entity.impl.LogBaseEntityImpl;
import io.easycm.framework.base.form.FormColumn;
import io.easycm.framework.security.entity.Tenant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Fernando on 06/04/2016.
 */
@Entity
@Table(name = "dynamicEntity", schema = "dynamic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DYNAMICENTITY.FIND_ALL", query = "SELECT e FROM DynamicEntity e")})
public class DynamicEntity extends LogBaseEntityImpl<UUID> implements Serializable {

  @FormColumn(description = "sample_id", label = "sample_id_label", placeholder = "sample_id_placeholder")
  @Id
  @Column(name = "id")
  @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
  @GeneratedValue(generator = "uuid-gen")
  //@Type(type="pg-uuid")
  private UUID id;

  @FormColumn(description = "sample_description", label = "sample_description_label", placeholder = "sample_description_placeholder")
  @NotEmpty
  @Size(min = 2, max = 100)
  private String name;

  @FormColumn(description = "sample_description", label = "sample_description_label", placeholder = "sample_description_placeholder")
  @NotEmpty
  @Size(min = 0, max = 250)
  private String description;

  // VERSION FIELD
  private Boolean isAuditable;

  @ManyToOne
  private Tenant tenant;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DynamicField> fields = new ArrayList<DynamicField>();

  @Version
  private Long version;

  @Override
  public UUID getId() {
    return id;
  }

  @Override
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

  public Boolean getAuditable() {
    return isAuditable;
  }

  public void setAuditable(Boolean auditable) {
    isAuditable = auditable;
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

  public List<DynamicField> getFields() {
    return fields;
  }

  public void setFields(List<DynamicField> fields) {
    this.fields = fields;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}

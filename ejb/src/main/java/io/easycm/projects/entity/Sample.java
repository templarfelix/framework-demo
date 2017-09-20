package io.easycm.projects.entity;

import io.easycm.framework.base.entity.impl.LogBaseEntityImpl;
import io.easycm.framework.base.form.FormColumn;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Fernando on 01/04/2016.
 */
@Entity
@Table(name = "sample")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SAMPLE.FIND_ALL", query = "SELECT e FROM Sample e")})
public class Sample
    extends LogBaseEntityImpl<Long> implements Serializable {

  @FormColumn(description = "sample_id", label = "sample_id_label", placeholder = "sample_id_placeholder")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @FormColumn(description = "sample_description", label = "sample_description_label", placeholder = "sample_description_placeholder")
  @NotEmpty
  @Size(min = 3, max = 100)
  private String description;

  @Version
  private Long version;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Sample sample = (Sample) o;

    return id != null ? id.equals(sample.id) : sample.id == null;

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}

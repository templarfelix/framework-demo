package io.easycm.projects.entity;

import io.easycm.framework.base.entity.impl.LogBaseEntityImpl;
import io.easycm.framework.security.entity.Tenant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Fernando on 21/04/2016.
 */
@Entity
@Table(name = "poolStatus", schema = "dynamic")
public class PoolStatus extends LogBaseEntityImpl<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer idleConnections;

  private Integer activeConnections;

  private Integer threadsAwaitingConnection;

  private Integer totalConnections;

  @ManyToOne
  private Tenant tenant;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public Integer getIdleConnections() {
    return idleConnections;
  }

  public void setIdleConnections(Integer idleConnections) {
    this.idleConnections = idleConnections;
  }

  public Integer getActiveConnections() {
    return activeConnections;
  }

  public void setActiveConnections(Integer activeConnections) {
    this.activeConnections = activeConnections;
  }

  public Integer getThreadsAwaitingConnection() {
    return threadsAwaitingConnection;
  }

  public void setThreadsAwaitingConnection(Integer threadsAwaitingConnection) {
    this.threadsAwaitingConnection = threadsAwaitingConnection;
  }

  public Integer getTotalConnections() {
    return totalConnections;
  }

  public void setTotalConnections(Integer totalConnections) {
    this.totalConnections = totalConnections;
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

  @Override
  public String toString() {
    return "PoolStatus{" +
        "idleConnections=" + idleConnections +
        ", activeConnections=" + activeConnections +
        ", threadsAwaitingConnection=" + threadsAwaitingConnection +
        ", totalConnections=" + totalConnections +
        '}';
  }
}

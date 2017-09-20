package io.easycm.projects.job;

import io.easycm.projects.entity.PoolStatus;
import io.easycm.projects.facade.PoolStatusFacade;
import com.zaxxer.hikari.HikariPoolMXBean;
import io.easycm.framework.security.entity.Tenant;
import io.easycm.framework.security.facade.TenantFacade;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * Created by Fernando on 12/03/2016.
 */
@Stateless
// FIXME DESCOMENTAR E CORRIGIR
//@RunAs("Admin")
//@RunAsPrincipal("jobrunner@mksdev.com")
//@SecurityDomain("SecurityRealm")
public class PoolStatusJob {

  @Inject
  private Logger log;

  @Inject
  private TenantFacade tenantFacade;

  @Inject
  private PoolStatusFacade poolStatusFacade;

  @Schedule(hour = "*", minute = "*/30", persistent = false)
  public void getStatus() {
    try {
      MBeanServerFactory.findMBeanServer(null);

      MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

      ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool (Dynamic)");
      mBeanServer.isRegistered(poolName);
      HikariPoolMXBean poolProxy = JMX
          .newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);

      PoolStatus poolStatus = new PoolStatus();
      poolStatus.setIdleConnections(poolProxy.getIdleConnections());
      poolStatus.setActiveConnections(poolProxy.getActiveConnections());
      poolStatus.setThreadsAwaitingConnection(poolProxy.getThreadsAwaitingConnection());
      poolStatus.setTotalConnections(poolProxy.getTotalConnections());
      poolStatusFacade.save(poolStatus);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Schedule(hour = "*", minute = "*/60", persistent = false)
  public void getTenantStatus() {
    try {

      for (Tenant tenant : tenantFacade.findAll()) {
        MBeanServerFactory.findMBeanServer(null);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName poolName = new ObjectName(
            "com.zaxxer.hikari:type=Pool (" + tenant.getName().toLowerCase() + ")");
        mBeanServer.isRegistered(poolName);
        HikariPoolMXBean poolProxy = JMX
            .newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);

        PoolStatus poolStatus = new PoolStatus();
        poolStatus.setTenant(tenant);
        poolStatus.setIdleConnections(poolProxy.getIdleConnections());
        poolStatus.setActiveConnections(poolProxy.getActiveConnections());
        poolStatus.setThreadsAwaitingConnection(poolProxy.getThreadsAwaitingConnection());
        poolStatus.setTotalConnections(poolProxy.getTotalConnections());
        poolStatusFacade.save(poolStatus);
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}

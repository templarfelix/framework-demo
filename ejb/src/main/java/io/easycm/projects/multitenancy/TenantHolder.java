package io.easycm.projects.multitenancy;

/**
 * Created by Fernando on 29/03/2016.
 */
public class TenantHolder {

  private static final InheritableThreadLocal<String> currentTenantName = new InheritableThreadLocal<>();

  public static String getCurrentTenant() {
    return currentTenantName.get();
  }

  public static void setTenant(final String tenantName) {
    currentTenantName.set(tenantName);
  }

  public static void cleanupTenant() {
    currentTenantName.remove();
  }
}

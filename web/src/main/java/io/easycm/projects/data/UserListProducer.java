package io.easycm.projects.data;

import io.easycm.framework.security.entity.User;
import io.easycm.framework.security.facade.UserFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class UserListProducer {

  @Inject
  private UserFacade facade;

  private List<User> users;

  // @Named provides access the return value via the EL variable name
  // "members" in the UI (e.g.
  // Facelets or JSP view)
  @Produces
  @Named
  public List<User> getUsers() {
    return users;
  }

  public void onUserListChanged(
      @Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
    System.out.println("UserListProducer.onUserListChanged()");

    retrieveAllUsers();
  }

  @PostConstruct
  public void retrieveAllUsers() {
    users = facade.findAll();
  }
}

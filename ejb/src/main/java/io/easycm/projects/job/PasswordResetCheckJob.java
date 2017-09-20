package io.easycm.projects.job;

import io.easycm.framework.base.dao.exception.DaoException;
import io.easycm.framework.security.entity.PasswordReset;
import io.easycm.framework.security.facade.PasswordResetFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * Exemplo de job.<br />
 * Este job lista os usuários do sistema a cada cinco minutos. Isto pode ser
 * configurado na anotação &#64;Schedule do método listUsers.
 */
@Stateless
// FIXME DESCOMENTAR E CORRIGIR
//@RunAs("Admin")
//@RunAsPrincipal("jobrunner@mksdev.com")
//@SecurityDomain("SecurityRealm")
public class PasswordResetCheckJob {

  @Inject
  private Logger log;

  @Inject
  private PasswordResetFacade facade;

  /**
   * Deleta Hash de troca de senha a mais de 24 horas no sistema.<br />
   */
  @Schedule(hour = "*/12", minute = "*", persistent = false)
  public void runJOB() throws ConstraintViolationException, ValidationException, DaoException {
    List<PasswordReset> lista = facade.findAll();
    for (PasswordReset passwordReset : lista) {
      Date dateCompare = new Date();
      dateCompare.setTime(dateCompare.getTime() - 24 * 60 * 1000);
      if (passwordReset.getCreationDate().compareTo(new Date()) == -1) {
        log.warning("REMOVENDO HASH DE SENHA" + passwordReset.toString());
        facade.remove(passwordReset);
      }

    }
  }
}
package mx.gob.hidalgo.repss.planeacion.services;

import org.springframework.core.NestedCheckedException;

public class BusinessException extends NestedCheckedException {

  private static final long serialVersionUID = 1L;

  private static final String MESSAGE_ERROR_ONE = "";

  public BusinessException(String msg) {
    super(msg);
  }

}

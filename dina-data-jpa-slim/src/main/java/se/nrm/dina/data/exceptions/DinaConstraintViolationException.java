package se.nrm.dina.data.exceptions;

import java.util.List;
import javax.ejb.ApplicationException;
import se.nrm.dina.data.vo.ErrorBean;

/**
 *
 * @author idali
 */
@ApplicationException
public class DinaConstraintViolationException extends DinaException {

  private Throwable t;

  public DinaConstraintViolationException(ErrorBean errorBean, int errorCode) {
    super(errorBean, errorCode);
  }

  public DinaConstraintViolationException(List<ErrorBean> errorBeans, int errorCode) {
    super(errorBeans, errorCode);
  }

  public DinaConstraintViolationException(Throwable t) {
    this.t = t;
  }

  public Throwable getT() {
    return t;
  }
}

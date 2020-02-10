package se.nrm.dina.data.exceptions;

import java.util.List;
import javax.ejb.ApplicationException;
import se.nrm.dina.data.vo.ErrorBean;

/**
 *
 * @author idali
 */
@ApplicationException
public class DinaDatabaseException extends DinaException {

  private Throwable t;

  public DinaDatabaseException() {
    super();
  }

  public DinaDatabaseException(ErrorBean errorBean, int errorCode) {
    super(errorBean, errorCode);
  }

  public DinaDatabaseException(List<ErrorBean> errorBeans, int errorCode) {
    super(errorBeans, errorCode);
  }

  public DinaDatabaseException(String errorMsg, int errorCode) {
    super(errorMsg, errorCode);
  }

  public DinaDatabaseException(Throwable t) {
    this.t = t;
  }

  public Throwable getT() {
    return t;
  }
}

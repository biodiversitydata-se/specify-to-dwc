package se.nrm.dina.data.exceptions;

import java.util.List;
import javax.ejb.ApplicationException;
import se.nrm.dina.data.vo.ErrorBean;

/**
 *
 * @author idali
 */
@ApplicationException
public class DinaException extends RuntimeException {

  private int errorCode;
  private ErrorBean errorBean;
  private List<ErrorBean> errorBeans;

  public DinaException() {
  }

  public DinaException(String s) {
    super(s);
  }

  public DinaException(String s, int errorCode) {
    super(s);
    this.errorCode = errorCode;
  }

  public DinaException(ErrorBean errorBean, int errorCode) {
    this.errorBean = errorBean;
    this.errorCode = errorCode;
  }

  public DinaException(List<ErrorBean> errorBeans, int errorCode) {
    this.errorBeans = errorBeans;
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public ErrorBean getErrorBean() {
    return errorBean;
  }

  public List<ErrorBean> getErrorBeans() {
    return errorBeans;
  }

}

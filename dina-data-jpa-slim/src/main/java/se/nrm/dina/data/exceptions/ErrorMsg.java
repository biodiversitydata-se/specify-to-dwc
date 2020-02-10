package se.nrm.dina.data.exceptions;

/**
 *
 * @author idali
 */
public class ErrorMsg {

  private final String CLASSNAME_CONVERT_ERROR = "No such entity ";
  private final String FIELD_NEMA_INVALID = "No such field ";
  private final String NO_ID_ANNOTATED_FIELD_ERROR = "No id annotated field";

  private final int BAD_REQUEST_CODE = 400;

  private final int ERROR_CODE_NONUNIQUE_RESULT_CODE = 400;

  private static ErrorMsg instance = null;

  public static synchronized ErrorMsg getInstance() {
    if (instance == null) {
      instance = new ErrorMsg();
    }
    return instance;
  }

  public int getBadRequestCode() {
    return BAD_REQUEST_CODE;
  }

  public String getFieldNotExist(String entityName, String fieldName) {
    StringBuilder sb = new StringBuilder("The entity: ");
    sb.append(entityName);
    sb.append(" doesn't have this field: ");
    sb.append(fieldName);
    return sb.toString();
  }

  public String getNoIdAnnotatedFieldMsg() {
    return NO_ID_ANNOTATED_FIELD_ERROR;
  }

  public int getNonUniqueErrorCode() {
    return ERROR_CODE_NONUNIQUE_RESULT_CODE;
  }

  public String getEntityNameErrorMsg() {
    return CLASSNAME_CONVERT_ERROR;
  }

  public String getFieldNameInvalid() {
    return FIELD_NEMA_INVALID;
  }

}

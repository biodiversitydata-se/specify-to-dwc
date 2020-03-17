package se.nrm.bas.specify.data.service.logic.util;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;  
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@Slf4j
public class Util {
 
  private static Util instance = null;
  private StringBuilder mappingFilePathSB; 
  private final String underScore = "_"; 
  private final String defaultKey = "default";
  private final String nrmIndex = "nrm_index";
  private final String gnmIndex = "gnm_index";
  private final String nrm = "nrm"; 
  
  private final String success = "Solr index run success"; 
  private final String badRequest = "Bad Request"; 
  private final String notFound = "Not Found"; 
  private final String preconditionFailed = "Precondition Failed"; 
  private final String internalServerError = "Internal Server Error"; 
    
  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

  public static Util getInstance() {
    synchronized (Util.class) {
      if (instance == null) {
        instance = new Util();
      }
    }
    return instance;
  }
   
  public String getIndexCore(String institution) {
    return isNrm(institution) ? nrmIndex : gnmIndex;
  }
  
  public boolean isNrm(String institution) {
    return institution.equals(nrm);
  }
  
  public String getMappingKey(String institution, int collectionCode) {
    if(collectionCode == 0) {
      return defaultKey;
    }
    mappingFilePathSB = new StringBuilder();
    mappingFilePathSB.append(institution);
    mappingFilePathSB.append(underScore);
    mappingFilePathSB.append(collectionCode);
    return mappingFilePathSB.toString().trim();
  }
  

  
  public Date stringToDate(String strDate) { 
    if(strDate == null) {
      return null;
    } 
    try {
      return dateFormat.parse(strDate);
    } catch (ParseException e) { 
      return null;
    }
  }
  
  
  /**
   * Convert java.util.Date to String
   * @param date
   * @return String
   */
  public String dateToString(Date date) { 
    return date == null ? null : dateFormat.format(date);
  }
  
  public String convertDataValueToString(Object value) {
     
    if (value instanceof Integer) {
      return String.valueOf(value); 
    } else if (value instanceof Date) {
      return dateToString((Date)value);
    } else if (value instanceof java.util.Date) {
      return dateToString((Date)value); 
    } else if (value instanceof Boolean) {
      return String.valueOf(value); 
    } else {
      return (String)value;
    }
  }

//  400 Bad Request — Client sent an invalid request — such as lacking required request body or parameter
//401 Unauthorized — Client failed to authenticate with the server
//403 Forbidden — Client authenticated but does not have permission to access the requested resource
//404 Not Found — The requested resource does not exist
//412 Precondition Failed — One or more conditions in the request header fields evaluated to false
//500 Internal Server Error — A generic error occurred on the server
  public String getSolrPostResponse(int statusCode) {
    switch (statusCode) {  
      case 400:
        return badRequest; 
      case 404:
        return notFound; 
      case 412:
        return preconditionFailed;
      case 500:
        return internalServerError;
      default:
        return success; 
    }
  }
}

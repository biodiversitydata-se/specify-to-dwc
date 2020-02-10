package se.nrm.bas.specify.data.service.logic.util;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 

/**
 *
 * @author idali
 */
public class Util {
  
  //  private final String strDefault = "/default_";
//  private final String strProperties = ".properties"; 
  
  
  
  private static Util instance = null;
  private StringBuilder mappingFilePathSB; 
  private final String underScore = "_"; 
  private final String defaultKey = "default";
   
  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 

  public static Util getInstance() {
    synchronized (Util.class) {
      if (instance == null) {
        instance = new Util();
      }
    }
    return instance;
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
  
  
//    public String buildPropertyFilePath(String basePath, String institution, int collectionId) { 
//    mappingFilePathSB = new StringBuilder();
//    mappingFilePathSB.append(basePath);
//    if(collectionId == 0) { 
//      mappingFilePathSB.append(strDefault);
//      mappingFilePathSB.append(institution); 
//    } else {
//      mappingFilePathSB.append(institution);
//      mappingFilePathSB.append(underScore);
//      mappingFilePathSB.append(collectionId);
//    } 
//    mappingFilePathSB.append(strProperties);
//    return mappingFilePathSB.toString();
//  }
}

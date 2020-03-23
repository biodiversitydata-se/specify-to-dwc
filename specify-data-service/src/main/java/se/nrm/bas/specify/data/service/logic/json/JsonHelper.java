package se.nrm.bas.specify.data.service.logic.json;

import java.math.BigDecimal;
import java.util.Date; 
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType; 
import se.nrm.bas.specify.data.service.logic.util.Util; 

/**
 *
 * @author idali
 */
public class JsonHelper {
  
  private static JsonHelper instance = null;
  
  private final String idKey = "id";
 
  public static JsonHelper getInstance() {
    synchronized (JsonHelper.class) {
      if (instance == null) {
        instance = new JsonHelper();
      }
    }
    return instance;
  }
  
  public boolean isStringType(ValueType type) { 
    return type == JsonValue.ValueType.STRING;
  }
  
  public void addId(JsonObjectBuilder attBuilder, String id) {
    attBuilder.add(idKey, id);
  }

  
  public void addAttributes(JsonObjectBuilder attBuilder, String key, Object value) {
    
    if (value instanceof Integer) {
      attBuilder.add(key, (int) value);
    } else if (value instanceof Short) {
      attBuilder.add(key, (Short) value);
    } else if (value instanceof Date) {
      attBuilder.add(key, Util.getInstance().dateToString((Date) value));
    } else if (value instanceof java.util.Date) {
      attBuilder.add(key, Util.getInstance().dateToString((java.util.Date) value));
    } else if (value instanceof BigDecimal) {
      attBuilder.add(key, (BigDecimal) value);
    } else if (value instanceof Boolean) {
      attBuilder.add(key, (Boolean) value);
    } else if (value instanceof Double) {
      attBuilder.add(key, (Double) value);
    } else if (value instanceof Float) {
      attBuilder.add(key, (Float) value);
    } else if (value instanceof Long) {
      attBuilder.add(key, (Long) value);
    } else {
      attBuilder.add(key, (String) value); 
    }
  } 
}

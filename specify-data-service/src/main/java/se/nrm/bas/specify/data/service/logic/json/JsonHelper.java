package se.nrm.bas.specify.data.service.logic.json;

import java.math.BigDecimal;
import java.util.Date;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType; 
import se.nrm.bas.specify.data.service.logic.util.Util;
import se.nrm.dina.datamodel.EntityBean;

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
  
//  public String getStringValue(JsonObject json, String key) {
//    String field = json.getString(key);
//    
//     Object value;
//                  if(field.contains(comma)) {
//                    value = mappingValueForMultipleFields(bean, field);   
////                    addAttributes(builder, StringUtils.removeEnd(sb.toString().trim(), comma), key);
//                  } else {
//                    value = getMappingValue(bean, field); 
//                  }  
//                  if (value != null) {
//                      JsonHelper.getInstance().addAttributes(builder, value, key);
//                  }
//  }
  
 
  
  
  
//  public void addJosnObjectForBean(JsonObject json, EntityBean bean) {
//    
////    if (bean != null) {
////      json.keySet().stream()
////              .forEach(key -> {  
////                if (json.get(key).getValueType() == JsonValue.ValueType.STRING) {
////                  String field = json.getString(key);
////                  
////                  Object value;
////                  if(field.contains(comma)) {
////                    value = mappingValueForMultipleFields(bean, field);   
//////                    addAttributes(builder, StringUtils.removeEnd(sb.toString().trim(), comma), key);
////                  } else {
////                    value = getMappingValue(bean, field); 
////                  }  
////                  if (value != null) {
////                      JsonHelper.getInstance().addAttributes(builder, value, key);
////                  }
////                } else {
////                  if (ReflectionHelper.getInstance().isList(bean.getClass(), key)) {
////                    mappingCollectionFieldValue(json.getJsonObject(key), getCollectionValue(bean, key));
////                  } else if (ReflectionHelper.getInstance().isSet(bean.getClass(), key)) {
////                    mappingCollectionFieldValue(json.getJsonObject(key), getSetValue(bean, key));
////                  } else { 
////                    EntityBean value = (EntityBean) getObjectValue(bean, key);
////                    if (value != null) {
////                      mappingFieldValue(json.getJsonObject(key), value);
////                    }
////                  }
////                }
////              });
////    }
//  }
  
//  public void getMappingValue(JsonObject json, EntityBean bean) {
//    if (bean != null) {
//      json.keySet().stream()
//              .forEach(key -> {  
//                
//                
////                if (json.get(key).getValueType() == JsonValue.ValueType.STRING) {
////                  String field = json.getString(key);
////                  
////                  Object value;
////                  if(field.contains(comma)) {
////                    value = mappingValueForMultipleFields(bean, field);   
//////                    addAttributes(builder, StringUtils.removeEnd(sb.toString().trim(), comma), key);
////                  } else {
////                    value = getMappingValue(bean, field); 
////                  }  
////                  if (value != null) {
////                      JsonHelper.getInstance().addAttributes(builder, value, key);
////                  }
////                } else {
////                  if (ReflectionHelper.getInstance().isList(bean.getClass(), key)) {
////                    mappingCollectionFieldValue(json.getJsonObject(key), getCollectionValue(bean, key));
////                  } else if (ReflectionHelper.getInstance().isSet(bean.getClass(), key)) {
////                    mappingCollectionFieldValue(json.getJsonObject(key), getSetValue(bean, key));
////                  } else { 
////                    EntityBean value = (EntityBean) getObjectValue(bean, key);
////                    if (value != null) {
////                      mappingFieldValue(json.getJsonObject(key), value);
////                    }
////                  }
////                }
//              });
//    }
//  }
  
  
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

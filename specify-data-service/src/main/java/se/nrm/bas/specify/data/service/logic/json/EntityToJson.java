package se.nrm.bas.specify.data.service.logic.json;

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List; 
import java.util.Map; 
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.bas.specify.data.service.logic.reflection.ReflectionHelper;
import se.nrm.bas.specify.data.service.logic.util.Util;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */
@Slf4j
public class EntityToJson implements Serializable {

//  private final String comma = ","; 
//  private final String dot = "."; 
  private boolean isCollection;
  private Map<String, List<String>> map; 
  
  private JsonArrayBuilder arrayBuilder; 
  
  public Object getStringValueFromEntity(EntityBean bean, String field) {
    return ReflectionHelper.getInstance().getValueFromFieldOrMethod(bean, field); 
  }

  public void convertEntityToJson(JsonObjectBuilder builder, JsonObject json, EntityBean bean, boolean isCollection) {
    json.keySet().stream()
            .forEach(key -> {
              if (JsonHelper.getInstance().isStringType(json.get(key).getValueType())) {
                Object value = getStringValueFromEntity(bean, json.getString(key));  
                if (value != null) { 
                  if(isCollection) {
                    List<String> list;
                    if(map.containsKey(key)) {
                      list = map.get(key); 
                    } else {
                      list = new ArrayList();  
                    } 
                    list.add(Util.getInstance().convertDataValueToString(value));
                    map.put(key, list);
                  } else {
                     JsonHelper.getInstance().addAttributes(builder, key, value);  
                  } 
                } 
              } else {
                if(ReflectionHelper.getInstance().isCollection(bean.getClass(), key)) {
                  List<EntityBean> beans = ReflectionHelper.getInstance().getChildListFromParent(bean, key);
                  convertEntitiesToJson(builder, json.getJsonObject(key), beans);
                } else { 
                  EntityBean child = (EntityBean) ReflectionHelper.getInstance().getChildFromParent(bean, key);
                  if (child != null) {
                    convertEntityToJson(builder, json.getJsonObject(key), child, isCollection);
                  }
                }
              }
            });
  }
   
  public void convertEntitiesToJson(JsonObjectBuilder builder, JsonObject json, List<EntityBean> beans) {
    isCollection = true; 
    map = new HashMap(); 
    beans.stream()
            .forEach(bean -> {
              convertEntityToJson(builder, json, bean, isCollection);
            });   
    arrayBuilder = Json.createArrayBuilder(); 
    map.keySet().stream()
            .forEach(key -> {
              List<String> list = map.get(key);
              list.stream()
                      .forEach(l -> { 
                        arrayBuilder.add(l);
                      }); 
              builder.add(key, arrayBuilder);
            }); 
    isCollection = false;
  }  
}

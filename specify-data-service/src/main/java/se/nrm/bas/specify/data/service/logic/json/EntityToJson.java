package se.nrm.bas.specify.data.service.logic.json;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List; 
import java.util.Map;  
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
 
  private boolean isCollection;
   
  public Object getStringValueFromEntity(EntityBean bean, String field) {
    return ReflectionHelper.getInstance().getValueFromFieldOrMethod(bean, field); 
  }

  public void convertEntityToJson(JsonObjectBuilder builder, JsonObject json, 
          EntityBean bean, Map<String, List<String>> map, boolean isCollection) { 
    
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
                  convertEntitiesToJson(builder, json.getJsonObject(key), beans, map);
                } else { 
                  EntityBean child = (EntityBean) ReflectionHelper.getInstance().getChildFromParent(bean, key); 
                  if (child != null) {
                    convertEntityToJson(builder, json.getJsonObject(key), child, map, isCollection);
                  }
                }
              }
            });
  }
   
  public void convertEntitiesToJson(JsonObjectBuilder builder, JsonObject json, 
          List<EntityBean> beans, Map<String, List<String>> map) { 
    isCollection = true;  
    beans.stream()
            .forEach(bean -> { 
              convertEntityToJson(builder, json, bean, map, isCollection);
            });    
    map.keySet().stream()
            .forEach(key -> { 
              builder.add(key, String.join(", ", map.get(key)));
            }); 
    isCollection = false;
  }  
}

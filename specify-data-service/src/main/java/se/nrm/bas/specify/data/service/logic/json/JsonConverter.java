package se.nrm.bas.specify.data.service.logic.json;
  
import java.io.Serializable; 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder; 
import lombok.extern.slf4j.Slf4j;  
import se.nrm.bas.specify.data.service.logic.reflection.ReflectionHelper; 
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */
@Slf4j
public class JsonConverter implements Serializable {


  @Inject
  private EntityToJson entityToJson;
  
  private JsonArrayBuilder arrayBuilder;
  private JsonObjectBuilder builder;
  
  private Map<String, List<String>> map; 
  
  private final String filterKey = "filters";
  private final String determinations = "determinations";
  
  public JsonConverter() {
    
  }
  
  public JsonConverter(EntityToJson entityJson) { 
    this.entityToJson = entityJson;
  }
    
  public JsonArray convert(List<EntityBean> beans, String institution, 
          int collectionId,  JsonObject mappingJson) {  
    log.info("convert: total number: {}", beans.size());
    arrayBuilder = Json.createArrayBuilder();
    builder = Json.createObjectBuilder();
  
    beans.stream()
            .forEach(bean -> {
              JsonHelper.getInstance().addId(builder, bean.getEntityId()); 
              map = new HashMap<>();
              mappingJson.keySet().stream()
                      .filter(key -> !key.equals(filterKey))
                      .forEach(key -> {
                        if (JsonHelper.getInstance().isStringType(mappingJson.get(key).getValueType())) {
                          Object value = entityToJson.getStringValueFromEntity(bean, mappingJson.getString(key)); 
                          if(value != null) {
                            JsonHelper.getInstance().addAttributes(builder, key, value);
                          }
                        } else {  
                          if (ReflectionHelper.getInstance().isCollection(bean.getClass(), key)) { 
                            List<EntityBean> list = ReflectionHelper.getInstance().getChildListFromParent(bean, key); 
                            if(key.equals(determinations)) {
                              entityToJson.convertEntityToJson(builder, mappingJson.getJsonObject(key), list, map);
                            } else {
                              entityToJson.convertEntitiesToJson(builder, mappingJson.getJsonObject(key), list, map);  
                            } 
//                            entityToJson.convertEntitiesToJson(builder, mappingJson.getJsonObject(key), list, map);  
                          } else {
                            EntityBean child = (EntityBean) ReflectionHelper.getInstance().getChildFromParent(bean, key); 
                            if (child != null) {
                              entityToJson.convertEntityToJson(builder, mappingJson.getJsonObject(key), child, map, false); 
                            }
                          } 
                  } 
              });
              arrayBuilder.add(builder);
            }); 
    return arrayBuilder.build(); 
  }  
}

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
import se.nrm.bas.specify.data.service.logic.propertyfiles.MappingFileReader;
import se.nrm.bas.specify.data.service.logic.reflection.ReflectionHelper;
import se.nrm.bas.specify.data.service.logic.util.Util; 
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */
@Slf4j
public class JsonConverter implements Serializable {

  @Inject
  private MappingFileReader mappingFile;
  @Inject
  private EntityToJson entityToJson;

  private JsonArrayBuilder arrayBuilder;
  private JsonObjectBuilder builder;
  
  private Map<String, List<String>> map; 
    
  public JsonArray convert(List<EntityBean> beans, String institution, int collectionId) {
    log.info("convert -- {} -- {}", institution, collectionId);
  
    JsonObject json = mappingFile.read(Util.getInstance().getMappingKey(institution, collectionId));  
    arrayBuilder = Json.createArrayBuilder();
    builder = Json.createObjectBuilder();
 
    beans.stream()
            .forEach(bean -> {
              JsonHelper.getInstance().addId(builder, bean.getIdentityString());
              
              map = new HashMap<>();
              json.keySet().stream()
                      .forEach(key -> {
                        if (JsonHelper.getInstance().isStringType(json.get(key).getValueType())) {
                          Object value = entityToJson.getStringValueFromEntity(bean, json.getString(key)); 
                          if(value != null) {
                            JsonHelper.getInstance().addAttributes(builder, key, value);
                          }
                        } else {  
                          if (ReflectionHelper.getInstance().isCollection(bean.getClass(), key)) {
                            List<EntityBean> list = ReflectionHelper.getInstance().getChildListFromParent(bean, key); 
                            entityToJson.convertEntitiesToJson(builder, json.getJsonObject(key), list, map);  
                          } else {
                            EntityBean child = (EntityBean) ReflectionHelper.getInstance().getChildFromParent(bean, key); 
                            if (child != null) {
                              entityToJson.convertEntityToJson(builder, json.getJsonObject(key), child, map, false); 
                            }
                          } 
                  } 
              });  
              arrayBuilder.add(builder);
            }); 
    return arrayBuilder.build(); 
  }  
}

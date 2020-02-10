package se.nrm.bas.specify.data.service.logic.propertyfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream; 
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject; 
import lombok.extern.slf4j.Slf4j;
import se.nrm.bas.specify.data.service.logic.InitialProperties; 

/**
 *
 * @author idali
 */
@Slf4j
public class MappingFileReader {

  @Inject
  private InitialProperties properties;
  
  private final String strDefault = "default";

  public JsonObject read(String mappingKey) {
    log.info("read: {}", mappingKey);
  
    InputStream fis = null;
    try {
      fis = new FileInputStream(properties.getDefaultMappingFilePath());
      JsonArray array = Json.createReader(fis).readArray(); 
      JsonObject json = array.stream()
              .filter(a -> a.asJsonObject().containsKey(mappingKey))
              .findAny().orElse(array.getJsonObject(0)).asJsonObject();
      return json.keySet().contains(mappingKey) ? json.getJsonObject(mappingKey) : json.getJsonObject(strDefault); 
    } catch (FileNotFoundException ex) {
      log.error(ex.getMessage()); 
    } finally {
      try {
        if(fis != null) {
           fis.close();
        } 
      } catch (IOException ex) {
        log.error(ex.getMessage());
      }
    }
    return null;
  }
}

package se.nrm.bas.specify.data.service.logic.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream; 
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class JsonFileReader {
  
  private InputStream fis;
  private final String defaultKey = "default";
  
  @Inject
  private InitialProperties properties; 
  
  @PostConstruct
  public void init() {
    fis = null;
    try {
      fis = new FileInputStream(properties.getDefaultMappingFilePath());      
    } catch (FileNotFoundException ex) {
      log.error(ex.getMessage());      
    } finally {
      try {
        if (fis != null) {
          fis.close();
        }        
      } catch (IOException ex) {
        log.error(ex.getMessage());
      }
    }    
  }
  
  public JsonObject read(String mappingKey) {
    log.info("read: {}", mappingKey);
    
    try {
      fis = new FileInputStream(properties.getDefaultMappingFilePath());
      JsonArray array = Json.createReader(fis).readArray();   
      JsonObject json = array.stream()
              .filter(a -> a.asJsonObject().containsKey(mappingKey))
              .findAny().orElse(array.getJsonObject(0)).asJsonObject();
      return json.containsKey(mappingKey) ? json.getJsonObject(mappingKey) 
              : json.getJsonObject(defaultKey); 
    } catch (FileNotFoundException ex) {
      log.error(ex.getMessage());      
    } finally {
      try {
        if (fis != null) {
          fis.close();
        }        
      } catch (IOException ex) {
        log.error(ex.getMessage());
      }
    }
    return null;
  }
  
  @PreDestroy
  public void destory() {
    if (fis != null) {
      try {
        fis.close();
      } catch (IOException ex) {
        log.error(ex.getMessage());
      }
    }
  }
}

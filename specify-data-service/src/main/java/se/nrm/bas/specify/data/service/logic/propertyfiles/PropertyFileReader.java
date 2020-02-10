package se.nrm.bas.specify.data.service.logic.propertyfiles;
 
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties; 
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.bas.specify.data.service.logic.InitialProperties; 
/**
 *
 * @author idali
 */
@Slf4j
public class PropertyFileReader {
  
  @Inject
  private InitialProperties properties;
     
  public Map<String, String> read(String institution, int collectionId) {  
    
    String basePath = properties.getDefaultMappingFilePath();
    String mappingFilePath = "";
//            Util.getInstance().buildPropertyFilePath(basePath, institution, collectionId);
    
    Properties props = new Properties(); 
    try {
      props.load(new FileReader(mappingFilePath));  
    } catch (IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
    return props.entrySet().stream().collect(
          Collectors.toMap(
               e -> e.getKey().toString(),
               e -> e.getValue().toString()
          )
      );  
  } 
}

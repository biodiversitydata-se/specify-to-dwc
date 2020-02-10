package se.nrm.bas.specify.data.service.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties; 
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray; 
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue; 
import javax.json.stream.JsonParser;
//import se.nrm.bas.specify.data.service.logic.propertyfiles.MappingFileReader;

/**
 *
 * @author idali
 */
public class Main {

  public static void main(String... args) throws IOException {
    String filePath = "/Users/idali/java/repos/bas/specify-to-dwc/mapping_files/mapping.json";
 
    
    
    InputStream fis = null;
    try {
      fis = new FileInputStream(filePath);
      
      JsonArray array = Json.createReader(fis).readArray();
      
      
      JsonParser jsonParser = Json.createParser(fis);
      
      JsonValue value = jsonParser.getArrayStream().filter(j -> j.asJsonObject().containsKey("nrm_786432"))
              .findFirst().get();
      
      String string = value.asJsonObject().toString();

      System.out.println("string: " + string);
//    String mappingFilePath = Util.getInstance().buildPropertyFilePath(basePath, institution, collectionId);
//
//    Properties props = new Properties(); 
//    try {
//      props.load(new FileReader(mappingFilePath));  
//    } catch (IOException ex) {
//      log.error(ex.getMessage());
//      return null;
//    }
//    return props.entrySet().stream().collect(
//          Collectors.toMap(
//               e -> e.getKey().toString(),
//               e -> e.getValue().toString()
//          )
//      );  
    } catch (FileNotFoundException ex) {
      System.out.println("ex: " + ex.getMessage());
//      Logger.getLogger(MappingFileReader.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        fis.close();
      } catch (IOException ex) {
        System.out.println("ex: " + ex.getMessage());
//        Logger.getLogger(MappingFileReader.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

//    Properties props = new Properties();
//    props.load(new FileReader(filePath));
//
//    System.out.println("props..." + props);
//    Map<String, String> map = props.entrySet().stream().collect(
//            Collectors.toMap(
//                    e -> e.getKey().toString(),
//                    e -> e.getValue().toString()
//            )
//    );
//
//    System.out.println("map ... " + map);
  }

}

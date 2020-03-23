package se.nrm.bas.specify.data.service.logic.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.After; 
import org.junit.AfterClass;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
public class EntityToJsonTest {
  
  private EntityToJson instance;
  private static Testentity testBean; 
  private static String mappingFilePath;
  private static JsonObject json;
  private JsonObjectBuilder builder; 
  private static InputStream fis;
  
  public EntityToJsonTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    mappingFilePath = "src/test/resources/mapping.json";
    try {
      fis = new FileInputStream(mappingFilePath); 
    } catch (FileNotFoundException ex) {
      System.out.println("error" + ex.getMessage());
    }
    testBean = new Testentity(10);
    testBean.setString("test string");
    testBean.setS((short) 18);
    testBean.setBgDecimal(BigDecimal.valueOf(18.7)); 
    testBean.setIsTrue(true); 
    
    json = Json.createReader(fis).readObject();  
  }

  @AfterClass
  public static void tearDownClass() {
    if(fis != null) {
      try {
        fis.close();
      } catch (IOException ex) { 
      }
    }
    testBean = null;
    json = null;
  }
   
  @Before
  public void setUp() {  
    builder = Json.createObjectBuilder(); 
    instance = new EntityToJson();
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of getStringValueFromEntity method, of class EntityToJson.
   */
  @Test
  public void testGetStringValueFromEntity() {
    System.out.println("getStringValueFromEntity");
  
    String field = "string";  
    String expResult = "test string";
    Object result = instance.getStringValueFromEntity(testBean, field);
    assertEquals(expResult, result); 
  }

  /**
   * Test of convertEntityToJson method, of class EntityToJson.
   */
  @Test
  public void testConvertEntityToJson() {
    System.out.println("convertEntityToJson");
       
    Map<String, List<String>> map = new HashMap();
    boolean isCollection = false;  
    instance.convertEntityToJson(builder, json, testBean, map, isCollection);  
    
    JsonObject result = builder.build();
    assertEquals(4, result.size());
  }

  /**
   * Test of convertEntitiesToJson method, of class EntityToJson.
   */
  @Test
  public void testConvertEntitiesToJson() {
    System.out.println("convertEntitiesToJson");
    
    List<EntityBean> list = new ArrayList();
    list.add(testBean);
    list.add(testBean);
 
    Map<String, List<String>> map = new HashMap();
    instance = new EntityToJson();
    instance.convertEntitiesToJson(builder, json, list, map); 
    
    JsonObject result = builder.build();  
    assertEquals(4, result.size());
  }
  
}

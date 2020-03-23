package se.nrm.bas.specify.data.service.logic.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;  
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.times;  
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import se.nrm.bas.specify.data.service.logic.propertyfiles.MappingFileReader;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class JsonConverterTest {
  
  @Mock
  private MappingFileReader mappingFileReader;
  
  @InjectMocks
  private EntityToJson entityToJson;
   
  private JsonConverter instance;
  
  private static Testentity testBean; 
  private static String mappingFilePath;
  private static JsonObject json;
  private static InputStream fis;
  
  private List<EntityBean> beans;
  
  public JsonConverterTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    mappingFilePath = "src/test/resources/mapping.json";  
    try {
      fis = new FileInputStream(mappingFilePath);  
    } catch (FileNotFoundException ex) {
      System.out.println("error" + ex.getMessage());
    }
    json = Json.createReader(fis).readObject();
    
    testBean = new Testentity(10);
    testBean.setString("test string");
    testBean.setS((short) 18);
    testBean.setBgDecimal(BigDecimal.valueOf(18.7)); 
    testBean.setIsTrue(true);  
  }
  
  @AfterClass
  public static void tearDownClass() {
    if(fis != null) {
      try {
        fis.close();
      } catch (IOException ex) {
      }
    }
    json = null;
  }
  
  @Before
  public void setUp() {

  }
  
  @After
  public void tearDown() {
     
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new JsonConverter();
    assertNotNull(instance); 
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructorException() {
    
    beans = new ArrayList();
    instance = new JsonConverter();
    assertNotNull(instance); 
    instance.convert(beans, "nrm", 0);
  }
  
  /**
   * Test of convert method, of class JsonConverter.
   */
  @Test
  public void testConvert() {
    System.out.println("convert");
       
    beans = new ArrayList();
    beans.add(testBean);
    beans.add(testBean);
    
    String institution = "nrm";
    int collectionId = 166; 
    when(mappingFileReader.read(any(String.class))).thenReturn(json);
    instance = new JsonConverter(mappingFileReader, entityToJson);
 
    JsonArray result = instance.convert(beans, institution, collectionId); 
    assertEquals(2, result.size());  
    verify(mappingFileReader, times(1)).read(any(String.class)); 
  }
  
}

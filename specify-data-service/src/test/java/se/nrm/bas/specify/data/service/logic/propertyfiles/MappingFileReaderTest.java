package se.nrm.bas.specify.data.service.logic.propertyfiles;
 
import javax.json.JsonObject;
import org.junit.After; 
import org.junit.AfterClass;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass; 
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.bas.specify.data.service.logic.InitialProperties; 

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class) 
public class MappingFileReaderTest {
 
  @Mock
  private InitialProperties properties; 
  
  private MappingFileReader instance;
   
  private static String mappingFilePath;

  public MappingFileReaderTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    mappingFilePath = "src/test/resources/mapping_2.json"; 
  }

  @AfterClass
  public static void tearDownClass() { 
  }

  @Before
  public void setUp() { 
    when(properties.getDefaultMappingFilePath()).thenReturn(mappingFilePath);  
  }

  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new MappingFileReader();
    assertNotNull(instance);
  }

  @Test(expected = NullPointerException.class)
  public void testDefaultConstructorException() {
    instance = new MappingFileReader();
    instance.read(mappingFilePath);
  }
  
  /**
   * Test of read method, of class MappingFileReader.
   */
  @Test
  public void testRead() {
    System.out.println("read"); 
    String mappingKey = "test";  
    instance = new MappingFileReader(properties);
    JsonObject result = instance.read(mappingKey); 
    assertNotNull(result);
    assertEquals(2, result.size()); 
    verify(properties, times(1)).getDefaultMappingFilePath(); 
  }
  
  @Test
  public void testReadException() {
    System.out.println("read"); 
    String mappingKey = "test";  
    
    when(properties.getDefaultMappingFilePath()).thenReturn("wrong/path");  
    instance = new MappingFileReader(properties);
    JsonObject result = instance.read(mappingKey);  
    assertTrue(result == null); 
    verify(properties, times(1)).getDefaultMappingFilePath(); 
  }
  
  @Test
  public void testReadWithCollectionCode() {
    System.out.println("read");
      
    String mappingKey = "nrm_163840";  
    
    instance = new MappingFileReader(properties);
    JsonObject result = instance.read(mappingKey);
    assertNotNull(result);
    assertEquals(3, result.size()); 
    verify(properties, times(1)).getDefaultMappingFilePath(); 
  }
  
}

package se.nrm.bas.specify.data.service.logic.json;
   
import java.math.BigDecimal;
import javax.json.Json; 
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*; 

/**
 *
 * @author idali
 */
public class JsonHelperTest {
  
  private JsonHelper instance;
  private JsonObjectBuilder attBuilder;
  
  public JsonHelperTest() {
  }
  
  @Before
  public void setUp() {
    instance = JsonHelper.getInstance(); 
    attBuilder = Json.createObjectBuilder();
  }
  
  @After
  public void tearDown() {
    instance = null;
    attBuilder = null;
  }

  /**
   * Test of getInstance method, of class JsonHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");  
    assertNotNull(instance); 
  }

  /**
   * Test of isStringType method, of class JsonHelper.
   */
  @Test
  public void testIsStringTypeTrue() {
    System.out.println("isStringType");
    JsonValue.ValueType type = JsonValue.ValueType.STRING;
     
    boolean result = instance.isStringType(type);
    assertEquals(true, result); 
  }
  
  @Test
  public void testIsStringTypeFalse() {
    System.out.println("isStringType");
    JsonValue.ValueType type = JsonValue.ValueType.NUMBER;
     
    boolean result = instance.isStringType(type);
    assertEquals(false, result); 
  }

  /**
   * Test of addId method, of class JsonHelper.
   */
  @Test
  public void testAddId() {
    System.out.println("addId");
     
    int id = 15; 
    instance.addId(attBuilder, id); 
     
    assertEquals(id, attBuilder.build().getInt("id")); 
  }

  /**
   * Test of addAttributes method, of class JsonHelper.
   */
  @Test
  public void testAddAttributes() {
    System.out.println("addAttributes"); 
    
    String idKey = "id";
    int id = 18;  
    
    String stringKey = "string";
    String string = "test string";
    
    String shortKey = "short";
    short s = (short) 6;
     
    java.util.Date date1 = new java.util.Date(12345);//ms since 1970 Jan 1 midnight
    java.sql.Date date2 = new java.sql.Date(12345);
    
    String date1Key = "date1";
    String date2key = "date2";
    
    BigDecimal bd = BigDecimal.valueOf(18.6);
    String bdKey = "bdkey";
    
    boolean b = true;
    String booleanKey = "booleanKey";
    
    double d = 18.7;
    String doubleKey = "doubleKey";
    
    float f = 12.86f;
    String floatkey = "floatKey";
    
    long l = 38;
    String longKey = "longKey";
    
    instance.addAttributes(attBuilder, idKey, id); 
    instance.addAttributes(attBuilder, stringKey, string);
    instance.addAttributes(attBuilder, shortKey, s);
    instance.addAttributes(attBuilder, date1Key, date1);
    instance.addAttributes(attBuilder, date2key, date2);
    instance.addAttributes(attBuilder, bdKey, bd);
    instance.addAttributes(attBuilder, booleanKey, b);
    instance.addAttributes(attBuilder, doubleKey, d);
    instance.addAttributes(attBuilder, floatkey, f);
    instance.addAttributes(attBuilder, longKey, l);
     
    assertEquals(10, attBuilder.build().size()); 
  }
  
}

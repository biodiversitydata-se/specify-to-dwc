package se.nrm.bas.specify.data.service.logic.util;
 
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class UtilTest {
  
  private Util instance;
  
  public UtilTest() {
  }
   
  @Before
  public void setUp() {
    instance = Util.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class Util.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");  
    assertNotNull(instance); 
  }

  /**
   * Test of getIndexCore method, of class Util.
   */
  @Test
  public void testGetIndexCoreNrm() {
    System.out.println("getIndexCore");
    String institution = "nrm"; 
    String expResult = "nrm_index";
    String result = instance.getIndexCore(institution);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetIndexCoreGnm() {
    System.out.println("getIndexCore");
    String institution = "gnm"; 
    String expResult = "gnm_index";
    String result = instance.getIndexCore(institution);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of isNrm method, of class Util.
   */
  @Test
  public void testIsNrmTrue() {
    System.out.println("isNrm");
    
    String institution = "nrm"; 
    boolean expResult = true;
    boolean result = instance.isNrm(institution);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of isNrm method, of class Util.
   */
  @Test
  public void testIsNrmFalse() {
    System.out.println("isNrm");
    
    String institution = "gnm"; 
    boolean expResult = false;
    boolean result = instance.isNrm(institution);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMappingKey method, of class Util.
   */
  @Test
  public void testGetMappingKey() {
    System.out.println("getMappingKey");
    
    String institution = "nrm";
    int collectionCode = 10;  
    String result = instance.getMappingKey(institution, collectionCode);
    assertNotNull(result); 
    assertTrue(result.contains("nrm_10"));
  }
  
  @Test
  public void testGetMappingDefaultKey() {
    System.out.println("getMappingKey");
    
    String institution = "nrm";
    int collectionCode = 0;  
    String result = instance.getMappingKey(institution, collectionCode);
    assertNotNull(result); 
    assertTrue(result.contains("default"));
  }

  /**
   * Test of stringToDate method, of class Util.
   */
  @Test
  public void testStringToDate() {
    System.out.println("stringToDate");
    String strDate = "2018-02-08";  
    Date result = instance.stringToDate(strDate);  
    
    LocalDate localDate = result.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     
    assertEquals(localDate.getYear(), 2018);
    assertEquals(localDate.getMonthValue(), 2);
    assertEquals(localDate.getDayOfMonth(), 8);
  }
  
  @Test
  public void testStringToDateWithNull() {
    System.out.println("stringToDate"); 
    Date result = instance.stringToDate(null);  
    assertNull(result);
  }

  @Test 
  public void testStringToDateWithException() {
    System.out.println("stringToDate"); 
    Date result = instance.stringToDate("xuye-e");   
    assertNull(result);
  }
  
  /**
   * Test of dateToString method, of class Util.
   */
  @Test
  public void testDateToString() {
    System.out.println("dateToString");
    
    LocalDate localDate = LocalDate.of(2018, 02, 8);
    Date date = java.sql.Date.valueOf(localDate);
    String expResult = "2018-02-08";
    String result = instance.dateToString(date);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testDateToStringNull() {
    System.out.println("dateToString");
     
    String result = instance.dateToString(null);
    assertNull(result); 
  }

  /**
   * Test of convertDataValueToString method, of class Util.
   */
  @Test
  public void testConvertDataValueToStringInt() {
    System.out.println("convertDataValueToString");
    Object value = 10; 
    String expResult = "10";
    String result = instance.convertDataValueToString(value);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testConvertDataValueToStringDate() {
    System.out.println("convertDataValueToString");
    
    LocalDate localDate = LocalDate.of(2018, 02, 8);
    Date date = java.sql.Date.valueOf(localDate);
     
    String expResult = "2018-02-08";
    String result = instance.convertDataValueToString(date);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testConvertDataValueToStringDateJavaUtil() {
    System.out.println("convertDataValueToString");
    
    LocalDate localDate = LocalDate.of(2018, 02, 8);
    java.util.Date date = java.sql.Date.valueOf(localDate);
     
    String expResult = "2018-02-08";
    String result = instance.convertDataValueToString(date);
    assertEquals(expResult, result); 
  }
   
  @Test
  public void testConvertDataValueToBoolean() {
    System.out.println("convertDataValueToString");
     
    String expResult = "false";
    String result = instance.convertDataValueToString(false);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testConvertDataValueToString() {
    System.out.println("convertDataValueToString");
     
    String expResult = "test";
    String result = instance.convertDataValueToString("test");
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSolrPostResponse method, of class Util.
   */
  @Test
  public void testGetSolrPostResponse200() {
    System.out.println("getSolrPostResponse");
    int statusCode = 200; 
    String expResult = "Solr index run success"; 
    String result = instance.getSolrPostResponse(statusCode);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetSolrPostResponse400() {
    System.out.println("getSolrPostResponse");
    int statusCode = 400; 
    String expResult = "Bad Request"; 
    String result = instance.getSolrPostResponse(statusCode);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetSolrPostResponse412() {
    System.out.println("getSolrPostResponse");
    int statusCode = 412; 
    String expResult = "Precondition Failed"; 
    String result = instance.getSolrPostResponse(statusCode);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetSolrPostResponse404() {
    System.out.println("getSolrPostResponse");
    int statusCode = 404; 
    String expResult = "Not Found"; 
    String result = instance.getSolrPostResponse(statusCode);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetSolrPostResponse500() {
    System.out.println("getSolrPostResponse");
    int statusCode = 500; 
    String expResult = "Internal Server Error"; 
    String result = instance.getSolrPostResponse(statusCode);
    assertEquals(expResult, result); 
  }
}

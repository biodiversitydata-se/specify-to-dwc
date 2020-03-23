package se.nrm.bas.specify.data.service.logic;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class InitialPropertiesTest {
  
  private InitialProperties instance;
  
  private String solrPath;
  private String defaultMappingFilePath;
  
  public InitialPropertiesTest() {
  }
   
  @Before
  public void setUp() {
    defaultMappingFilePath = "/usr/mapping.json";
    solrPath = "http://localhost:8983/solr"; 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new InitialProperties();
    assertNotNull(instance); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testException1() {
    instance = new InitialProperties();
    
    instance.getDefaultMappingFilePath();
  }
   
  @Test(expected = RuntimeException.class)
  public void testException2() {
    instance = new InitialProperties();
    
    instance.getSolrPath();
  }

  /**
   * Test of getSolrPath method, of class InitialProperties.
   */
 @Test
  public void testGetSolrPath() {
    System.out.println("getSolrPath");
    
    instance = new InitialProperties(solrPath, defaultMappingFilePath); 
    String result = instance.getSolrPath();
    assertEquals(solrPath, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetSolrPathException() {
    System.out.println("getSolrPath");
    
    instance = new InitialProperties(null, defaultMappingFilePath); 
    instance.getSolrPath(); 
  }

  /**
   * Test of getDefaultMappingFilePath method, of class InitialProperties.
   */
  @Test
  public void testGetDefaultMappingFilePath() {
    System.out.println("getDefaultMappingFilePath");
    instance = new InitialProperties(solrPath, defaultMappingFilePath);
 
    String result = instance.getDefaultMappingFilePath();
    assertEquals(defaultMappingFilePath, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetDefaultMappingFilePathException() {
    System.out.println("getDefaultMappingFilePath");
    instance = new InitialProperties(solrPath, null);
    instance.getDefaultMappingFilePath(); 
  }
  
}

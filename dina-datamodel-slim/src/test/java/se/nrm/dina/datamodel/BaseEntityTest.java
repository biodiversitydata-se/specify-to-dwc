package se.nrm.dina.datamodel;

import java.util.Date;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class BaseEntityTest {
  
  private BaseEntity instance;
  
  public BaseEntityTest() {
  }
  
  @Before
  public void setUp() {
    instance = new BaseEntity() {
      @Override
      public String getIdentityString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

      @Override
      public int getEntityId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
    }; 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getVersion method, of class BaseEntity.
   */
  @Test
  public void testGetVersion() {
    System.out.println("getVersion");
    
    instance.setVersion(1); 
    
    Integer expResult = 1;
    Integer result = instance.getVersion();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setVersion method, of class BaseEntity.
   */
  @Test
  public void testSetVersion() {
    System.out.println("setVersion");
    Integer version = 2; 
    instance.setVersion(version); 
    assertEquals(version, instance.getVersion()); 
  }

  /**
   * Test of getTimestampCreated method, of class BaseEntity.
   */
  @Test
  public void testGetTimestampCreated() {
    System.out.println("getTimestampCreated"); 
    
    Date date = new Date();
    
    instance.setTimestampCreated(date);
    Date result = instance.getTimestampCreated();
    assertEquals(date, result); 
  }

  /**
   * Test of setTimestampCreated method, of class BaseEntity.
   */
  @Test
  public void testSetTimestampCreated() {
    System.out.println("setTimestampCreated");
    Date timestampCreated = new Date(); 
    instance.setTimestampCreated(timestampCreated); 
    assertEquals(timestampCreated, instance.getTimestampCreated());
  } 
  
}

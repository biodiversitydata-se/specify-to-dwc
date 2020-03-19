package se.nrm.dina.datamodel;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
public class EntityBeanTest {
  
  private EntityBean testInstance;
  
  public EntityBeanTest() {
  }
   
  @Before
  public void setUp() {
    testInstance = new Testentity(18); 
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getIdentityString method, of class EntityBean.
   */
  @Test
  public void testGetIdentityString() {
    System.out.println("getIdentityString"); 
    String expResult = "18";
    String result = testInstance.getIdentityString();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getEntityId method, of class EntityBean.
   */
  @Test
  public void testGetEntityId() {
    System.out.println("getEntityId"); 
    int expResult = 18;
    int result = testInstance.getEntityId();
    assertEquals(expResult, result); ;
  } 
}

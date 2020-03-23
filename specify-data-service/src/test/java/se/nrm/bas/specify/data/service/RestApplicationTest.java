package se.nrm.bas.specify.data.service;

import org.junit.After; 
import static org.junit.Assert.assertNotNull;
import org.junit.Before; 
import org.junit.Test; 

/**
 *
 * @author idali
 */
public class RestApplicationTest {
  
  private RestApplication instance;
  
  public RestApplicationTest() {
  }
 
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  @Test
  public void testSomeMethod() { 
    instance = new RestApplication();
    assertNotNull(instance);
  }
  
}

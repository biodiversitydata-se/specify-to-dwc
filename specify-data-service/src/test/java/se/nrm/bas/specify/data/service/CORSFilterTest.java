package se.nrm.bas.specify.data.service;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class CORSFilterTest {
  
  @Mock
  private ContainerRequestContext requestContext;
  @Mock
  private ContainerResponseContext responseContext;
  
  private MultivaluedMap map;
  
  public CORSFilterTest() {
  }
   
  
  @Before
  public void setUp() { 
    map = new MultivaluedHashMap();
    when(responseContext.getHeaders()).thenReturn(map);  
  }
  
  @After
  public void tearDown() {
    map = null;
    responseContext = null;
  }

  /**
   * Test of filter method, of class CORSFilter.
   * @throws java.io.IOException
   */
  @Test
  public void testFilter() throws IOException {
    System.out.println("filter"); 
    CORSFilter instance = new CORSFilter();
    instance.filter(requestContext, responseContext); 
    assertNotNull(map);
    assertEquals(map.size(), 4);
    
    verify(responseContext, times(4)).getHeaders(); 
  }
  
}

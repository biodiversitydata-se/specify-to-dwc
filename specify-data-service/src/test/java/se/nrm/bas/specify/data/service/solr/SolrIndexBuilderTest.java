package se.nrm.bas.specify.data.service.solr;
 
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity; 
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import se.nrm.bas.specify.data.service.logic.InitialProperties;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class) 
@PrepareForTest({SolrIndexBuilder.class, HttpClientBuilder.class})
@PowerMockIgnore({"javax.management.*", 
  "org.apache.http.conn.ssl.*", 
  "com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*"})
public class SolrIndexBuilderTest {
  
  private SolrIndexBuilder instance;
  
  private CloseableHttpResponse response;
  private CloseableHttpClient client;
  
  @Mock
  private InitialProperties properties;
  
  public SolrIndexBuilderTest() {
  }
 
  @Before
  public void setUp() {
    String solrPath = "http://localhost:8983";
    when(properties.getSolrPath()).thenReturn(solrPath);  
    
    client = mock(CloseableHttpClient.class);
    PowerMockito.mockStatic(HttpClientBuilder.class); 
     
    HttpClientBuilder builder = mock(HttpClientBuilder.class);
    when(HttpClientBuilder.create()).thenReturn(builder);
    when(builder.build()).thenReturn(client);
  }
  
  @After
  public void tearDown() {
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new SolrIndexBuilder();
    assertNotNull(instance);
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructorException() {
    instance = new SolrIndexBuilder();
    instance.init();
  }

  /**
   * Test of init method, of class SolrIndexBuilder.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    
    instance = new SolrIndexBuilder(properties);
    instance.init();  
  }

  /**
   * Test of postToSolr method, of class SolrIndexBuilder.
   * @throws java.io.IOException
   */
  @Test
  public void testPostToSolr() throws IOException {
    System.out.println("postToSolr");
    
    instance = new SolrIndexBuilder(properties); 
    
    instance.init();
//    HttpEntity httpEntity = mock(HttpEntity.class);
    
//    InputStream input = IOUtils.toInputStream("some test data for my input stream", "UTF-8"); 
//    when(httpEntity.getContent()).thenReturn(input);  
    
    StatusLine statusLine = mock(StatusLine.class);
    when(statusLine.getStatusCode()).thenReturn(200);
    response = mock(CloseableHttpResponse.class);  
//    when(response.getEntity()).thenReturn(httpEntity); 
    when(response.getStatusLine()).thenReturn(statusLine);
    when(client.execute(any(HttpPost.class))).thenReturn(response);
    
    String core = "nrm_index";
    String jsonString = "";
    
    int result = instance.postToSolr(core, jsonString); 
    assertEquals(200, result);  
    verify(response, times(1)).getStatusLine(); 
    verify(client, times(1)).execute(any(HttpPost.class)); 
  }
  
  @Test
  public void testPostToSolrTestException() throws IOException {
    System.out.println("postToSolr");
    
    instance = new SolrIndexBuilder(properties); 
    
    instance.init();
    HttpEntity httpEntity = mock(HttpEntity.class);
    
    InputStream input = null; 
    when(httpEntity.getContent()).thenReturn(input);  
    
    StatusLine statusLine = mock(StatusLine.class);
    when(statusLine.getStatusCode()).thenReturn(200);
    response = mock(CloseableHttpResponse.class);   
    when(response.getStatusLine()).thenReturn(statusLine);
    
    PowerMockito.doThrow(new IOException()).when(client).execute(any(HttpPost.class));  
    
    String core = "nrm_index";
    String jsonString = ""; 
    int result = instance.postToSolr(core, jsonString);  
    System.out.println("result..." + result);
    assertEquals(500, result);
    
    verify(response, never()).getStatusLine(); 
    verify(client, times(1)).execute(any(HttpPost.class)); 
  }
  
}

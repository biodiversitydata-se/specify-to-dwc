package se.nrm.bas.specify.data.service.solr;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;  
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity; 
import org.apache.http.impl.client.HttpClientBuilder; 

/**
 *
 * @author idali
 */
@Slf4j
public class SolrIndexBuilder { 
 

  private final String utf8 = "UTF-8";
  private final String applicationJson = "application/json";
  private HttpResponse response; 
    
  private int status;
 
  public SolrIndexBuilder() {
    
  }  

  public int postToSolr(String solrUpdateUrl, String jsonString) { 
    log.info("postToSolr");
    
    HttpClient client = HttpClientBuilder.create().build();
  
    HttpPost post = new HttpPost(solrUpdateUrl);
 
    StringEntity entity = new StringEntity(jsonString, utf8);
    entity.setContentType(applicationJson);
    post.setEntity(entity); 
    try {
      response = client.execute(post);  
      status = response.getStatusLine().getStatusCode(); 
      log.info("status : {}", status);
      return status;
    } catch (IOException ex) {
      log.error(ex.getMessage());
      return 500;
    } 
  } 
}

package se.nrm.bas.specify.data.service.solr;

import java.io.IOException; 
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;  
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity; 
import org.apache.http.impl.client.HttpClientBuilder;
import se.nrm.bas.specify.data.service.logic.InitialProperties;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrIndexBuilder {

  private String solrBaseUrl;
  private String solrUrl;
  private final String solrUpdate = "/update/json?wt=json&commit=true";
  private final String utf8 = "UTF-8";
  private final String applicationJson = "application/json";
  private HttpResponse response; 
  
  private HttpClient client;
  private HttpPost post;
  private StringEntity entity; 
  
  private int status;

  @Inject
  private InitialProperties properties;
  
  public SolrIndexBuilder() {
    
  }
  
  public SolrIndexBuilder(InitialProperties properties) {
    this.properties = properties;
  }
  

  @PostConstruct
  public void init() {
    solrBaseUrl = properties.getSolrPath(); 
  }

  public int postToSolr(String core, String jsonString) { 
    log.info("postToSolr");
    
    client = HttpClientBuilder.create().build();
 
    solrUrl = solrBaseUrl + core + solrUpdate;
    post = new HttpPost(solrUrl);
 
    entity = new StringEntity(jsonString, utf8);
    entity.setContentType(applicationJson);
    post.setEntity(entity); 
    try {
      response = client.execute(post);  
      status = response.getStatusLine().getStatusCode(); 
      return status;
    } catch (IOException ex) {
      log.error(ex.getMessage());
      return 500;
    } 
  } 
}

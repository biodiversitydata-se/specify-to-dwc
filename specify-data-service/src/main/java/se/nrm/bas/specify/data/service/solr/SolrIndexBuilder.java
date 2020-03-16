package se.nrm.bas.specify.data.service.solr;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
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
  private HttpEntity httpEntity; 
  
  private HttpClient client;
  private HttpPost post;
  private StringEntity entity;

  @Inject
  private InitialProperties properties;

  @PostConstruct
  public void init() {
    solrBaseUrl = properties.getSolrPath();
    client = HttpClientBuilder.create().build();
  }

  public void postToSolr(String core, String jsonString) {
    log.info("postSolr: core = {}", core);

    solrUrl = solrBaseUrl + core + solrUpdate; 
    post = new HttpPost(solrUrl);
    
    entity = new StringEntity(jsonString, utf8);
    entity.setContentType(applicationJson);
    try {
      post.setEntity(entity); 
      response = client.execute(post); 
      httpEntity = response.getEntity(); 
      String encoding = httpEntity.getContentEncoding() == null ? utf8 : httpEntity.getContentEncoding().getName();
      try(InputStream input = httpEntity.getContent()) {
        encoding = encoding == null ? utf8 : encoding;
        String responseText = IOUtils.toString(input, encoding);
        log.info("response text: {} ---  {}", responseText, response.getStatusLine().getStatusCode());
       }  
    } catch (IOException ex) {
      log.info(ex.getMessage());
    }
  } 
}

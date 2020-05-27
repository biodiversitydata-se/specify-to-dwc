package se.nrm.bas.specify.data.service.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;   
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.JsonArray; 
import lombok.extern.slf4j.Slf4j; 
import se.nrm.bas.specify.data.service.logic.data.DataReader; 
import se.nrm.bas.specify.data.service.logic.json.JsonConverter; 
import se.nrm.bas.specify.data.service.logic.util.Util;
import se.nrm.bas.specify.data.service.solr.SolrIndexBuilder; 

/**
 *
 * @author idali
 */
@Slf4j
public class SolrIndexBuilderLogic implements Serializable {
  
  @Inject
  private DataReader reader;
  @Inject
  private JsonConverter converter;
  @Inject
  private SolrIndexBuilder indexBuilder; 
  @Inject
  private InitialProperties properties;
  
  private final int batch = 1000; 
  private final int successCode = 200;
  private final String solrUpdate = "/update/json?wt=json&commit=true";
    
  private int end;
  
  private int statusCode; 
  private int solrPostStatusCode; 
  
  private String solrBaseUrl; 

   
  public SolrIndexBuilderLogic() {
    
  }
  
  public SolrIndexBuilderLogic(DataReader reader, JsonConverter converter, 
          SolrIndexBuilder indexBuilder, InitialProperties properties) {
    this.reader = reader;
    this.converter = converter;
    this.indexBuilder = indexBuilder;
    this.properties = properties;
  }
  
  @PostConstruct
  public void init() {
    solrBaseUrl = properties.getSolrPath(); 
  }
   
  public int run(String institution, int collectionCode, String strFromDate, String strToDate) {
    
    String core = Util.getInstance().getIndexCore(institution);
    StringBuilder solrUrlSb = new StringBuilder();
    
    solrUrlSb.append(solrBaseUrl);
    solrUrlSb.append(core);
    solrUrlSb.append(solrUpdate);
             
    solrPostStatusCode = successCode;
    Date fromDate = Util.getInstance().stringToDate(strFromDate);
    Date toDate = Util.getInstance().stringToDate(strToDate);
    
    boolean isNrm = Util.getInstance().isNrm(institution);  
     
    List<Integer> ids = reader.getCollectionIds(collectionCode, fromDate, toDate, isNrm);   
    int total = ids.size();  
    log.info("total: {} ", total);
    for (int i = 0; i < total; i += batch) {  
      end = i + batch <= total ? i + batch : total; 
      
      log.info("start: {} --- end: {}", i, end); 
      JsonArray json = converter.convert(reader.fetchData(collectionCode, fromDate, toDate, ids.subList(i, end), isNrm),
              institution, collectionCode);   
      statusCode = indexBuilder.postToSolr(solrUrlSb.toString().trim(), json.toString());
   
      if(statusCode != successCode) {
        solrPostStatusCode = statusCode;
      }
    } 
    return solrPostStatusCode;
  }
}

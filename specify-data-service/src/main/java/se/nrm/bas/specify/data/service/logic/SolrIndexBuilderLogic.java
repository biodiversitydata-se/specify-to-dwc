package se.nrm.bas.specify.data.service.logic;

import java.io.Serializable;
import java.util.List; 
import javax.inject.Inject;
import javax.json.JsonArray; 
import lombok.extern.slf4j.Slf4j;
import se.nrm.bas.specify.data.service.logic.data.DataReader; 
import se.nrm.bas.specify.data.service.logic.json.JsonConverter; 
import se.nrm.bas.specify.data.service.solr.SolrIndexBuilder;
import se.nrm.dina.datamodel.EntityBean; 

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
  
  
  
  public SolrIndexBuilderLogic() {
    
  }
   
  public void run(String institution, int collectionCode, String fromDate, String toDate) {
    List<EntityBean> list = reader.findAll(institution, collectionCode, fromDate, toDate);
  
    JsonArray json = converter.convert(list, institution, collectionCode); 
 
    indexBuilder.postToSolr("nrm_index", json.toString()); 
  }
}

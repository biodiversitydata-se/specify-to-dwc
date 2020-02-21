package se.nrm.bas.specify.data.service.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;   
import javax.inject.Inject;
import javax.json.JsonArray; 
import lombok.extern.slf4j.Slf4j; 
import se.nrm.bas.specify.data.service.logic.data.DataReader; 
import se.nrm.bas.specify.data.service.logic.json.JsonConverter; 
import se.nrm.bas.specify.data.service.logic.util.Util;
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
  
  private final int batch = 2000; 
  private int end;
  
  public SolrIndexBuilderLogic() {
    
  }
   
  public void run(String institution, int collectionCode, String strFromDate, String strToDate) {
    
    Date fromDate = Util.getInstance().stringToDate(strFromDate);
    Date toDate = Util.getInstance().stringToDate(strToDate);
    
    boolean isNrm = Util.getInstance().isNrm(institution);  
    List<Integer> ids = reader.getCollectionIds(collectionCode, fromDate, toDate, isNrm); 
    
    int total = ids.size();
    for (int i = 0; i < total; i += batch) {  
      end = i + batch <= total ? i + batch : total;
      List<EntityBean> list = reader.fetchData(institution, collectionCode, fromDate, toDate, ids.subList(i, end), isNrm); 
      JsonArray json = converter.convert(list, institution, collectionCode);  
      indexBuilder.postToSolr(Util.getInstance().getIndexCore(institution), json.toString());  
    }     
  }
}

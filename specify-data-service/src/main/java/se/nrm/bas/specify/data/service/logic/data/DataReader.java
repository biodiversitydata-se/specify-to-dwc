package se.nrm.bas.specify.data.service.logic.data;
 
import java.io.Serializable; 
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB; 
import lombok.extern.slf4j.Slf4j; 
import se.nrm.bas.specify.data.service.logic.util.Util;
import se.nrm.dina.data.jpa.DinaDao;
import se.nrm.dina.datamodel.EntityBean; 

/**
 *
 * @author idali
 */
@Slf4j
public class DataReader implements Serializable {
  
  @EJB
  private DinaDao dao; 
  
  public DataReader() {
    
  }
  
  public void getCollectionObjectByCollectionCode() {
    
  }
  
  public List<EntityBean> findAll(String institution, int collectionId, String fromDate, String toDate) { 
    
    return ((Stream<EntityBean>)dao 
            .findByCollectonId(collectionId, institution, 
                    Util.getInstance().stringToDate(fromDate),
                    Util.getInstance().stringToDate(toDate)))
            .collect(Collectors.toList()); 
  }
  
}

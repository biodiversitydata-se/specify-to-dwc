package se.nrm.bas.specify.data.service.logic.data;
 
import java.io.Serializable; 
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB; 
import lombok.extern.slf4j.Slf4j;  
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
  
  public DataReader(DinaDao dao) {
    this.dao = dao;
  }
    
  public List<Integer> getCollectionIds(int collectionId, Date fromDate, Date toDate, boolean isNrm) {
    return dao.findAllIds(collectionId, fromDate, toDate, isNrm);
  }

  public List<EntityBean> fetchData(int collectionId, Date fromDate, Date toDate, List<Integer> ids, boolean isNrm) { 
    return ((Stream<EntityBean>) dao
            .findByCollectonId(collectionId, isNrm, fromDate, toDate, ids))
            .collect(Collectors.toList());
  }
}

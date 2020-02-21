package se.nrm.dina.data.jpa;
 
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 * @param <T>{@link BaseEntity}
 */
public interface DinaDao<T extends EntityBean> {
  
  public Stream<T> findByCollectonId(int collectionId, boolean isNrm, Date fromDate, Date toDate, List<Integer> ids);
  public List<Integer> findAllIds(int collectionCode, Date fromDate, Date toDate, boolean isNrm);  
  public Stream<T> findAll(Class<T> clazz, boolean isNrm);  
  public int getCollectionCount(int collectionId, boolean isNrm);  
}

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
  
  public Stream<T> findByCollectonId(int collectionId, String institution, Date fromDate, Date toDate);

  public Stream<T> findByCollectionCode(int collectionCode, List<Integer> ids, boolean isNrm); 
  
  public Stream<T> findGeographyParents(int geographyId, boolean isNrm); 
  
  public Stream<T> findTaxonParents(int taxonId, boolean isNrm);

  public Stream<T> findAll(Class<T> clazz, boolean isNrm);
  
  public List<Integer> findAllIds(int collectionCode, boolean isNrm);

  public int getCollectionCount(int collectionId, boolean isNrm); 
  
  public List<T> findSMTPCollectionEventData();
  
  public List<T> findOverdueLoans();  
}

package se.nrm.dina.data.jpa.impl;

import java.io.Serializable; 
import java.util.Date;
import java.util.List; 
import java.util.stream.Stream; 
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;  
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query; 
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.data.jpa.DinaDao;  
import se.nrm.dina.datamodel.EntityBean; 

/**
 * CRUD operations to database
 *
 * @author idali
 * @param <T>
 */
@Slf4j
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
//@Asynchronous 
@TransactionAttribute(NOT_SUPPORTED)
public class DinaDaoImpl<T extends EntityBean> implements DinaDao<T>, Serializable {
   
  private final String nrm = "nrm";
  private final String collectionMemberId = "collectionMemberID";
  private final String fromDateKey = "fromDate";
  private final String toDateKey = "toDate";
  private final String idsKey = "ids";
 
  @PersistenceContext(unitName = "jpaNrmPU")
  private EntityManager nrmEntityManager;

  @PersistenceContext(unitName = "jpaGnmPU")
  private EntityManager gnmEntityManager;
   
  private Query query;

  public DinaDaoImpl() {

  }
  
  public DinaDaoImpl(EntityManager nrmEntityManager, EntityManager gnmEntityManager) {
    this.nrmEntityManager = nrmEntityManager;
    this.gnmEntityManager = gnmEntityManager;
  }

  private EntityManager getEntityManager(boolean isNrm) {
    return isNrm ? nrmEntityManager : gnmEntityManager;
  }
 
  @Override
  public Stream<T> findByCollectonId(int collectionId, boolean isNrm, Date fromDate, Date toDate, List<Integer> ids) {
    query = getEntityManager(isNrm)
            .createQuery(QueryBuilder.getInstance()
                    .buildQuery(fromDate, toDate, ids != null))
            .setParameter(collectionMemberId, collectionId);

    if (fromDate != null) {
      query.setParameter(fromDateKey, fromDate);
    }

    if (toDate != null) {
      query.setParameter(toDateKey, toDate);
    }

    if (ids != null) {
      query.setParameter(idsKey, ids);
    }
    return query.getResultStream();
  }
  
  
  @Override
  public List<Integer> findAllIds(int collectionMemberID, Date fromDate, Date toDate, boolean isNrm) {
    
    query = getEntityManager(isNrm)
              .createQuery(QueryBuilder.getInstance().buildGetIdsQuery(fromDate, toDate))
              .setParameter(collectionMemberId, collectionMemberID);
    
    if (fromDate != null) {
      query.setParameter(fromDateKey, fromDate);
    } 
    if (toDate != null) {
      query.setParameter(toDateKey, toDate);
    } 
    return query.getResultList(); 
  } 
  
  @Override
  public Stream<T> findAll(Class<T> clazz, boolean isNrm) {
    log.info("findAll : {} -- {}", clazz, isNrm);

    query = getEntityManager(isNrm).createNamedQuery(clazz.getSimpleName() + ".findAll");
    return query.getResultStream();
  }
  
  @Override
  public int getCollectionCount(int collectionMemberID, boolean isNrm) {
    log.info("getCollectionCount: {} ", collectionMemberID);

    Number number;
    query = getEntityManager(isNrm).createNamedQuery("Collectionobject.findCount")
            .setParameter(collectionMemberId, collectionMemberID);

    try {
      number = (Number) query.getSingleResult();
    } catch (Exception e) {
      log.info(e.getMessage());
      return 0;
    }
    return number.intValue();
  }
   
  public void closeEntityManager() {
    log.info("disposesing entityManage....");
    if (gnmEntityManager.isOpen()) {
      log.info("closing...");
      gnmEntityManager.close();
    }
    if(nrmEntityManager.isOpen()) {
      nrmEntityManager.close();
    }
  }
}

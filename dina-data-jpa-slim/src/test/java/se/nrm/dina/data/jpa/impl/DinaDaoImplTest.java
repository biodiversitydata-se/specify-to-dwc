package se.nrm.dina.data.jpa.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.data.jpa.DinaDao;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class DinaDaoImplTest {
  
  @Mock
  private static EntityManager gnmEntityManager;
  @Mock
  private static EntityManager nrmEntityManager;
  @Mock
  private static Query query;
  @Mock
  private static Stream<EntityBean> stream;
  
  private static DinaDao dao;
  private static int collectionId;
  private static Date fromDate;
  private static Date toDate;
  private static List<Integer> ids;
  private static Number count;
  private static Map<String, String> map;
   
  public DinaDaoImplTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    dao = new DinaDaoImpl();
    collectionId = 123;
    Calendar calendar = new GregorianCalendar(2000, 00, 01);
    fromDate = calendar.getTime();
    calendar = new GregorianCalendar(2001, 00, 01);
    toDate = calendar.getTime();
    
    ids = new ArrayList();
    ids.add(333);
    ids.add(334);
    ids.add(335); 
    
    count = 500;
    
    map = new HashMap();
  }
  
  @AfterClass
  public static void tearDownClass() {
    dao = null;
  }
  
  @Before
  public void setUp() {   
    when(query.setParameter("collectionMemberID", collectionId)).thenReturn(query);
    when(query.setParameter("fromDate", fromDate)).thenReturn(query);
    when(query.setParameter("toDate", toDate)).thenReturn(query);
    when(query.setParameter("ids", ids)).thenReturn(query);
    when(query.getResultStream()).thenReturn(stream);  
    when(query.getSingleResult()).thenReturn(count);
             
    when(nrmEntityManager.createQuery(any(String.class))).thenReturn(query);   
    when(gnmEntityManager.createQuery(any(String.class))).thenReturn(query);  
     
    when(nrmEntityManager.createNamedQuery(any(String.class))).thenReturn(query);   
    when(gnmEntityManager.createNamedQuery(any(String.class))).thenReturn(query); 
  }
  
  @After
  public void tearDown() {
    dao = null;
  }

  @Test
  public void testDinaDaoImplConstractor() throws Exception {
    System.out.println("testDinaDaoImplConstractor"); 
    dao = new DinaDaoImpl();
    assertNotNull(dao);
  }
  
  @Test
  public void testDinaDaoImplConstractorWithArgs() throws Exception {
    System.out.println("testDinaDaoImplConstractor"); 
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager);
    assertNotNull(dao);
  }


  /**
   * Test of findByCollectonId method, of class DinaDaoImpl.
   */
  @Test
  public void testFindByCollectonIdWithNRM()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = true;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, fromDate, toDate, ids, null);
    
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate); 
    verify(query, times(1)).setParameter("ids", ids); 
    
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result); 
  }
  
  @Test
  public void testFindByCollectonIdWithNRMWithNoFromDate()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = true;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, null, toDate, ids, null);
    
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, never()).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate); 
    verify(query, times(1)).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }
  
  @Test
  public void testFindByCollectonIdWithNRMWithNotoDate()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = true;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, fromDate, null, ids, null);
    
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, never()).setParameter("toDate", toDate); 
    verify(query, times(1)).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }
  
  @Test
  public void testFindByCollectonIdWithNRMWithNoIds()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = true;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, fromDate, toDate, null, null);
    
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate); 
    verify(query, never()).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }
  
  @Test
  public void testFindByCollectonIdWithGnm()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = false;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, fromDate, toDate, ids, null);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate); 
    verify(query, times(1)).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result); 
  }
  
  @Test
  public void testFindByCollectonIdWithGnmWithNoFromDate()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = false;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, null, toDate, ids, null);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, never()).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate); 
    verify(query, times(1)).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }
  
  @Test
  public void testFindByCollectonIdWithGnmWithNotoDate()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = false;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, fromDate, null, ids, null);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, never()).setParameter("toDate", toDate); 
    verify(query, times(1)).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }
  
  @Test
  public void testFindByCollectonIdWithGnmWithNoIds()  {
    System.out.println("findByCollectonId"); 
    
    boolean isNrm = false;    
    
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    Stream result = dao.findByCollectonId(collectionId, isNrm, fromDate, toDate, null, null);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate); 
    verify(query, never()).setParameter("ids", ids); 
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }

  /**
   * Test of findAllIds method, of class DinaDaoImpl.
   */
  @Test
  public void testFindAllIdsNrm() {
    System.out.println("findAllIds"); 
    boolean isNrm = true;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, fromDate, toDate, isNrm);
    
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsNrmWithNoFromDate() {
    System.out.println("findAllIds"); 
    boolean isNrm = true;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, null, toDate, isNrm);
    
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, never()).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsNrmWithNoTodate() {
    System.out.println("findAllIds"); 
    boolean isNrm = true;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, fromDate, null, isNrm);
    
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, never()).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsNrmWithNoFromDataAndToDate() {
    System.out.println("findAllIds"); 
    boolean isNrm = true;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, null, null, isNrm);
    
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, never()).setParameter("fromDate", fromDate); 
    verify(query, never()).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsGnm() {
    System.out.println("findAllIds"); 
    boolean isNrm = false;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, fromDate, toDate, isNrm);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsGnmWithNoFromDate() {
    System.out.println("findAllIds"); 
    boolean isNrm = false;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, null, toDate, isNrm);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, never()).setParameter("fromDate", fromDate); 
    verify(query, times(1)).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsGnmWithNoToDate() {
    System.out.println("findAllIds"); 
    boolean isNrm = false;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, fromDate, null, isNrm);
    
    verify(nrmEntityManager, never()).createQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, times(1)).setParameter("fromDate", fromDate); 
    verify(query, never()).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }
  
  @Test
  public void testFindAllIdsGnmWithNoFromDataAndToDate() {
    System.out.println("findAllIds"); 
    boolean isNrm = true;
     
    when(query.getResultList()).thenReturn(ids);
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    List<Integer> result = dao.findAllIds(collectionId, null, null, isNrm);
    
    verify(gnmEntityManager, never()).createQuery(any(String.class)); 
    verify(nrmEntityManager, times(1)).createQuery(any(String.class)); 
    verify(query, times(1)).setParameter("collectionMemberID", collectionId); 
    verify(query, never()).setParameter("fromDate", fromDate); 
    verify(query, never()).setParameter("toDate", toDate);  
    verify(query, times(1)).getResultList(); 
    assertEquals(3, result.size()); 
  }

  /**
   * Test of findAll method, of class DinaDaoImpl.
   */
  @Test
  public void testFindAllNrm() {
    System.out.println("findAll");
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager);   
    
    Class clazz = EntityBean.class;
    Stream result = dao.findAll(clazz, true);
     
    verify(nrmEntityManager, times(1)).createNamedQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createNamedQuery(any(String.class)); 
    
    verify(query, times(1)).getResultStream();  
    assertNotNull(result);  
  }
  
  @Test
  public void testFindAllGnm() {
    System.out.println("findAll");
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager);   
    
    Class clazz = EntityBean.class;
    Stream result = dao.findAll(clazz, false);
     
    verify(gnmEntityManager, times(1)).createNamedQuery(any(String.class)); 
    verify(nrmEntityManager, never()).createNamedQuery(any(String.class));  
    verify(query, times(1)).getResultStream();  
    verify(query, times(1)).getResultStream(); 
    assertNotNull(result);  
  }
 
  /**
   * Test of getCollectionCount method, of class DinaDaoImpl.
   */
  @Test
  public void testGetCollectionCountNrm() {
    System.out.println("getCollectionCount"); 
     
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager);   
    int result = dao.getCollectionCount(collectionId, true);
    
    verify(nrmEntityManager, times(1)).createNamedQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createNamedQuery(any(String.class));  
    verify(query, times(1)).getSingleResult();  
    assertNotNull(result); 
    assertEquals(500, result);
  }
  
  @Test
  public void testGetCollectionCountNrmException() {
    System.out.println("getCollectionCount"); 
     
    when(query.getSingleResult()).thenThrow(Exception.class);;
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager);   
    int result = dao.getCollectionCount(collectionId, true);
      
    verify(nrmEntityManager, times(1)).createNamedQuery(any(String.class)); 
    verify(gnmEntityManager, never()).createNamedQuery(any(String.class));  
    verify(query, times(1)).getSingleResult();  
    assertNotNull(result); 
    assertEquals(0, result);
  }
  
  @Test
  public void testGetCollectionCountGnm() {
    System.out.println("getCollectionCount"); 
     
    dao = new DinaDaoImpl(nrmEntityManager, gnmEntityManager);   
    int result = dao.getCollectionCount(collectionId, false);
    
    verify(nrmEntityManager, never()).createNamedQuery(any(String.class)); 
    verify(gnmEntityManager, times(1)).createNamedQuery(any(String.class));  
    verify(query, times(1)).getSingleResult();  
    assertNotNull(result); 
    assertEquals(500, result);
  }
  

  /**
   * Test of closeEntityManager method, of class DinaDaoImpl.
   */
  @Test
  public void testCloseEntityManagerOpen() {
    System.out.println("closeEntityManager");  
    
    when(nrmEntityManager.isOpen()).thenReturn(true);   
    when(gnmEntityManager.isOpen()).thenReturn(true); 
    DinaDaoImpl daoImpl = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    daoImpl.closeEntityManager();
    verify(nrmEntityManager, times(1)).close(); 
    verify(gnmEntityManager, times(1)).close(); 
  }
  
  @Test
  public void testCloseEntityManagerClose() {
    System.out.println("closeEntityManager");  
    
    when(nrmEntityManager.isOpen()).thenReturn(false); 
    when(gnmEntityManager.isOpen()).thenReturn(false); 
    DinaDaoImpl daoImpl = new DinaDaoImpl(nrmEntityManager, gnmEntityManager); 
    daoImpl.closeEntityManager();
    verify(nrmEntityManager, never()).close(); 
    verify(gnmEntityManager, never()).close(); 
  } 
}

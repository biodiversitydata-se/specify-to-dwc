/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.jpa.impl;

import java.math.BigDecimal;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OptimisticLockException; 
import javax.persistence.Query;
import javax.persistence.TypedQuery;       
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame; 
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass; 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;  
import static org.mockito.Mockito.doThrow; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;   
import se.nrm.dina.data.exceptions.DinaConstraintViolationException;
import se.nrm.dina.data.exceptions.DinaDatabaseException;   
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.jpa.DinaDao;
import se.nrm.dina.data.util.HelpClass; 
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class DinaDaoImplNGTest {
    
    @Mock
    static EntityManager entityManager;
    
    @Mock
    static QueryBuilder queryBuilder;
    
    @Mock
    static Query query;
    
    @Mock
    TypedQuery<String> tq;
    
    @Mock
    TypedQuery<Object[]> tqObj;
    
    @Mock
    TypedQuery<Integer> intTQ;
    
    private String strQuery;
    private static DinaDao dao;
    
    private Testentity theEntity; 
    
    public DinaDaoImplNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
//        dao = new DinaDaoImpl(entityManager, query);
      dao = new DinaDaoImpl();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        dao = null;
    }
    
     
    @Test
    public void testDinaDaoImplConstractor() throws Exception {
        System.out.println("testDinaDaoImplConstractor");
        dao = new DinaDaoImpl();
        assertNotNull(dao);
    }
     
    @Test
    public void testDinaDaoImplConstractorWithEntityManager() throws Exception {
        System.out.println("testDinaDaoImplConstractorWithEntityManager");
//        dao = new DinaDaoImpl(entityManager);
        assertNotNull(dao);
    }
    
    @Test
    public void testDinaDaoImplConstractorWithEntityManagerAndQuery() throws Exception {
        System.out.println("testDinaDaoImplConstractorWithEntityManagerAndQuery");
//        dao = new DinaDaoImpl(entityManager, query);
        assertNotNull(dao);
    }
 
//    @Test
//    public void testFindAll_Class() throws Exception {
//        System.out.println("findAll");
//   
//        Class clazz = Testentity.class; 
//        List<Testentity> testEntities = new ArrayList<>();
//        theEntity = new Testentity(20);
//        testEntities.add(theEntity);
//        
//        when(entityManager.createNamedQuery(clazz.getSimpleName() + ".findAll")).thenReturn(query); 
//        when(query.getResultList()).thenReturn(testEntities);
//        int expResult = 1;
//        
////        dao = new DinaDaoImpl(entityManager, query);
//        List result = dao.findAll(clazz);
//        verify(entityManager).createNamedQuery(clazz.getSimpleName() + ".findAll");
//        verify(query).getResultList();
//        assertEquals(expResult, result.size());  
//        assertSame(result, testEntities);
//    }
//    
////    @Test
//    public void testFindAllWithCondition() throws Exception {
//        System.out.println("testFindAllWithCondition");
//        
//        List<Testentity> testEntities = new ArrayList<>();
//        theEntity = new Testentity(20);
//        testEntities.add(theEntity);
//        
//        theEntity = new Testentity(21);
//        testEntities.add(theEntity);
//        
//        theEntity = new Testentity(22);
//        testEntities.add(theEntity);
//         
//                
//        Map<String, String> conditions = new HashMap<>();
//        conditions.put("version", "1");
//        boolean isFuzzSearh = false;
//        
//        Class clazz = Testentity.class;
//        int limit = 2;
//        int offset = 2;
//        
//        when(entityManager.createQuery(strQuery)).thenReturn(query); 
//        when(queryBuilder.createQuery(query, clazz, conditions, isFuzzSearh)).thenReturn(query);
//        when(query.getResultList()).thenReturn(testEntities);
//        int expResult = 3;
//        
////        dao = new DinaDaoImpl(entityManager, query);
//        List<Testentity> result = dao.findAll(clazz, strQuery, limit, conditions, isFuzzSearh, offset);  
//        
//        verify(entityManager).createQuery(strQuery);
//        verify(query).getResultList();
//        verify(query).setFirstResult(offset);
//        assertEquals(expResult, result.size());   
//    }
//    
//    
//    /**
//     * Test of findAll method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindAll_6args() throws Exception {
//        System.out.println("findAll");
//        
//        Class clazz = Testentity.class;
//  
//        int limit = 20;
//        Map<String, String> conditions = null;
//        boolean isFuzzSearh = false;
//        int offset = 0;
//        
//        List<Testentity> testEntities = new ArrayList<>();
//        theEntity = new Testentity(20);
//        testEntities.add(theEntity);
//        
//        when(entityManager.createQuery(strQuery)).thenReturn(query); 
//        when(query.getResultList()).thenReturn(testEntities);
//        int expResult = 1;
//        
////        dao = new DinaDaoImpl(entityManager, query);
//        List<Testentity> result = dao.findAll(clazz, strQuery, limit, conditions, isFuzzSearh, offset);  
//        
//        verify(entityManager).createQuery(strQuery);
//        verify(query).getResultList();
//        assertEquals(expResult, result.size()); 
//    }
//    
//    
//    
//    
//
//    /**
//     * Test of findAll method, of class DinaDaoImpl.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test(expected = DinaException.class)
//    public void testFindAllWithConditionsException() throws Exception {
//        System.out.println("findAll");
//
//        Class clazz = Testentity.class;
//        int limit = 20;
//        Map<String, String> conditions = new HashMap();
//        conditions.put("key", "value");
// 
//        when(entityManager.createQuery(strQuery)).thenReturn(query);
//        when(query.setMaxResults(limit)).thenReturn(query);
//        when(query.getResultList()).thenThrow(new DinaException());
// 
//        List<Testentity> result = dao.findAll(clazz, strQuery, limit, conditions, true, 0);   
//        
//        verify(entityManager).createQuery(strQuery);
//        verify(query, times(0)).getResultList();
//        verify(query, times(0)).setMaxResults(HelpClass.getInstance().maxLimit(limit));
//        verify(query, times(0)).setFirstResult(0);
//        assertNull(result);
//    }
//
//
//
//    /**
//     * Test of findById method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindByIdOptimistic() throws Exception {
//        System.out.println("findById");
//        
//        theEntity = new Testentity(20);
//        when(entityManager.find(Testentity.class, 20, LockModeType.OPTIMISTIC)).thenReturn(theEntity); 
//        
////        dao = new DinaDaoImpl(entityManager);
//        EntityBean result = dao.findById(20, Testentity.class, true);
//         
//        verify(entityManager).find(Testentity.class, 20, LockModeType.OPTIMISTIC);
//        verify(entityManager).flush();
//        assertSame(theEntity, result);   
//    }
//    
//    /**
//     * Test of findById method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindByIdPessimistic() throws Exception {
//        System.out.println("findById");
//        
//        theEntity = new Testentity(20);
//        when(entityManager.find(Testentity.class, 20, LockModeType.PESSIMISTIC_READ)).thenReturn(theEntity); 
//        
////        dao = new DinaDaoImpl(entityManager);
//        EntityBean result = dao.findById(20, Testentity.class, false);
//         
//        verify(entityManager).find(Testentity.class, 20, LockModeType.PESSIMISTIC_READ);
//        verify(entityManager).flush();
//        assertSame(theEntity, result);  
//    }
//    
//        
////    @Test(expected = DinaDatabaseException.class)
//    public void testFindByIdFailure() throws Exception {
//        System.out.println("findById");
//        
//        when(entityManager.find(Testentity.class, 20, LockModeType.OPTIMISTIC)).thenThrow(new OptimisticLockException());
//        
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.findById(20, Testentity.class, true); 
//         
//        verify(entityManager).find(Testentity.class, 20, LockModeType.OPTIMISTIC);
//        verify(entityManager).refresh(null);
//        assertNull(result);
//    }
//     
////    @Test(expected = DinaDatabaseException.class)
//    public void testFindByIdException() throws Exception {
//        System.out.println("findById");
//        
//        when(entityManager.find(Testentity.class, 20, LockModeType.OPTIMISTIC)).thenThrow(new DinaException());
//        
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.findById(20, Testentity.class, true); 
//         
//        verify(entityManager).find(Testentity.class, 20, LockModeType.OPTIMISTIC);
//        verify(entityManager, times(0)).flush();
//        assertNull(result);
//    }
//
//    /**
//     * Test of findByStringId method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindByStringId() throws Exception {
//        System.out.println("testFindByStringId");
// 
//        theEntity = new Testentity();
//        when(entityManager.find(Testentity.class, "20", LockModeType.NONE)).thenReturn(theEntity);
// 
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.findByStringId("20", Testentity.class);
//        verify(entityManager).find(Testentity.class, "20", LockModeType.NONE);
//        verify(entityManager).flush();
//        assertSame(theEntity, result);
//    }
//    
////    @Test(expected = DinaDatabaseException.class)
//    public void testFindByStringIdFailure() throws Exception {
//        System.out.println("testFindByStringId");
// 
//        theEntity = new Testentity();
//        when(entityManager.find(Testentity.class, "20", LockModeType.NONE)).thenThrow(new OptimisticLockException());
// 
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.findByStringId("20", Testentity.class);
//        verify(entityManager).refresh(null);
//        assertNull(result);
//    }
//    
////    @Test(expected = DinaDatabaseException.class)
//    public void testFindByStringIdException() throws Exception {
//        System.out.println("testFindByStringId");
// 
//        theEntity = new Testentity();
//        when(entityManager.find(Testentity.class, "20", LockModeType.NONE)).thenThrow(new DinaException());
// 
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.findByStringId("20", Testentity.class);
//        verify(entityManager, times(0)).flush();
//        assertNull(result);
//    }
//
//    /**
//     * Test of findByReference method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testFindByReference() throws Exception {
//        System.out.println("findByReference");
//        
//        theEntity = new Testentity();
//        
//        when(entityManager.getReference(Testentity.class, 20)).thenReturn(theEntity);
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.findByReference(20, Testentity.class);
//        verify(entityManager).getReference(Testentity.class, 20);
//        assertEquals(theEntity, result);
//    }
//
//    /**
//     * Test of create method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test 
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        
//        EntityBean bean = new Testentity(50);
//        
//        int expResult = 50;
//        
////        dao = new DinaDaoImpl(entityManager); 
//        Testentity result = (Testentity) dao.create(bean);
//        verify(entityManager).persist(bean);
//        verify(entityManager).flush();        
//        assertEquals(expResult, (int) result.getEntityId());
//    }
//    
////    @Test 
//    public void testCreateFailure() throws Exception {
//        System.out.println("create");
//         
//        EntityBean bean = new Testentity(); 
//        doThrow(javax.persistence.PersistenceException.class).when(entityManager).persist(bean); 
//  
////        dao = new DinaDaoImpl(entityManager); 
//        dao.create(bean);
//        verify(entityManager).persist(bean);   
//        verify(entityManager, times(0)).flush();
//    }
//
////    @Test(expected = DinaConstraintViolationException.class)
//    public void testCreateConstrainViolation() throws Exception {
//        System.out.println("create");
//         
//        EntityBean bean = new Testentity();
//        doThrow(javax.validation.ConstraintViolationException.class).when(entityManager).persist(bean); 
//  
////        dao = new DinaDaoImpl(entityManager);  
//        dao.create(bean);
//        verify(entityManager).persist(bean);   
//        verify(entityManager, times(0)).flush();
//    }
//    
////    @Test(expected = DinaException.class)
//    public void testCreateException() throws Exception {
//        System.out.println("create");
//         
//        EntityBean bean = new Testentity();
//        doThrow(DinaException.class).when(entityManager).persist(bean); 
//  
////        dao = new DinaDaoImpl(entityManager);  
//        dao.create(bean);
//        verify(entityManager).persist(bean);   
//        verify(entityManager, times(0)).flush();
//    }
//
//    /**
//     * Test of merge method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testMerge() throws Exception {
//        System.out.println("merge");
//        
//        theEntity = new Testentity(20);
//        theEntity.setBgDecimal(BigDecimal.ONE);
//        BigDecimal expectedResult = BigDecimal.ONE;
//        
//        when(entityManager.merge(theEntity)).thenReturn(theEntity);
//        
////        dao = new DinaDaoImpl(entityManager); 
//        Testentity result = (Testentity) dao.merge(theEntity);
//        
//        verify(entityManager).merge(theEntity);
//        verify(entityManager).flush();
//        assertEquals(expectedResult, result.getBgDecimal());
//    }
//    
////    @Test
//    public void testMergeOptimisticLock() throws Exception {
//        System.out.println("merge");
//        
//        theEntity = new Testentity(20);
//        theEntity.setBgDecimal(BigDecimal.ONE); 
//        
//        when(entityManager.merge(theEntity)).thenThrow(OptimisticLockException.class);       
//        
////        dao = new DinaDaoImpl(entityManager); 
//        dao.merge(theEntity);
//        
//        verify(entityManager).merge(theEntity);
//        verify(entityManager, times(0)).flush(); 
//    }
//    
////    @Test(expected = DinaException.class)
//    public void testMergeConstraintViolationException() throws Exception {
//        System.out.println("merge");
//        
//        theEntity = new Testentity(20);
//        theEntity.setBgDecimal(BigDecimal.ONE); 
//        
//        when(entityManager.merge(theEntity)).thenThrow(javax.validation.ConstraintViolationException.class);       
//        
////        dao = new DinaDaoImpl(entityManager); 
//        dao.merge(theEntity);
//        
//        verify(entityManager).merge(theEntity);
//        verify(entityManager, times(0)).flush(); 
//    }
//    
////    @Test
//    public void testMergeException() throws Exception {
//        System.out.println("merge");
//        
//        theEntity = new Testentity(20);
//        theEntity.setBgDecimal(BigDecimal.ONE); 
//        
//        when(entityManager.merge(theEntity)).thenThrow(DinaException.class);       
//        
////        dao = new DinaDaoImpl(entityManager); 
//        dao.merge(theEntity);
//        
//        verify(entityManager).merge(theEntity);
//        verify(entityManager, times(0)).flush(); 
//    }
//
//    /**
//     * Test of updateByJPQL method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testUpdateByJPQL() throws Exception {
//        System.out.println("updateByJPQL");
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("UPDATE Accession a SET a.assectionNumber = 'acc1235'");
//        sb.append(" WHERE a.accessionId = 20");
//        
//        when(entityManager.createQuery(sb.toString())).thenReturn(query);
//        when(query.executeUpdate()).thenReturn(1);
//        
////        dao = new DinaDaoImpl(entityManager); 
//        boolean result = dao.updateByJPQL(sb.toString());
//        verify(entityManager).createQuery(sb.toString());
//        verify(query).executeUpdate();
//        assertTrue(result);
//    }
//    
////    @Test
//    public void testUpdateByJPQLFailure() throws Exception {
//        System.out.println("updateByJPQL");
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("UPDATE Accession a SET a.assectionNumber = 'acc1235'");
//        sb.append(" WHERE a.accessionId = 20");
//        
//        when(entityManager.createQuery(sb.toString())).thenReturn(query);
//        when(query.executeUpdate()).thenReturn(0);
//        
////        dao = new DinaDaoImpl(entityManager); 
//        boolean result = dao.updateByJPQL(sb.toString());
//        verify(entityManager).createQuery(sb.toString());
//        verify(query).executeUpdate();
//        assertFalse(result);
//    }

//    /**
//     * Test of getEntityByJPQL method, of class DinaDaoImpl.
//     * @throws java.lang.Exception
//     */
////    @Test
//    public void testGetEntityByJPQL() throws Exception {
//        System.out.println("getEntityByJPQL");
//        
//        String jpql = "SELECT t FROM Testentity WHERE t.testEntity = 2";
//        
//        theEntity = new Testentity(2);
//        when(entityManager.createQuery(jpql)).thenReturn(query);
//        when(query.getSingleResult()).thenReturn(theEntity);
//        
//        Testentity expResult = theEntity;
//        
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.getEntityByJPQL(jpql);
//        verify(entityManager).createQuery(jpql);
//        verify(query).getSingleResult();
//        assertEquals(expResult, result);
//        assertEquals(expResult.getEntityId(), result.getEntityId());
//    }
//    
////    @Test
//    public void testGetEntityByJPQLNoResult() throws Exception {
//        System.out.println("getEntityByJPQL");
//        
//        String jpql = "SELECT t FROM Testentity WHERE t.testEntity = 2";
//        
//        theEntity = new Testentity(2);
//        when(entityManager.createQuery(jpql)).thenReturn(query);
//        when(query.getSingleResult()).thenThrow(NoResultException.class);
//        
//        Testentity expResult = null;
//        
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.getEntityByJPQL(jpql);
//        verify(entityManager).createQuery(jpql);
//        verify(query).getSingleResult();
//        assertEquals(expResult, result); 
//    }
//    
////    @Test(expected = DinaDatabaseException.class)
//    public void testGetEntityByJPQLNoUniqueResult() throws Exception {
//        System.out.println("getEntityByJPQL");
//        
//        String jpql = "SELECT t FROM Testentity WHERE t.testEntity = 2";
//        
//        theEntity = new Testentity(2);
//        when(entityManager.createQuery(jpql)).thenReturn(query);
//        when(query.getSingleResult()).thenThrow(NonUniqueResultException.class);
//          
////        dao = new DinaDaoImpl(entityManager); 
//        EntityBean result = dao.getEntityByJPQL(jpql);
//        verify(entityManager).createQuery(jpql);
//        verify(query).getSingleResult(); 
//        assertNull(result);
//    }

    /**
     * Test of getCountByQuery method, of class DinaDaoImpl.
     * @throws java.lang.Exception
     */
//    @Test
    public void testGetCountByQuery() throws Exception {
        System.out.println("testGetCountByQuery");
         
        when(entityManager.createQuery(strQuery)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(20);
        
//        dao = new DinaDaoImpl(entityManager); 
//        int result = dao.getCountByQuery(strQuery);
        verify(entityManager).createQuery(strQuery);
        verify(query).getSingleResult();
//        assertEquals(result, 20);
    }
    
//    @Test
    public void testGetCountByQueryException() throws Exception {
        System.out.println("testGetCountByQuery");
         
        when(entityManager.createQuery(strQuery)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(DinaException.class);
        
//        dao = new DinaDaoImpl(entityManager); 
//        int result = dao.getCountByQuery(strQuery);
        verify(entityManager).createQuery(strQuery);
        verify(query).getSingleResult();
//        assertEquals(result, 0);
    }

    /**
     * Test of delete method, of class DinaDaoImpl.
     * @throws java.lang.Exception
     */
//    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        theEntity = new Testentity(20);
        
//        dao = new DinaDaoImpl(entityManager); 
//        dao.delete(theEntity);
        
        verify(entityManager).remove(theEntity);
        verify(entityManager).flush();
    } 
    
//    @Test
    public void testDeleteConstraintViolationException() throws Exception {
        System.out.println("delete");
        
        theEntity = new Testentity(20);
        doThrow(javax.validation.ConstraintViolationException.class).when(entityManager).remove(theEntity);  
//        dao = new DinaDaoImpl(entityManager); 
//        dao.delete(theEntity);
        
        verify(entityManager).remove(theEntity);
        verify(entityManager, times(0)).flush();
    } 
    
//    @Test
    public void testDeleteException() throws Exception {
        System.out.println("delete");
        
        theEntity = new Testentity(20);
        doThrow(DinaException.class).when(entityManager).remove(theEntity);  
//        dao = new DinaDaoImpl(entityManager); 
//        dao.delete(theEntity);
        
        verify(entityManager).remove(theEntity);
        verify(entityManager, times(0)).flush();
    } 
}

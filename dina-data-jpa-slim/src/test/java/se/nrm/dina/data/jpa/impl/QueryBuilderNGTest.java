/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.jpa.impl;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Query; 
import org.junit.AfterClass; 
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;  
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify; 
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.datamodel.impl.Testentity;
import se.nrm.dina.datamodel.util.DataModelHelper;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class QueryBuilderNGTest {

    @Mock
    static Query query;
    
    private QueryBuilder instance;
    
    public QueryBuilderNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception { 
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
 
    /**
     * Test of getInstance method, of class QueryBuilder.
     */
    @Test
    public void testGetInstanceNull() {
        System.out.println("getInstance"); 
        
        instance = null;
        instance = QueryBuilder.getInstance();
        assertNotNull(instance);
    }
    
    
    @Test
    public void testGetInstance() {
        System.out.println("getInstance"); 
         
        instance = QueryBuilder.getInstance();
        assertNotNull(instance);
    }

    
    /**
     * Test of createQuery method, of class QueryBuilder.
     */
    @Test
    public void testCreateQuery() {
        System.out.println("createQuery");
         
        Class clazz = Testentity.class;
         
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "1");
        parameters.put(DataModelHelper.getInstance().getCREATED_BY_FIELD(), "1");
        parameters.put("bgDecimal", "1.0");
        parameters.put("s", "1");
        parameters.put("string", "test");
        parameters.put("version", "1");
        parameters.put("timestampCreated", "2001-01-01");
        
        boolean isExact = false;
  
        instance = new QueryBuilder();
          
        query = instance.createQuery(query, clazz, parameters, isExact);
        assertNotNull(query); 
    }  
    
    
        
    /**
     * Test of createQuery method, of class QueryBuilder.
     */
    @Test
    public void testCreateQueryBetween() {
        System.out.println("createQuery");
         
        Class clazz = Testentity.class;
         
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "1");
        parameters.put(DataModelHelper.getInstance().getCREATED_BY_FIELD(), "1");
        parameters.put("bgDecimal", "between(1.0,10.0)"); 
        parameters.put("s", "1");
        parameters.put("string", "test");
        parameters.put("version", "1");
        parameters.put("timestampCreated", "between(2001-01-01,2003-10-10)");
        
        boolean isExact = true;
   
        instance = new QueryBuilder(); 
           
        query = instance.createQuery(query, clazz, parameters, isExact);
        assertNotNull(query); 
    }  
    
        /**
     * Test of createQuery method, of class QueryBuilder.
     */
    @Test
    public void testCreateQueryBetweenLessthan() {
        System.out.println("createQuery");
         
        Class clazz = Testentity.class;
         
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "1");
        parameters.put(DataModelHelper.getInstance().getCREATED_BY_FIELD(), "1");
        parameters.put("bgDecimal", "lt(10.0)"); 
        parameters.put("s", "1");
        parameters.put("string", "test");
        parameters.put("version", "1");
        parameters.put("timestampCreated", "lt(2003-10-10)");
        
        boolean isExact = true;
   
        instance = new QueryBuilder(); 
           
        query = instance.createQuery(query, clazz, parameters, isExact);
        assertNotNull(query); 
    }  
    
    
    @Test
    public void testCreateQueryBetweenGreatthan() {
        System.out.println("createQuery");
         
        Class clazz = Testentity.class;
         
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "1");
        parameters.put(DataModelHelper.getInstance().getCREATED_BY_FIELD(), "1");
        parameters.put("bgDecimal", "gt(10.0)"); 
        parameters.put("s", null);
        parameters.put("string", "test");
        parameters.put("version", "1");
        parameters.put("timestampCreated", "gt(2003-10-10)");
        
        boolean isExact = true;
   
        instance = new QueryBuilder(); 
           
        query = instance.createQuery(query, clazz, parameters, isExact);
        assertNotNull(query); 
    }  
    
    @Test
    public void testCreateQueryNullParameters() {
        System.out.println("createQuery");
         
        Class clazz = null;
        Map<String, String> parameters = null;
        boolean isExact = false;
        
        instance = new QueryBuilder();  
        query = instance.createQuery(query, clazz, parameters, isExact);
        verify(query, times(0)).setParameter("string", "string");
        assertNotNull(query); 
    } 
    
    @Test(expected = DinaException.class)
    public void testCreateQueryException() {
        System.out.println("createQuery");
         
        Class clazz = Testentity.class;
         
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tttt", "1"); 
        
        boolean isExact = false;
  
        instance = new QueryBuilder();
          
        query = instance.createQuery(query, clazz, parameters, isExact);
        verify(query, times(0)).setParameter("string", "string");
    }  
}

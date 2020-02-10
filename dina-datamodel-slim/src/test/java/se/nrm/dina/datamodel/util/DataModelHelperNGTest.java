/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

 

/**
 *
 * @author idali
 */
public class DataModelHelperNGTest {
    
    
    private final String CREATED_BY_FIELD = "createdByAgentID";
    private final String TIME_CREAGED_FIELD = "timestampCreated";
    private final String VERSION = "version";
    private final String ENTITY_PACKAGE = "se.nrm.dina.datamodel.impl.";
    private final String CREATED_BY_CLASS_NAME = "Agent";
    
    private static DataModelHelper instance;
    
    public DataModelHelperNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        instance = new DataModelHelper();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance = null;
    } 

    /**
     * Test of getInstance method, of class DataModelHelper.
     */
    @Test
    public void testGetInstance() {
        
        System.out.println("getInstance"); 
        DataModelHelper result = DataModelHelper.getInstance();
        assertNotNull(result);
        assertTrue(result instanceof DataModelHelper);
    }

    
    
    /**
     * Test of getInstance method, of class DataModelHelper.
     */
    @Test
    public void testInstanceIsNull() {
        
        System.out.println("testInstanceIsNull"); 
        
        instance = null;
        
        instance = DataModelHelper.getInstance();
        assertNotNull(instance);
        assertTrue(instance instanceof DataModelHelper);
    }
    /**
     * Test of getENTITY_PACKAGE method, of class DataModelHelper.
     */
    @Test
    public void testGetENTITY_PACKAGE() {
        System.out.println("getENTITY_PACKAGE");
          
        String result = instance.getENTITY_PACKAGE();
        assertEquals(result, ENTITY_PACKAGE); 
    }

    /**
     * Test of getVERSION method, of class DataModelHelper.
     */
    @Test
    public void testGetVERSION() {
        System.out.println("getVERSION");  
        
        String result = instance.getVERSION();
        assertEquals(result, VERSION); 
    }

    /**
     * Test of getCREATED_BY_FIELD method, of class DataModelHelper.
     */
    @Test
    public void testGetCREATED_BY_FIELD() {
        System.out.println("getCREATED_BY_FIELD"); 
        String result = instance.getCREATED_BY_FIELD();
        assertEquals(result, CREATED_BY_FIELD); 
    }

    /**
     * Test of getTIME_CREATED_FIELD method, of class DataModelHelper.
     */
    @Test
    public void testGetTIME_CREAGED_FIELD() {
        System.out.println("getTIME_CREAGED_FIELD"); 
        String result = instance.getTIME_CREATED_FIELD();
        assertEquals(result, TIME_CREAGED_FIELD); 
    } 
    
        /**
     * Test of getTIME_CREATED_FIELD method, of class DataModelHelper.
     */
    @Test
    public void testGetCreated_By_Class_Name() {
        System.out.println("testGetCreated_By_Class_Name"); 
        String result = instance.getCREATED_BY_CLASS_NAME();
        assertEquals(result, CREATED_BY_CLASS_NAME); 
    } 
}

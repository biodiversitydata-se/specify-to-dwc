/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.exceptions;

import java.util.ArrayList;
import java.util.List;    
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

import se.nrm.dina.data.vo.ErrorBean;

/**
 *
 * @author idali
 */
public class DinaDatabaseExceptionNGTest {
    
    private static DinaDatabaseException instance; 
    private static ErrorBean errorBean;
    private static List<ErrorBean> errorBeans;
    private static int errorCode;
    private static String errorMsg;

    public DinaDatabaseExceptionNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        errorCode = 400;
        errorBean = new ErrorBean("testName", "testError");
        errorBeans = new ArrayList<>();
        errorBeans.add(errorBean); 
        errorMsg = "error";
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance = null;  
    }
    
    @Test
    public void testConstruct() {
        System.out.println("testConstructWithErrorBean");
        instance = new DinaDatabaseException(); 
        assertNotNull(instance);  
    }

    @Test
    public void testConstructWithErrorBean() {
        System.out.println("testConstructWithErrorBean");
        instance = new DinaDatabaseException(errorBean, errorCode);
        ErrorBean result = instance.getErrorBean();
        int resultCode = instance.getErrorCode();
        assertEquals(result, errorBean);
        assertEquals(resultCode, errorCode);
        assertEquals(errorBean.getErrorMsg(), "testError");
        assertEquals(errorBean.getEntityName(), "testName");
    }
    
    @Test
    public void testConstructWithErrorBeans() {
        System.out.println("testConstructWithErrorBeans");
        instance = new DinaDatabaseException(errorBeans, errorCode);
        List<ErrorBean> results = instance.getErrorBeans();
        int resultCode = instance.getErrorCode();
        assertNotNull(results);
        assertEquals(results, errorBeans);
        assertEquals(resultCode, errorCode);
        assertEquals(results.size(), 1); 
    }
    
    @Test
    public void testConstructWithErrorMessage() {
        System.out.println("testConstructWithErrorMessage");
        instance = new DinaDatabaseException(errorMsg, errorCode);
        String results = instance.getMessage();
        int resultCode = instance.getErrorCode();
        assertNotNull(results);
        assertEquals(results, errorMsg);
        assertEquals(resultCode, errorCode); 
    }
    
    @Test
    public void testConstractorWithThrowable() {
        System.out.println("testConstructWithErrorBeans");
         
        DinaDatabaseException ex = new DinaDatabaseException(errorBean, errorCode);
        
        instance = new DinaDatabaseException(ex);
        Throwable t = instance.getT(); 
        assertNotNull(t);
        assertEquals(t, ex); 
    }
    
    
    /**
     * Test of getErrorCode method, of class DinaException.
     */
    @Test
    public void testGetErrorCode() {
        System.out.println("getErrorCode");
        
        instance = new DinaDatabaseException(errorBean, errorCode);
 
        int result = instance.getErrorCode();
        assertEquals(result, errorCode); 
    } 
    
    /**
     * Test of getErrorCode method, of class DinaException.
     */
    @Test
    public void testGetErrorBean() {
        System.out.println("getErrorCode");
        
        instance = new DinaDatabaseException(errorBean, errorCode);
 
        ErrorBean result = instance.getErrorBean();
        assertNotNull(result);
        assertEquals(result, errorBean);  
        assertEquals("testError", result.getErrorMsg());
        assertEquals("testName", result.getEntityName());
    } 
    
     /**
     * Test of getErrorCode method, of class DinaException.
     */
    @Test
    public void testGetErrorBeans() {
        System.out.println("getErrorCode");
        
        instance = new DinaDatabaseException(errorBeans, errorCode);
 
        List<ErrorBean> results = instance.getErrorBeans();
        assertNotNull(results); 
        assertEquals(results, errorBeans); 
        assertEquals(results.size(), 1); 
    } 
}

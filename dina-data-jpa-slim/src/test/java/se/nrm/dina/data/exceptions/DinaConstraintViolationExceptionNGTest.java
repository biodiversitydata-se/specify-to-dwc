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
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
 
import se.nrm.dina.data.vo.ErrorBean;

/**
 *
 * @author idali
 */
public class DinaConstraintViolationExceptionNGTest {
    
    private static DinaConstraintViolationException instance;
    private static DinaConstraintViolationException instance1;
    private static DinaConstraintViolationException instance2;
    private static ErrorBean errorBean;
    private static List<ErrorBean> errorBeans;
    private static int errorCode;
    
    public DinaConstraintViolationExceptionNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        errorCode = 400;
        errorBean = new ErrorBean("testName", "testError");
        errorBeans = new ArrayList<>();
        errorBeans.add(errorBean);
        
        instance = new DinaConstraintViolationException(errorBean, errorCode); 
        instance1 = new DinaConstraintViolationException(errorBeans, errorCode);
        instance2 = new DinaConstraintViolationException(instance);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance = null;
        instance1 = null;
        instance2 = null;
    }
  
    @Test
    public void testDinaException() {
        assertTrue(instance instanceof DinaException);
    }
    
    @Test
    public void testConstractor1() {
        ErrorBean result = instance.getErrorBean();
        int resultCode = instance.getErrorCode();
        assertEquals(result, errorBean);
        assertEquals(resultCode, errorCode);
        assertEquals(errorBean.getErrorMsg(), "testError");
        assertEquals(errorBean.getEntityName(), "testName");
    }
    
    @Test
    public void testConstractor2() {
        List<ErrorBean> results = instance1.getErrorBeans();
        int resultCode = instance1.getErrorCode();
        assertNotNull(results);
        assertEquals(results, errorBeans);
        assertEquals(resultCode, errorCode);
        assertEquals(results.size(), 1); 
    }
    
    @Test
    public void testConstractor3() {
        Throwable t = instance2.getT(); 
        assertNotNull(t);
        assertEquals(t, instance); 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.vo;
 
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author idali
 */
public class ErrorBeanNGTest {
    
    private static ErrorBean instance;
    
    public ErrorBeanNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        instance = new ErrorBean();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance = null;
    }
 
    /**
     * Test of getEntityName method, of class ErrorBean.
     */
    @Test
    public void testGetEntityName() {
        System.out.println("getEntityName");
  
        String expResult = "ErrorBean";
        instance.setEntityName("ErrorBean");
        String result = instance.getEntityName();
        assertEquals(result, expResult); 
    }

    /**
     * Test of setEntityName method, of class ErrorBean.
     */
    @Test
    public void testSetEntityName() {
        System.out.println("setEntityName");
        String entityName = "testEntityName"; 
        instance.setEntityName(entityName); 
        assertEquals(entityName, instance.getEntityName());
    }

    /**
     * Test of getConstrianKey method, of class ErrorBean.
     */
    @Test
    public void testGetConstrianKey() {
        System.out.println("getConstrianKey"); 
         
        String expResult = "testKey"; 
        instance.setConstrianKey("testKey");
        String result = instance.getConstrianKey();
        assertEquals(result, expResult); 
    }

    /**
     * Test of setConstrianKey method, of class ErrorBean.
     */
    @Test
    public void testSetConstrianKey() {
        System.out.println("setConstrianKey");
        String constrianKey = "testKey"; 
        instance.setConstrianKey(constrianKey); 
        assertEquals(constrianKey, instance.getConstrianKey());
    }

    /**
     * Test of getErrorMsg method, of class ErrorBean.
     */
    @Test
    public void testGetErrorMsg() {
        System.out.println("getErrorMsg"); 
        String expResult = "Error";
        instance.setErrorMsg("Error");
        String result = instance.getErrorMsg();
        assertEquals(result, expResult); 
    }

    /**
     * Test of setErrorMsg method, of class ErrorBean.
     */
    @Test
    public void testSetErrorMsg() {
        System.out.println("setErrorMsg");
        String errorMsg = "Error"; 
        instance.setErrorMsg(errorMsg); 
        assertEquals(errorMsg, instance.getErrorMsg());
    }

    /**
     * Test of getViolation method, of class ErrorBean.
     */
    @Test
    public void testGetViolation() {
        System.out.println("getViolation"); 
        String expResult = "Error";
        instance.setViolation("Error");
        String result = instance.getViolation();
        assertEquals(result, expResult);  
    }

    /**
     * Test of setViolation method, of class ErrorBean.
     */
    @Test
    public void testSetViolation() {
        System.out.println("setViolation");
        String violation = "Error"; 
        instance.setViolation(violation); 
        assertEquals(violation, instance.getViolation());
    }

    /**
     * Test of getInvalidValue method, of class ErrorBean.
     */
    @Test
    public void testGetInvalidValue() {
        System.out.println("getInvalidValue"); 
        String expResult = "invalidValue";
        instance.setInvalidValue("invalidValue");
        String result = instance.getInvalidValue();
        assertEquals(result, expResult); 
    }

    /**
     * Test of setInvalidValue method, of class ErrorBean.
     */
    @Test
    public void testSetInvalidValue() {
        System.out.println("setInvalidValue");
        String invalidValue = "invalidValue"; 
        instance.setInvalidValue(invalidValue); 
        assertEquals(invalidValue, instance.getInvalidValue());
    } 
}

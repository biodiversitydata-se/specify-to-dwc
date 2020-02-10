/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.exceptions;
  
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

 



/**
 *
 * @author idali
 */
public class ErrorMsgNGTest {
    
    private static ErrorMsg instance;
    
    public ErrorMsgNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        instance = new ErrorMsg();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

 
    /**
     * Test of getInstance method, of class ErrorMsg.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance"); 
        ErrorMsg result = ErrorMsg.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getEntityNameErrorMsg method, of class ErrorMsg.
     */
    @Test
    public void testGetEntityNameErrorMsg() {
        System.out.println("getEntityNameErrorMsg");
         
        String expResult = "No such entity ";
        String result = instance.getEntityNameErrorMsg();
        assertEquals(result, expResult); 
    } 

    /**
     * Test of getBadRequestCode method, of class ErrorMsg.
     */
    @Test
    public void testGetBadRequestCode() {
        System.out.println("getBadRequestCode"); 
        int expResult = 400;
        int result = instance.getBadRequestCode();
        assertEquals(result, expResult); 
    }

    /**
     * Test of getFieldNotExist method, of class ErrorMsg.
     */
    @Test
    public void testGetFieldNotExist() {
        System.out.println("getFieldNotExist");
        
        String entityName = "testEntity";
        String fieldName = "testField"; 
        
        StringBuilder sb = new StringBuilder("The entity: ");
        sb.append(entityName);
        sb.append(" doesn't have this field: ");
        sb.append(fieldName);
         
        String expResult = sb.toString();
        String result = instance.getFieldNotExist(entityName, fieldName);
        assertEquals(result, expResult); 
    }

    /**
     * Test of getNonUniqueErrorCode method, of class ErrorMsg.
     */
    @Test
    public void testGetNonUniqueErrorCode() {
        System.out.println("getNonUniqueErrorCode"); 
        
        int expResult = 400;
        int result = instance.getNonUniqueErrorCode();
        assertEquals(result, expResult); 
    }

    /**
     * Test of getFieldNameInvalid method, of class ErrorMsg.
     */
    @Test
    public void testGetFieldNameInvalid() {
        System.out.println("getFieldNameInvalid");
  
        String expResult = "No such field ";
        String result = instance.getFieldNameInvalid();
        assertEquals(result, expResult); 
    }
}

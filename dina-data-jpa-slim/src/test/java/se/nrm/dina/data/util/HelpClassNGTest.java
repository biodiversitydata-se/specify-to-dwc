package se.nrm.dina.data.util;

import java.math.BigDecimal; 
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;   
import org.junit.AfterClass; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;
import se.nrm.dina.data.exceptions.DinaException;

/**
 *
 * @author idali
 */
public class HelpClassNGTest {
    
    private static HelpClass testInstance;
    
    public HelpClassNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        testInstance = HelpClass.getInstance();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
 
    /**
     * Test of getInstance method, of class HelpClass.
     */
    @Test
    public void testGetInstance() {
        System.out.println("testGetInstance");
         
        HelpClass result = HelpClass.getInstance();
        assertNotNull(result);   
    }

    /**
     * Test of convertStringToDouble method, of class HelpClass.
     */
    @Test
    public void testConvertStringToDouble() {
        System.out.println("convertStringToDouble");
        
        String strDouble = "20.5"; 
        double expResult = 20.5;
        double result = testInstance.convertStringToDouble(strDouble);
        assertEquals(expResult, result, 20.5);
    }

    /**
     * Test of convertStringToBigDecimal method, of class HelpClass.
     */
    @Test
    public void testConvertStringToBigDecimal() {
        System.out.println("convertStringToBigDecimal");
        String strDouble = "20.5";
  
        BigDecimal expResult = new BigDecimal(20.5);
        BigDecimal result = testInstance.convertStringToBigDecimal(strDouble);
        assertEquals(result, expResult); 
    }

    /**
     * Test of convertStringToDate method, of class HelpClass.
     */
    @Test
    public void testConvertStringToDate() {
        System.out.println("convertStringToDate");
        
        String strDate = "2000-01-01";
  
        Calendar calendar = new GregorianCalendar(2000, 00, 01);
        Date expResult = calendar.getTime();
        Date result = testInstance.convertStringToDate(strDate); 
        assertEquals(result, expResult); 
    }
    
    @Test(expected = DinaException.class)
    public void testConvertStringToDateFailure() {
        System.out.println("convertStringToDate");
        
        String strDate = "20001212"; 
        Date result = testInstance.convertStringToDate(strDate);  
        assertNull(result);
    }
    
    @Test
    public void testConvertStringToShort() {
        System.out.println("testConvertStringToShort");
        
        String s = "2";  
        int result = testInstance.convertStringToShort(s);
        assertEquals(result, 2); 
    }
    
    @Test
    public void testConvertStringToShortNull() {
        System.out.println("testConvertStringToShort");
        
        String s = null;  
        int result = testInstance.convertStringToShort(s);
        assertEquals(result, 0);
    }
    
    @Test
    public void testConvertStringToShortException() {
        System.out.println("testConvertStringToShort");
        
        String s = "t";  
        int result = testInstance.convertStringToShort(s);
        assertEquals(result, 0);
    }

    /**
     * Test of maxLimit method, of class HelpClass.
     */
    @Test
    public void testMaxLessThenLimit() {
        System.out.println("maxLimit");
        int limit = 2000; 
        int expResult = 500;
        int result = testInstance.maxLimit(limit);
        assertEquals(result, expResult); 
    }
    
    
    /**
     * Test of maxLimit method, of class HelpClass.
     */
    @Test
    public void testMaxGreatThenLimit() {
        System.out.println("maxLimit");
        int limit = 20; 
        int expResult = 20;
        int result = testInstance.maxLimit(limit);
        assertEquals(result, expResult); 
    }
    
    @Test
    public void testMaxLimit() {
        System.out.println("maxLimit");
        int limit = 0;
 
        int result = testInstance.maxLimit(limit);
        assertEquals(result, 50); 
    }
}

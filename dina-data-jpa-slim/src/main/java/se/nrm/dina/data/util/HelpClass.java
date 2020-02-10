/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;  
import se.nrm.dina.data.exceptions.DinaException;

/**
 *
 * @author idali
 */
public class HelpClass {
      
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
    private final int DEFAULT_LIMIT = 50;
    private final int MAX_LIMIT = 500;
    
    private static HelpClass instance = null;
    
    public static synchronized HelpClass getInstance() {
        if (instance == null) {
            instance = new HelpClass();
        }
        return instance;
    }  
    
    public double convertStringToDouble(String strDouble) { 
        return Double.parseDouble(strDouble);
    }
    
    public BigDecimal convertStringToBigDecimal(String strDouble) {
        return new BigDecimal(strDouble);
    }
    
    public Date convertStringToDate(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException ex) {
            throw new DinaException("Error.  " + ex.getMessage());
        }
    }
    
    public int convertStringToShort(String s) {
        if(s == null) {
            return 0;
        } 
        try {
            return Short.parseShort(s); 
        } catch(NumberFormatException e) {
            return 0;
        }
    }
    
        
    /**
     * Calculates limit 
     * @param limit
     * @return int
     */
    public int maxLimit(int limit) {
        if(limit > MAX_LIMIT) {
            return MAX_LIMIT;
        } else if(limit == 0) {
            return DEFAULT_LIMIT;
        } else {
            return limit;
        } 
    } 
}

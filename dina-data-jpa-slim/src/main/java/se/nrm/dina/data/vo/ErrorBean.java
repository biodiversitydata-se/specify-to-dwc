/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.vo;
  
import java.io.Serializable; 

/**
 *
 * @author idali
 */
public class ErrorBean implements Serializable {
    
    
    private String entityName;
    private String constrianKey;
    private String violation;
    private String errorMsg;
    private String invalidValue;
    
    public ErrorBean() {
        
    }
    
    public ErrorBean(String entityName, String errorMsg) {
        this.entityName = entityName;
        this.errorMsg = errorMsg;
    }
  
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName; 
    }

    public String getConstrianKey() {
        return constrianKey;
    }

    public void setConstrianKey(String constrianKey) {
        this.constrianKey = constrianKey;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }   
}

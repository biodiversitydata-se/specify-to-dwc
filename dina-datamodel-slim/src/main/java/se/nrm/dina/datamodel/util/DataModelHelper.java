/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.util;

/**
 *
 * @author idali
 */
public class DataModelHelper {
    
    private final String CREATED_BY_FIELD = "createdByAgentID";
    private final String TIME_CREATED_FIELD = "timestampCreated";
    private final String VERSION = "version";
    private final String ENTITY_PACKAGE = "se.nrm.dina.datamodel.impl.";
    private final String CREATED_BY_CLASS_NAME = "Agent";
      
    private static DataModelHelper instance = null;
     
    public static synchronized DataModelHelper getInstance() {
        if (instance == null) {
            instance = new DataModelHelper();
        }
        return instance;
    }

    public String getENTITY_PACKAGE() {
        return ENTITY_PACKAGE;
    }
     
    public String getVERSION() {
        return VERSION;
    }
 
    public String getCREATED_BY_FIELD() {
        return CREATED_BY_FIELD;
    }

    public String getTIME_CREATED_FIELD() {
        return TIME_CREATED_FIELD;
    } 

    public String getCREATED_BY_CLASS_NAME() {
        return CREATED_BY_CLASS_NAME;
    } 
}

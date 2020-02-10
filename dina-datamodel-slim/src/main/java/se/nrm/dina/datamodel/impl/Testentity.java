package se.nrm.dina.datamodel.impl;

import java.math.BigDecimal; 
import java.util.List; 
import javax.persistence.Id; 
import javax.xml.bind.annotation.XmlTransient;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import se.nrm.dina.datamodel.BaseEntity; 

/**
 *
 * @author idali
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Testentity extends BaseEntity {
    
    @Id
    private int id;
    
//    private String identityString;
//    private int entityId;
    
    private Agent createdByAgentID;
    private List<String> testList;
    private BigDecimal bgDecimal;
    private short s;
    private String string;
    
    
    public Testentity() {
        
    }
    
    public Testentity(int id) {
        this.id = id;
    }
 
    @XmlTransient 
    @Override 
    public String getIdentityString() {
        return String.valueOf(id);
    }

    @XmlTransient 
    @Override
    public int getEntityId() {
        return id;
    }

    public int getId() {
        return id;
    } 

    public Agent getCreatedByAgentID() {
        return createdByAgentID;
    }

    public void setCreatedByAgentID(Agent createdByAgentID) {
        this.createdByAgentID = createdByAgentID;
    } 

    public BigDecimal getBgDecimal() {
        return bgDecimal;
    }

    public void setBgDecimal(BigDecimal bgDecimal) {
        this.bgDecimal = bgDecimal;
    }

    public short getS() {
        return s;
    }

    public void setS(short s) {
        this.s = s;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
     
    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }  
}

package se.nrm.dina.datamodel.impl;

import java.math.BigDecimal;  
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlTransient; 
import se.nrm.dina.datamodel.BaseEntity;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */ 
public class Testentity extends BaseEntity {

  @Id
  private int id;
 
  private Agent createdByAgentID;
  private List<String> testList;
  private BigDecimal bgDecimal;
  private short s;
  private String string;
  private boolean isTrue;
  private java.util.Date date1;
  private java.sql.Date date2;
  private List<EntityBean> beans;

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

  public boolean isIsTrue() {
    return isTrue;
  }

  public void setIsTrue(boolean isTrue) {
    this.isTrue = isTrue;
  }

  public Date getDate1() {
    return date1;
  }

  public void setDate1(Date date1) {
    this.date1 = date1;
  }

  public java.sql.Date getDate2() {
    return date2;
  }

  public void setDate2(java.sql.Date date2) {
    this.date2 = date2;
  }
   
  public List<EntityBean> getBeans() {
    return beans;
  }

  public void setBeans(List<EntityBean> beans) {
    this.beans = beans;
  }

}

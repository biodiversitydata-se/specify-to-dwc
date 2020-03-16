package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date; 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Lob; 
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID; 
import javax.xml.bind.annotation.XmlRootElement; 

/**
 *
 * @author idali
 */
@Entity
@Table(name = "collectingeventattribute")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Collectingeventattribute.findAll", query = "SELECT c FROM Collectingeventattribute c"),
  @NamedQuery(name = "Collectingeventattribute.findByCollectingEventAttributeID", 
          query = "SELECT c FROM Collectingeventattribute c WHERE c.collectingEventAttributeID = :collectingEventAttributeID")})
public class Collectingeventattribute extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "CollectingEventAttributeID")
  private Integer collectingEventAttributeID;

  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "Number1")
  private Float number1;

  @Column(name = "Number2")
  private Float number2;
 
  @Lob
  @Size(max = 65535)
  @Column(name = "Text1")
  private String text1;

  @Lob
  @Size(max = 65535)
  @Column(name = "Text2")
  private String text2;

  @Lob
  @Size(max = 65535)
  @Column(name = "Text3")
  private String text3;
   
  @Column(name = "Text4")
  private String text4;
  
  @Size(max = 100)
  @Column(name = "Text5")
  private String text5;
  
  @Size(max = 50)
  @Column(name = "Text6")
  private String text6;
  
  @Size(max = 50)
  @Column(name = "Text7")
  private String text7;
  
  @Size(max = 50)
  @Column(name = "Text8")
  private String text8;

  @Size(max = 50)
  @Column(name = "Text9")
  private String text9;

  @Column(name = "YesNo1")
  private Boolean yesNo1;

  @Column(name = "YesNo2")
  private Boolean yesNo2;

  @Column(name = "YesNo3")
  private Boolean yesNo3;
 
  public Collectingeventattribute() {
  }

  public Collectingeventattribute(Integer collectingEventAttributeID) {
    this.collectingEventAttributeID = collectingEventAttributeID;
  }

  public Collectingeventattribute(Integer collectingEventAttributeID, Date timestampCreated) {
    this.collectingEventAttributeID = collectingEventAttributeID;
    this.timestampCreated = timestampCreated;
  }

  @XmlID
  @XmlAttribute(name = "id")
  @Override
  public String getIdentityString() {
    return String.valueOf(collectingEventAttributeID);
  }

  @Override
  public int getEntityId() {
    return collectingEventAttributeID;
  }

  public Integer getCollectingEventAttributeID() {
    return collectingEventAttributeID;
  }

  public void setCollectingEventAttributeID(Integer collectingEventAttributeID) {
    this.collectingEventAttributeID = collectingEventAttributeID;
  }

  public Float getNumber1() {
    return number1;
  }

  public void setNumber1(Float number1) {
    this.number1 = number1;
  }

  public Float getNumber2() {
    return number2;
  }

  public void setNumber2(Float number2) {
    this.number2 = number2;
  }
 
  public String getText1() {
    return text1;
  }

  public String getText2() {
    return text2;
  }

  public void setText2(String text2) {
    this.text2 = text2;
  }

  public String getText3() {
    return text3;
  }

  public void setText3(String text3) {
    this.text3 = text3;
  }
  
  public String getText4() {
    return text4;
  }

  public void setText4(String text4) {
    this.text4 = text4;
  }

  public String getText5() {
    return text5;
  }

  public void setText5(String text5) {
    this.text5 = text5;
  }

  public String getText6() {
    return text6;
  }

  public void setText6(String text6) {
    this.text6 = text6;
  }

  public String getText7() {
    return text7;
  }

  public void setText7(String text7) {
    this.text7 = text7;
  }

  public String getText8() {
    return text8;
  }

  public void setText8(String text8) {
    this.text8 = text8;
  }

 
  public String getText9() {
    return text9;
  }

  public void setText9(String text9) {
    this.text9 = text9;
  }
  
  public Boolean getYesNo1() {
    return yesNo1;
  }

  public void setYesNo1(Boolean yesNo1) {
    this.yesNo1 = yesNo1;
  }

  public Boolean getYesNo2() {
    return yesNo2;
  }

  public void setYesNo2(Boolean yesNo2) {
    this.yesNo2 = yesNo2;
  }

  public Boolean getYesNo3() {
    return yesNo3;
  }

  public void setYesNo3(Boolean yesNo3) {
    this.yesNo3 = yesNo3;
  }
  
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (collectingEventAttributeID != null ? collectingEventAttributeID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Collectingeventattribute)) {
      return false;
    }
    Collectingeventattribute other = (Collectingeventattribute) object;
    return !((this.collectingEventAttributeID == null && other.collectingEventAttributeID != null) || (this.collectingEventAttributeID != null && !this.collectingEventAttributeID.equals(other.collectingEventAttributeID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collectingeventattribute[ collectingEventAttributeID=" + collectingEventAttributeID + " ]";
  }
}

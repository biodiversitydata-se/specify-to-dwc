package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date; 
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "determination")
@NamedQueries({
  @NamedQuery(name = "Determination.findAll", query = "SELECT d FROM Determination d"),
  @NamedQuery(name = "Determination.findByDeterminationID", query = "SELECT d FROM Determination d WHERE d.determinationID = :determinationID"),
  @NamedQuery(name = "Determination.findByCollectionMemberID", query = "SELECT d FROM Determination d WHERE d.collectionMemberID = :collectionMemberID"),
  @NamedQuery(name = "Determination.findByDeterminedDate", query = "SELECT d FROM Determination d WHERE d.determinedDate = :determinedDate"),
  @NamedQuery(name = "Determination.findByIsCurrent", query = "SELECT d FROM Determination d WHERE d.isCurrent = :isCurrent")})
public class Determination extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "DeterminationID")
  private Integer determinationID;

  @Basic(optional = false)
  @NotNull
  @Min(value = 1, message = "collectionMemberID can not be null")
  @Column(name = "CollectionMemberID")
  private int collectionMemberID;

  @Column(name = "DeterminedDate")
  @Temporal(TemporalType.DATE)
  private Date determinedDate;

  @Basic(optional = false)
  @NotNull(message = "IsCurrent can not be null")
  @Column(name = "IsCurrent")
  private boolean isCurrent;
 
  @Lob
  @Size(max = 65535)
  @Column(name = "Remarks")
  private String remarks;
  
  @Size(max = 16)
  @Column(name = "Qualifier")
  private String qualifier;

  @Size(max = 50)
  @Column(name = "TypeStatusName")
  private String typeStatusName;

  @JoinColumn(name = "TaxonID", referencedColumnName = "TaxonID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Taxon taxon;

  @JoinColumn(name = "PreferredTaxonID", referencedColumnName = "TaxonID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Taxon preferredTaxon;

  @JoinColumn(name = "CollectionObjectID", referencedColumnName = "CollectionObjectID")
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collectionobject collectionObject;

  @JoinColumn(name = "DeterminerID", referencedColumnName = "AgentID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Agent determiner;
  
   

  public Determination() {
  }

  public Determination(Integer determinationID) {
    this.determinationID = determinationID;
  }

  public Determination(Integer determinationID, Date timestampCreated, int collectionMemberID, boolean isCurrent) {
    this.determinationID = determinationID;
    this.timestampCreated = timestampCreated;
    this.collectionMemberID = collectionMemberID;
    this.isCurrent = isCurrent;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(determinationID);
  }

  @Override
  public int getEntityId() {
    return determinationID;
  }

  public Integer getDeterminationID() {
    return determinationID;
  }

  public void setDeterminationID(Integer determinationID) {
    this.determinationID = determinationID;
  }

  public int getCollectionMemberID() {
    return collectionMemberID;
  }

  public void setCollectionMemberID(int collectionMemberID) {
    this.collectionMemberID = collectionMemberID;
  }

  public Date getDeterminedDate() {
    return determinedDate;
  }

  public void setDeterminedDate(Date determinedDate) {
    this.determinedDate = determinedDate;
  }

  public boolean getIsCurrent() {
    return isCurrent;
  }

  public void setIsCurrent(boolean isCurrent) {
    this.isCurrent = isCurrent;
  }
   
  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
  }

//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
//    }
// 
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getTypeStatusName() {
    return typeStatusName;
  }

  public void setTypeStatusName(String typeStatusName) {
    this.typeStatusName = typeStatusName;
  }

  public Taxon getTaxon() {
    return taxon;
  }

  public void settaxon(Taxon taxon) {
    this.taxon = taxon;
  }

  public Taxon getPreferredTaxon() {
    return preferredTaxon;
  }

  public void setPreferredTaxon(Taxon preferredTaxon) {
    this.preferredTaxon = preferredTaxon;
  }

  public Collectionobject getCollectionObject() {
    return collectionObject;
  }

  public void setCollectionObject(Collectionobject collectionObject) {
    this.collectionObject = collectionObject;
  }

  public Agent getDeterminer() {
    return determiner;
  }

  public void setDeterminer(Agent determiner) {
    this.determiner = determiner;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (determinationID != null ? determinationID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Determination)) {
      return false;
    }
    Determination other = (Determination) object;
    return !((this.determinationID == null && other.determinationID != null)
            || (this.determinationID != null && !this.determinationID.equals(other.determinationID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Determination[ determinationID=" + determinationID + " ]";
  }
}

package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "collectionobjectcitation")
@NamedQueries({
  @NamedQuery(name = "Collectionobjectcitation.findAll", query = "SELECT c FROM Collectionobjectcitation c"),
  @NamedQuery(name = "Collectionobjectcitation.findByCollectionObjectCitationID", 
         query = "SELECT c FROM Collectionobjectcitation c WHERE c.collectionObjectCitationID = :collectionObjectCitationID"),
  @NamedQuery(name = "Collectionobjectcitation.findByCollectionMemberID", 
          query = "SELECT c FROM Collectionobjectcitation c WHERE c.collectionMemberID = :collectionMemberID")})
public class Collectionobjectcitation extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "CollectionObjectCitationID")
  private Integer collectionObjectCitationID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "CollectionMemberID")
  private int collectionMemberID;
 
  @JoinColumn(name = "CollectionObjectID", referencedColumnName = "CollectionObjectID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Collectionobject collectionObject;

  public Collectionobjectcitation() {
  }

  public Collectionobjectcitation(Integer collectionObjectCitationID) {
    this.collectionObjectCitationID = collectionObjectCitationID;
  }

  public Collectionobjectcitation(Integer collectionObjectCitationID, Date timestampCreated, int collectionMemberID) {
    this.collectionObjectCitationID = collectionObjectCitationID;
    this.timestampCreated = timestampCreated;
    this.collectionMemberID = collectionMemberID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(collectionObjectCitationID);
  }

  @Override
  public int getEntityId() {
    return collectionObjectCitationID;
  }

  public Integer getCollectionObjectCitationID() {
    return collectionObjectCitationID;
  }

  public void setCollectionObjectCitationID(Integer collectionObjectCitationID) {
    this.collectionObjectCitationID = collectionObjectCitationID;
  }

  public int getCollectionMemberID() {
    return collectionMemberID;
  }

  public void setCollectionMemberID(int collectionMemberID) {
    this.collectionMemberID = collectionMemberID;
  }

//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
  public Collectionobject getCollectionObject() {
    return collectionObject;
  }

  public void setCollectionObject(Collectionobject collectionObject) {
    this.collectionObject = collectionObject;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (collectionObjectCitationID != null ? collectionObjectCitationID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Collectionobjectcitation)) {
      return false;
    }
    Collectionobjectcitation other = (Collectionobjectcitation) object;
    return !((this.collectionObjectCitationID == null && other.collectionObjectCitationID != null)
            || (this.collectionObjectCitationID != null && !this.collectionObjectCitationID.equals(other.collectionObjectCitationID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collectionobjectcitation[ collectionObjectCitationID=" + collectionObjectCitationID + " ]";
  }
}

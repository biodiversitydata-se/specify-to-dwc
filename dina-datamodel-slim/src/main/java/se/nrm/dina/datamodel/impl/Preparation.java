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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "preparation")
@NamedQueries({
  @NamedQuery(name = "Preparation.findAll", query = "SELECT p FROM Preparation p"),
  @NamedQuery(name = "Preparation.findByPreparationID",
          query = "SELECT p FROM Preparation p WHERE p.preparationID = :preparationID")})
public class Preparation extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "PreparationID")
  private Integer preparationID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "CollectionMemberID")
  private int collectionMemberID;

  @Column(name = "CountAmt")
  private Integer countAmt;

  @Column(name = "PreparedDate")
  @Temporal(TemporalType.DATE)
  private Date preparedDate;

  @Column(name = "PreparedDatePrecision")
  private Short preparedDatePrecision;

  @Size(max = 32)
  @Column(name = "SampleNumber")
  private String sampleNumber;

  @Size(max = 32)
  @Column(name = "Status")
  private String status;

  @Size(max = 50)
  @Column(name = "StorageLocation")
  private String storageLocation;

  @JoinColumn(name = "PrepTypeID", referencedColumnName = "PrepTypeID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Preptype prepType;

  @JoinColumn(name = "CollectionObjectID", referencedColumnName = "CollectionObjectID")
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collectionobject collectionObject;

  @JoinColumn(name = "StorageID", referencedColumnName = "StorageID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Storage storage;

  @JoinColumn(name = "PreparedByID", referencedColumnName = "AgentID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Agent preparedBy;

  public Preparation() {
  }

  public Preparation(Integer preparationID) {
    this.preparationID = preparationID;
  }

  public Preparation(Integer preparationID, Date timestampCreated, int collectionMemberID) {
    this.preparationID = preparationID;
    this.timestampCreated = timestampCreated;
    this.collectionMemberID = collectionMemberID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(preparationID);
  }

  @Override
  public int getEntityId() {
    return preparationID == null ? 0 : preparationID;
  }

  public Integer getPreparationID() {
    return preparationID;
  }

  public void setPreparationID(Integer preparationID) {
    this.preparationID = preparationID;
  }

  public int getCollectionMemberID() {
    return collectionMemberID;
  }

  public void setCollectionMemberID(int collectionMemberID) {
    this.collectionMemberID = collectionMemberID;
  }

  public Integer getCountAmt() {
    return countAmt;
  }

  public void setCountAmt(Integer countAmt) {
    this.countAmt = countAmt;
  }

  public Date getPreparedDate() {
    return preparedDate;
  }

  public void setPreparedDate(Date preparedDate) {
    this.preparedDate = preparedDate;
  }

  public Short getPreparedDatePrecision() {
    return preparedDatePrecision;
  }

  public void setPreparedDatePrecision(Short preparedDatePrecision) {
    this.preparedDatePrecision = preparedDatePrecision;
  }

  public String getSampleNumber() {
    return sampleNumber;
  }

  public void setSampleNumber(String sampleNumber) {
    this.sampleNumber = sampleNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStorageLocation() {
    return storageLocation;
  }

  public void setStorageLocation(String storageLocation) {
    this.storageLocation = storageLocation;
  }

  public Preptype getPrepType() {
    return prepType;
  }

  public void setPrepType(Preptype prepType) {
    this.prepType = prepType;
  }

  public Collectionobject getCollectionObject() {
    return collectionObject;
  }

  public void setCollectionObject(Collectionobject collectionObject) {
    this.collectionObject = collectionObject;
  }

  public Storage getStorage() {
    return storage;
  }

  public void setStorage(Storage storage) {
    this.storage = storage;
  }

  public Agent getPreparedBy() {
    return preparedBy;
  }

  public void setPreparedBy(Agent preparedBy) {
    this.preparedBy = preparedBy;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (preparationID != null ? preparationID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Preparation)) {
      return false;
    }
    Preparation other = (Preparation) object;
    return !((this.preparationID == null && other.preparationID != null)
            || (this.preparationID != null && !this.preparationID.equals(other.preparationID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Preparation[ preparationID=" + preparationID + " ]";
  }
}

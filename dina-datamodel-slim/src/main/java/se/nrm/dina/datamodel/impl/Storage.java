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
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "storage")
@NamedQueries({
  @NamedQuery(name = "Storage.findAll", query = "SELECT s FROM Storage s"),
  @NamedQuery(name = "Storage.findByStorageID", query = "SELECT s FROM Storage s WHERE s.storageID = :storageID"),
  @NamedQuery(name = "Storage.findByFullName", query = "SELECT s FROM Storage s WHERE s.fullName = :fullName")})
public class Storage extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "StorageID")
  private Integer storageID;

  @Size(max = 255)
  @Column(name = "FullName")
  private String fullName;

  @Column(name = "HighestChildNodeNumber")
  private Integer highestChildNodeNumber;

  @Column(name = "IsAccepted")
  private Boolean isAccepted;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 64)
  @Column(name = "Name")
  private String name;

  @Column(name = "NodeNumber")
  private Integer nodeNumber;

  @Basic(optional = false)
  @NotNull
  @Column(name = "RankID")
  private int rankID;
 
  @JoinColumn(name = "AcceptedID", referencedColumnName = "StorageID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Storage accepted;

  @JoinColumn(name = "StorageTreeDefItemID", referencedColumnName = "StorageTreeDefItemID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Storagetreedefitem storageTreeDefItem;

  @JoinColumn(name = "StorageTreeDefID", referencedColumnName = "StorageTreeDefID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Storagetreedef storageTreeDef;

  @JoinColumn(name = "ParentID", referencedColumnName = "StorageID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Storage parent;

  public Storage() {
  }

  public Storage(Integer storageID) {
    this.storageID = storageID;
  }

  public Storage(Integer storageID, Date timestampCreated, String name, int rankID) {
    this.storageID = storageID;
    this.timestampCreated = timestampCreated;
    this.name = name;
    this.rankID = rankID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(storageID);
  }

  @Override
  public int getEntityId() {
    return storageID;
  }

  public Integer getStorageID() {
    return storageID;
  }

  public void setStorageID(Integer storageID) {
    this.storageID = storageID;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getHighestChildNodeNumber() {
    return highestChildNodeNumber;
  }

  public void setHighestChildNodeNumber(Integer highestChildNodeNumber) {
    this.highestChildNodeNumber = highestChildNodeNumber;
  }

  public Boolean getIsAccepted() {
    return isAccepted;
  }

  public void setIsAccepted(Boolean isAccepted) {
    this.isAccepted = isAccepted;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNodeNumber() {
    return nodeNumber;
  }

  public void setNodeNumber(Integer nodeNumber) {
    this.nodeNumber = nodeNumber;
  }

  public int getRankID() {
    return rankID;
  }

  public void setRankID(int rankID) {
    this.rankID = rankID;
  }
 
  public Storage getAccepted() {
    return accepted;
  }

  public void setAccepted(Storage accepted) {
    this.accepted = accepted;
  }

  public Storagetreedefitem getStorageTreeDefItem() {
    return storageTreeDefItem;
  }

  public void setStorageTreeDefItem(Storagetreedefitem storageTreeDefItem) {
    this.storageTreeDefItem = storageTreeDefItem;
  }

  public Storagetreedef getStorageTreeDef() {
    return storageTreeDef;
  }

  public void setStorageTreeDef(Storagetreedef storageTreeDef) {
    this.storageTreeDef = storageTreeDef;
  }

  public Storage getParent() {
    return parent;
  }

  public void setParent(Storage parent) {
    this.parent = parent;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (storageID != null ? storageID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Storage)) {
      return false;
    }
    Storage other = (Storage) object;
    return !((this.storageID == null && other.storageID != null) || (this.storageID != null && !this.storageID.equals(other.storageID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Storage[ storageID=" + storageID + " ]";
  }
}

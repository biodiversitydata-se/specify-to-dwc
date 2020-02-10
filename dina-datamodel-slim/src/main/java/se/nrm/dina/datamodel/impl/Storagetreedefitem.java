package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "storagetreedefitem")
@NamedQueries({
  @NamedQuery(name = "Storagetreedefitem.findAll", query = "SELECT s FROM Storagetreedefitem s"),
  @NamedQuery(name = "Storagetreedefitem.findByStorageTreeDefItemID",
          query = "SELECT s FROM Storagetreedefitem s WHERE s.storageTreeDefItemID = :storageTreeDefItemID")})
public class Storagetreedefitem extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "StorageTreeDefItemID")
  private Integer storageTreeDefItemID;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 64)
  @Column(name = "Name")
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(name = "RankID")
  private int rankID;

  @Size(max = 64)
  @Column(name = "Title")
  private String title;

  @JoinColumn(name = "ParentItemID", referencedColumnName = "StorageTreeDefItemID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Storagetreedefitem parentItem;

  @JoinColumn(name = "StorageTreeDefID", referencedColumnName = "StorageTreeDefID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Storagetreedef storageTreeDef;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "storageTreeDefItem", fetch = FetchType.LAZY)
  private List<Storage> storages;

  public Storagetreedefitem() {
  }

  public Storagetreedefitem(Integer storageTreeDefItemID) {
    this.storageTreeDefItemID = storageTreeDefItemID;
  }

  public Storagetreedefitem(Integer storageTreeDefItemID, Date timestampCreated, String name, int rankID) {
    this.storageTreeDefItemID = storageTreeDefItemID;
    this.timestampCreated = timestampCreated;
    this.name = name;
    this.rankID = rankID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(storageTreeDefItemID);
  }

  @Override
  public int getEntityId() {
    return storageTreeDefItemID;
  }

  public Integer getStorageTreeDefItemID() {
    return storageTreeDefItemID;
  }

  public void setStorageTreeDefItemID(Integer storageTreeDefItemID) {
    this.storageTreeDefItemID = storageTreeDefItemID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRankID() {
    return rankID;
  }

  public void setRankID(int rankID) {
    this.rankID = rankID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Storagetreedefitem getParentItemID() {
    return parentItem;
  }

  public void setParentItem(Storagetreedefitem parentItem) {
    this.parentItem = parentItem;
  }

  public Storagetreedef getStorageTreeDef() {
    return storageTreeDef;
  }

  public void setStorageTreeDef(Storagetreedef storageTreeDef) {
    this.storageTreeDef = storageTreeDef;
  }

  public List<Storage> getStorages() {
    return storages;
  }

  public void setStorages(List<Storage> storages) {
    this.storages = storages;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (storageTreeDefItemID != null ? storageTreeDefItemID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Storagetreedefitem)) {
      return false;
    }
    Storagetreedefitem other = (Storagetreedefitem) object;
    return !((this.storageTreeDefItemID == null && other.storageTreeDefItemID != null) || (this.storageTreeDefItemID != null && !this.storageTreeDefItemID.equals(other.storageTreeDefItemID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Storagetreedefitem[ storageTreeDefItemID=" + storageTreeDefItemID + " ]";
  }
}

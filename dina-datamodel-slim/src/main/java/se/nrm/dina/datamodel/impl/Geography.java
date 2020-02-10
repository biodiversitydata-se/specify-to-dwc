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
@Table(name = "geography")
@NamedQueries({
  @NamedQuery(name = "Geography.findAll", query = "SELECT g FROM Geography g"),
  @NamedQuery(name = "Geography.findByGeographyID", query = "SELECT g FROM Geography g WHERE g.geographyID = :geographyID"),
  @NamedQuery(name = "Geography.findByCommonName", query = "SELECT g FROM Geography g WHERE g.commonName = :commonName"),
  @NamedQuery(name = "Geography.findByFullName", query = "SELECT g FROM Geography g WHERE g.fullName = :fullName"),
  @NamedQuery(name = "Geography.findByIsAccepted", query = "SELECT g FROM Geography g WHERE g.isAccepted = :isAccepted"),
  @NamedQuery(name = "Geography.findByIsCurrent", query = "SELECT g FROM Geography g WHERE g.isCurrent = :isCurrent"),
  @NamedQuery(name = "Geography.findByName", query = "SELECT g FROM Geography g WHERE g.name = :name")})
public class Geography extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "GeographyID")
  private Integer geographyID;

  @Size(max = 128)
  @Column(name = "CommonName")
  private String commonName;

  @Size(max = 255)
  @Column(name = "FullName")
  private String fullName;

  @Size(max = 24)
  @Column(name = "GeographyCode")
  private String geographyCode;

  @Column(name = "HighestChildNodeNumber")
  private Integer highestChildNodeNumber;

  @Column(name = "IsAccepted")
  private Boolean isAccepted;

  @Column(name = "IsCurrent")
  private Boolean isCurrent;

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
  private int rank;
 
  @JoinColumn(name = "ParentID", referencedColumnName = "GeographyID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Geography parent;

  @JoinColumn(name = "GeographyTreeDefID", referencedColumnName = "GeographyTreeDefID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Geographytreedef geographyTreeDef;

  @JoinColumn(name = "GeographyTreeDefItemID", referencedColumnName = "GeographyTreeDefItemID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Geographytreedefitem geographyTreeDefItem;

  @JoinColumn(name = "AcceptedID", referencedColumnName = "GeographyID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Geography accepted;
   
  public Geography() {
  }

  public Geography(Integer geographyID) {
    this.geographyID = geographyID;
  }

  public Geography(Integer geographyID, Date timestampCreated, String name, int rank) {
    this.geographyID = geographyID;
    this.timestampCreated = timestampCreated;
    this.name = name;
    this.rank = rank;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(geographyID);
  }

  @Override
  public int getEntityId() {
    return geographyID;
  }

  public Integer getGeographyID() {
    return geographyID;
  }

  public void setGeographyID(Integer geographyID) {
    this.geographyID = geographyID;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getGeographyCode() {
    return geographyCode;
  }

  public void setGeographyCode(String geographyCode) {
    this.geographyCode = geographyCode;
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

  public Boolean getIsCurrent() {
    return isCurrent;
  }

  public void setIsCurrent(Boolean isCurrent) {
    this.isCurrent = isCurrent;
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

  public int getRank() {
    return rank;
  }

  public void setRankID(int rank) {
    this.rank = rank;
  }
 
  public Geography getParent() {
    return parent;
  }

  public void setParentID(Geography parent) {
    this.parent = parent;
  }

  public Geographytreedef getGeographyTreeDef() {
    return geographyTreeDef;
  }

  public void setGeographyTreeDef(Geographytreedef geographyTreeDef) {
    this.geographyTreeDef = geographyTreeDef;
  }

  public Geographytreedefitem getGeographyTreeDefItem() {
    return geographyTreeDefItem;
  }

  public void setGeographyTreeDefItem(Geographytreedefitem geographyTreeDefItem) {
    this.geographyTreeDefItem = geographyTreeDefItem;
  }

  public Geography getAccepted() {
    return accepted;
  }

  public void setAccepted(Geography accepted) {
    this.accepted = accepted;
  }

  public String getCounty() { 
    if (rank == 400) {
      return name;
    }
    if (rank > 400) {
      Geography newParent = parent;
      while (newParent.getRank() > 400) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 400) {
        return newParent.getName();
      }
    }
    return null;
  }

  public String getState() {
    if (rank == 300) {
      return name;
    }
    if (rank > 300) {
      Geography newParent = parent;
      while (newParent.getRank() > 300) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 300) {
        return newParent.getName();
      }
    }
    return null;
  }

  public String getCountry() {
    if (rank == 200) {
      return name;
    }
    if (rank > 200) {
      Geography newParent = parent;
      while (newParent.getRank() > 200) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 200) {
        return newParent.getName();
      }
    }
    return null;
  }
   
  public String getContinent() {
    if (rank == 100) {
      return name;
    }
    if (rank > 100) {
      Geography newParent = parent;
      while (newParent.getRank() > 100) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 100) {
        return newParent.getName();
      }
    }
    return null;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (geographyID != null ? geographyID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Geography)) {
      return false;
    }
    Geography other = (Geography) object;
    return !((this.geographyID == null && other.geographyID != null)
            || (this.geographyID != null && !this.geographyID.equals(other.geographyID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Geography[ geographyID=" + geographyID + " ]";
  }
}

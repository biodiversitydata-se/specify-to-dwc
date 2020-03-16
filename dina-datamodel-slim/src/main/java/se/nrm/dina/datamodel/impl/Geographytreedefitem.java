package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "geographytreedefitem")
@NamedQueries({
  @NamedQuery(name = "Geographytreedefitem.findAll", query = "SELECT g FROM Geographytreedefitem g"),
  @NamedQuery(name = "Geographytreedefitem.findByGeographyTreeDefItemID", 
          query = "SELECT g FROM Geographytreedefitem g WHERE g.geographyTreeDefItemID = :geographyTreeDefItemID"),
  @NamedQuery(name = "Geographytreedefitem.findByName", query = "SELECT g FROM Geographytreedefitem g WHERE g.name = :name"),
  @NamedQuery(name = "Geographytreedefitem.findByRankID", query = "SELECT g FROM Geographytreedefitem g WHERE g.rankID = :rankID"),
  @NamedQuery(name = "Geographytreedefitem.findByTitle", query = "SELECT g FROM Geographytreedefitem g WHERE g.title = :title")})
public class Geographytreedefitem extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "GeographyTreeDefItemID")
  private Integer geographyTreeDefItemID;

  @Size(max = 32)
  @Column(name = "FullNameSeparator")
  private String fullNameSeparator;

  @Column(name = "IsEnforced")
  private Boolean isEnforced;

  @Column(name = "IsInFullName")
  private Boolean isInFullName;

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

  @OneToMany(mappedBy = "parentItem", fetch = FetchType.LAZY)
  private List<Geographytreedefitem> geographytreedefitemList;

  @JoinColumn(name = "ParentItem", referencedColumnName = "GeographyTreeDefItemID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Geographytreedefitem parentItem;

  @JoinColumn(name = "GeographyTreeDefID", referencedColumnName = "GeographyTreeDefID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Geographytreedef geographyTreeDef;

  public Geographytreedefitem() {
  }

  public Geographytreedefitem(Integer geographyTreeDefItemID) {
    this.geographyTreeDefItemID = geographyTreeDefItemID;
  }

  public Geographytreedefitem(Integer geographyTreeDefItemID, Date timestampCreated, String name, int rankID) {
    this.geographyTreeDefItemID = geographyTreeDefItemID;
    this.timestampCreated = timestampCreated;
    this.name = name;
    this.rankID = rankID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(geographyTreeDefItemID);
  }

  @Override
  public int getEntityId() {
    return geographyTreeDefItemID;
  }

  public Integer getGeographyTreeDefItemID() {
    return geographyTreeDefItemID;
  }

  public void setGeographyTreeDefItemID(Integer geographyTreeDefItemID) {
    this.geographyTreeDefItemID = geographyTreeDefItemID;
  }

  public String getFullNameSeparator() {
    return fullNameSeparator;
  }

  public void setFullNameSeparator(String fullNameSeparator) {
    this.fullNameSeparator = fullNameSeparator;
  }

  public Boolean getIsEnforced() {
    return isEnforced;
  }

  public void setIsEnforced(Boolean isEnforced) {
    this.isEnforced = isEnforced;
  }

  public Boolean getIsInFullName() {
    return isInFullName;
  }

  public void setIsInFullName(Boolean isInFullName) {
    this.isInFullName = isInFullName;
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

  public List<Geographytreedefitem> getGeographytreedefitemList() {
    return geographytreedefitemList;
  }

  public void setGeographytreedefitemList(List<Geographytreedefitem> geographytreedefitemList) {
    this.geographytreedefitemList = geographytreedefitemList;
  }

  public Geographytreedefitem getParentItem() {
    return parentItem;
  }

  public void setParentItem(Geographytreedefitem parentItem) {
    this.parentItem = parentItem;
  }

  public Geographytreedef getGeographyTreeDef() {
    return geographyTreeDef;
  }

  public void setGeographyTreeDef(Geographytreedef geographyTreeDef) {
    this.geographyTreeDef = geographyTreeDef;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (geographyTreeDefItemID != null ? geographyTreeDefItemID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Geographytreedefitem)) {
      return false;
    }
    Geographytreedefitem other = (Geographytreedefitem) object;
    return !((this.geographyTreeDefItemID == null && other.geographyTreeDefItemID != null)
            || (this.geographyTreeDefItemID != null && !this.geographyTreeDefItemID.equals(other.geographyTreeDefItemID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Geographytreedefitem[ geographyTreeDefItemID=" + geographyTreeDefItemID + " ]";
  }
}

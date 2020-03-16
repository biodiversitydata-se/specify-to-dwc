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
@Table(name = "taxontreedefitem")
@NamedQueries({
  @NamedQuery(name = "Taxontreedefitem.findAll", query = "SELECT t FROM Taxontreedefitem t"),
  @NamedQuery(name = "Taxontreedefitem.findByTaxonTreeDefItemID",
          query = "SELECT t FROM Taxontreedefitem t WHERE t.taxonTreeDefItemID = :taxonTreeDefItemID")})
public class Taxontreedefitem extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "TaxonTreeDefItemID")
  private Integer taxonTreeDefItemID;

  @Size(max = 32)
  @Column(name = "FormatToken")
  private String formatToken;

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

  @JoinColumn(name = "ParentItemID", referencedColumnName = "TaxonTreeDefItemID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Taxontreedefitem parentItem;

  @JoinColumn(name = "TaxonTreeDefID", referencedColumnName = "TaxonTreeDefID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Taxontreedef taxonTreeDef;

  public Taxontreedefitem() {
  }

  public Taxontreedefitem(Integer taxonTreeDefItemID) {
    this.taxonTreeDefItemID = taxonTreeDefItemID;
  }

  public Taxontreedefitem(Integer taxonTreeDefItemID, Date timestampCreated, String name, int rankID) {
    this.taxonTreeDefItemID = taxonTreeDefItemID;
    this.timestampCreated = timestampCreated;
    this.name = name;
    this.rankID = rankID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(taxonTreeDefItemID);
  }

  @Override
  public int getEntityId() {
    return taxonTreeDefItemID;
  }

  public Integer getTaxonTreeDefItemID() {
    return taxonTreeDefItemID;
  }

  public void setTaxonTreeDefItemID(Integer taxonTreeDefItemID) {
    this.taxonTreeDefItemID = taxonTreeDefItemID;
  }

  public String getFormatToken() {
    return formatToken;
  }

  public void setFormatToken(String formatToken) {
    this.formatToken = formatToken;
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

  public Taxontreedefitem getParentItem() {
    return parentItem;
  }

  public void setParentItemID(Taxontreedefitem parentItem) {
    this.parentItem = parentItem;
  }

  public Taxontreedef getTaxonTreeDef() {
    return taxonTreeDef;
  }

  public void setTaxonTreeDef(Taxontreedef taxonTreeDef) {
    this.taxonTreeDef = taxonTreeDef;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (taxonTreeDefItemID != null ? taxonTreeDefItemID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Taxontreedefitem)) {
      return false;
    }
    Taxontreedefitem other = (Taxontreedefitem) object;
    return !((this.taxonTreeDefItemID == null && other.taxonTreeDefItemID != null) || (this.taxonTreeDefItemID != null && !this.taxonTreeDefItemID.equals(other.taxonTreeDefItemID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Taxontreedefitem[ taxonTreeDefItemID=" + taxonTreeDefItemID + " ]";
  }
}

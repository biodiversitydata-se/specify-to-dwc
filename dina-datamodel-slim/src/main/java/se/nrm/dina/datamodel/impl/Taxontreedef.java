package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "taxontreedef")
@NamedQueries({
  @NamedQuery(name = "Taxontreedef.findAll", query = "SELECT t FROM Taxontreedef t"),
  @NamedQuery(name = "Taxontreedef.findByTaxonTreeDefID", query = "SELECT t FROM Taxontreedef t WHERE t.taxonTreeDefID = :taxonTreeDefID"),
  @NamedQuery(name = "Taxontreedef.findByFullNameDirection", query = "SELECT t FROM Taxontreedef t WHERE t.fullNameDirection = :fullNameDirection"),
  @NamedQuery(name = "Taxontreedef.findByName", query = "SELECT t FROM Taxontreedef t WHERE t.name = :name")})
public class Taxontreedef extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "TaxonTreeDefID")
  private Integer taxonTreeDefID;

  @Column(name = "FullNameDirection")
  private Integer fullNameDirection;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 64)
  @Column(name = "Name")
  private String name;
 
  public Taxontreedef() {
  }

  public Taxontreedef(Integer taxonTreeDefID) {
    this.taxonTreeDefID = taxonTreeDefID;
  }

  public Taxontreedef(Integer taxonTreeDefID, Date timestampCreated, String name) {
    this.taxonTreeDefID = taxonTreeDefID;
    this.timestampCreated = timestampCreated;
    this.name = name;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(taxonTreeDefID);
  }

  @Override
  public int getEntityId() {
    return taxonTreeDefID;
  }

  public Integer getTaxonTreeDefID() {
    return taxonTreeDefID;
  }

  public void setTaxonTreeDefID(Integer taxonTreeDefID) {
    this.taxonTreeDefID = taxonTreeDefID;
  }

  public Integer getFullNameDirection() {
    return fullNameDirection;
  }

  public void setFullNameDirection(Integer fullNameDirection) {
    this.fullNameDirection = fullNameDirection;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
 
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (taxonTreeDefID != null ? taxonTreeDefID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Taxontreedef)) {
      return false;
    }
    Taxontreedef other = (Taxontreedef) object;
    return !((this.taxonTreeDefID == null && other.taxonTreeDefID != null) || (this.taxonTreeDefID != null && !this.taxonTreeDefID.equals(other.taxonTreeDefID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Taxontreedef[ taxonTreeDefID=" + taxonTreeDefID + " ]";
  }
}

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
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "commonnametx")
@NamedQueries({
  @NamedQuery(name = "Commonnametx.findAll", query = "SELECT c FROM Commonnametx c"),
  @NamedQuery(name = "Commonnametx.findByCommonNameTxID", query = "SELECT c FROM Commonnametx c WHERE c.commonNameTxID = :commonNameTxID"),
  @NamedQuery(name = "Commonnametx.findByAuthor", query = "SELECT c FROM Commonnametx c WHERE c.author = :author"),
  @NamedQuery(name = "Commonnametx.findByCountry", query = "SELECT c FROM Commonnametx c WHERE c.country = :country"),
  @NamedQuery(name = "Commonnametx.findByLanguage", query = "SELECT c FROM Commonnametx c WHERE c.language = :language"),
  @NamedQuery(name = "Commonnametx.findByName", query = "SELECT c FROM Commonnametx c WHERE c.name = :name")})
public class Commonnametx extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "CommonNameTxID")
  private Integer commonNameTxID;

  @Size(max = 128)
  @Column(name = "Author")
  private String author;

  @Size(max = 2)
  @Column(name = "Country")
  private String country;

  @Size(max = 2)
  @Column(name = "Language")
  private String language;

  @Size(max = 255)
  @Column(name = "Name")
  private String name;

  @JoinColumn(name = "TaxonID", referencedColumnName = "TaxonID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Taxon taxon;

  public Commonnametx() {
  }

  public Commonnametx(Integer commonNameTxID) {
    this.commonNameTxID = commonNameTxID;
  }

  public Commonnametx(Integer commonNameTxID, Date timestampCreated) {
    this.commonNameTxID = commonNameTxID;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(commonNameTxID);
  }

  @Override
  public int getEntityId() {
    return commonNameTxID;
  }

  public Integer getCommonNameTxID() {
    return commonNameTxID;
  }

  public void setCommonNameTxID(Integer commonNameTxID) {
    this.commonNameTxID = commonNameTxID;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Taxon getTaxon() {
    return taxon;
  }

  public void setTaxon(Taxon taxon) {
    this.taxon = taxon;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (commonNameTxID != null ? commonNameTxID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Commonnametx)) {
      return false;
    }
    Commonnametx other = (Commonnametx) object;
    return !((this.commonNameTxID == null && other.commonNameTxID != null)
            || (this.commonNameTxID != null && !this.commonNameTxID.equals(other.commonNameTxID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Commonnametx[ commonNameTxID=" + commonNameTxID + " ]";
  }
}

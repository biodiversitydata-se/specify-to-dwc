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
@Table(name = "taxonattachment")
@NamedQueries({
  @NamedQuery(name = "Taxonattachment.findAll", query = "SELECT t FROM Taxonattachment t"),
  @NamedQuery(name = "Taxonattachment.findByTaxonAttachmentID",
          query = "SELECT t FROM Taxonattachment t WHERE t.taxonAttachmentID = :taxonAttachmentID"),
  @NamedQuery(name = "Taxonattachment.findByOrdinal", query = "SELECT t FROM Taxonattachment t WHERE t.ordinal = :ordinal")})
public class Taxonattachment extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "TaxonAttachmentID")
  private Integer taxonAttachmentID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "Ordinal")
  private int ordinal;
 
  @JoinColumn(name = "TaxonID", referencedColumnName = "TaxonID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Taxon taxon;

  @JoinColumn(name = "AttachmentID", referencedColumnName = "AttachmentID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Attachment attachment;

  public Taxonattachment() {
  }

  public Taxonattachment(Integer taxonAttachmentID) {
    this.taxonAttachmentID = taxonAttachmentID;
  }

  public Taxonattachment(Integer taxonAttachmentID, Date timestampCreated, int ordinal) {
    this.taxonAttachmentID = taxonAttachmentID;
    this.timestampCreated = timestampCreated;
    this.ordinal = ordinal;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(taxonAttachmentID);
  }

  @Override
  public int getEntityId() {
    return taxonAttachmentID;
  }

  public Integer getTaxonAttachmentID() {
    return taxonAttachmentID;
  }

  public void setTaxonAttachmentID(Integer taxonAttachmentID) {
    this.taxonAttachmentID = taxonAttachmentID;
  }

  public int getOrdinal() {
    return ordinal;
  }

  public void setOrdinal(int ordinal) {
    this.ordinal = ordinal;
  }

//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
  public Taxon getTaxon() {
    return taxon;
  }

  public void setTaxon(Taxon taxon) {
    this.taxon = taxon;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (taxonAttachmentID != null ? taxonAttachmentID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Taxonattachment)) {
      return false;
    }
    Taxonattachment other = (Taxonattachment) object;
    return !((this.taxonAttachmentID == null && other.taxonAttachmentID != null) || (this.taxonAttachmentID != null && !this.taxonAttachmentID.equals(other.taxonAttachmentID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Taxonattachment[ taxonAttachmentID=" + taxonAttachmentID + " ]";
  }
}

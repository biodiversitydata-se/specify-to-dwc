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
@Table(name = "accessionattachment")
@NamedQueries({
  @NamedQuery(name = "Accessionattachment.findAll", query = "SELECT a FROM Accessionattachment a"),
  @NamedQuery(name = "Accessionattachment.findByAccessionAttachmentID", query = "SELECT a FROM Accessionattachment a WHERE a.accessionAttachmentID = :accessionAttachmentID")})
public class Accessionattachment extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "AccessionAttachmentID")
  private Integer accessionAttachmentID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "Ordinal")
  private int ordinal;

  @JoinColumn(name = "AccessionID", referencedColumnName = "AccessionID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Accession accession;

  @JoinColumn(name = "AttachmentID", referencedColumnName = "AttachmentID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Attachment attachment;

  public Accessionattachment() {
  }

  public Accessionattachment(Integer accessionAttachmentID) {
    this.accessionAttachmentID = accessionAttachmentID;
  }

  public Accessionattachment(Integer accessionAttachmentID, Date timestampCreated, int ordinal) {
    this.accessionAttachmentID = accessionAttachmentID;
    this.timestampCreated = timestampCreated;
    this.ordinal = ordinal;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(accessionAttachmentID);
  }

  @Override
  public int getEntityId() {
    return accessionAttachmentID;
  }

  public Integer getAccessionAttachmentID() {
    return accessionAttachmentID;
  }

  public void setAccessionAttachmentID(Integer accessionAttachmentID) {
    this.accessionAttachmentID = accessionAttachmentID;
  }

  public int getOrdinal() {
    return ordinal;
  }

  public void setOrdinal(int ordinal) {
    this.ordinal = ordinal;
  }

  public Accession getAccession() {
    return accession;
  }

  public void setAccessionID(Accession accession) {
    this.accession = accession;
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
    hash += (accessionAttachmentID != null ? accessionAttachmentID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Accessionattachment)) {
      return false;
    }
    Accessionattachment other = (Accessionattachment) object;
    return !((this.accessionAttachmentID == null && other.accessionAttachmentID != null)
            || (this.accessionAttachmentID != null && !this.accessionAttachmentID.equals(other.accessionAttachmentID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Accessionattachment[ accessionAttachmentID=" + accessionAttachmentID + " ]";
  }
}

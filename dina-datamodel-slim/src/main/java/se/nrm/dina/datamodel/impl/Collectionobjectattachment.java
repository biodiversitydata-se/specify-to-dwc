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
import javax.validation.constraints.NotNull;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "collectionobjectattachment")
@NamedQueries({
  @NamedQuery(name = "Collectionobjectattachment.findAll", query = "SELECT c FROM Collectionobjectattachment c"),
  @NamedQuery(name = "Collectionobjectattachment.findByCollectionObjectAttachmentID", 
          query = "SELECT c FROM Collectionobjectattachment c WHERE c.collectionObjectAttachmentID = :collectionObjectAttachmentID"),
  @NamedQuery(name = "Collectionobjectattachment.findByCollectionMemberID", query = "SELECT c FROM Collectionobjectattachment c WHERE c.collectionMemberID = :collectionMemberID"),
  @NamedQuery(name = "Collectionobjectattachment.findByOrdinal", query = "SELECT c FROM Collectionobjectattachment c WHERE c.ordinal = :ordinal")})
public class Collectionobjectattachment extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "CollectionObjectAttachmentID")
  private Integer collectionObjectAttachmentID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "CollectionMemberID")
  private int collectionMemberID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "Ordinal")
  private int ordinal;
 
  @JoinColumn(name = "CollectionObjectID", referencedColumnName = "CollectionObjectID")
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collectionobject collectionObject;

  @JoinColumn(name = "AttachmentID", referencedColumnName = "AttachmentID")
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Attachment attachment;

  public Collectionobjectattachment() {
  }

  public Collectionobjectattachment(Integer collectionObjectAttachmentID) {
    this.collectionObjectAttachmentID = collectionObjectAttachmentID;
  }

  public Collectionobjectattachment(Integer collectionObjectAttachmentID, Date timestampCreated, int collectionMemberID, int ordinal) {
    this.collectionObjectAttachmentID = collectionObjectAttachmentID;
    this.timestampCreated = timestampCreated;
    this.collectionMemberID = collectionMemberID;
    this.ordinal = ordinal;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(collectionObjectAttachmentID);
  }

  @Override
  public int getEntityId() {
    return collectionObjectAttachmentID;
  }

  public Integer getCollectionObjectAttachmentID() {
    return collectionObjectAttachmentID;
  }

  public void setCollectionObjectAttachmentID(Integer collectionObjectAttachmentID) {
    this.collectionObjectAttachmentID = collectionObjectAttachmentID;
  }

  public int getCollectionMemberID() {
    return collectionMemberID;
  }

  public void setCollectionMemberID(int collectionMemberID) {
    this.collectionMemberID = collectionMemberID;
  }

  public int getOrdinal() {
    return ordinal;
  }

  public void setOrdinal(int ordinal) {
    this.ordinal = ordinal;
  } 
  
  public Collectionobject getCollectionObject() {
    return collectionObject;
  }

  public void setCollectionObject(Collectionobject collectionObject) {
    this.collectionObject = collectionObject;
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
    hash += (collectionObjectAttachmentID != null ? collectionObjectAttachmentID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Collectionobjectattachment)) {
      return false;
    }
    Collectionobjectattachment other = (Collectionobjectattachment) object;
    return !((this.collectionObjectAttachmentID == null && other.collectionObjectAttachmentID != null)
            || (this.collectionObjectAttachmentID != null && !this.collectionObjectAttachmentID.equals(other.collectionObjectAttachmentID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collectionobjectattachment[ collectionObjectAttachmentID=" + collectionObjectAttachmentID + " ]";
  }
}

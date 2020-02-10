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
@Table(name = "attachment") 
@NamedQueries({
  @NamedQuery(name = "Attachment.findAll", query = "SELECT a FROM Attachment a"),
  @NamedQuery(name = "Attachment.findByAttachmentID", query = "SELECT a FROM Attachment a WHERE a.attachmentID = :attachmentID"),  
  @NamedQuery(name = "Attachment.findByTitle", query = "SELECT a FROM Attachment a WHERE a.title = :title") })
public class Attachment extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "AttachmentID")
  private Integer attachmentID;

  @Size(max = 20000)
  @Column(name = "origFilename")
  private String origFilename;
 
  @Size(max = 255)
  @Column(name = "title")
  private String title;

  @Basic(optional = false)
  @NotNull
  @Column(name = "IsPublic")
  private boolean isPublic;

  @JoinColumn(name = "AttachmentImageAttributeID", referencedColumnName = "AttachmentImageAttributeID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Attachmentimageattribute attachmentImageAttribute;

  public Attachment() {
  }

  public Attachmentimageattribute getAttachmentImageAttribute() {
    return attachmentImageAttribute;
  }

  public void setAttachmentImageAttribute(Attachmentimageattribute attachmentImageAttribute) {
    this.attachmentImageAttribute = attachmentImageAttribute;
  }

  public Attachment(Integer attachmentID) {
    this.attachmentID = attachmentID;
  }

  public Attachment(Integer attachmentID, Date timestampCreated) {
    this.attachmentID = attachmentID;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(attachmentID);
  }

  @Override
  public int getEntityId() {
    return attachmentID;
  }

  public Integer getAttachmentID() {
    return attachmentID;
  }

  public void setAttachmentID(Integer attachmentID) {
    this.attachmentID = attachmentID;
  }

  public String getOrigFilename() {
    return origFilename;
  }

  public void setOrigFilename(String origFilename) {
    this.origFilename = origFilename;
  }
 
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (attachmentID != null ? attachmentID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Attachment)) {
      return false;
    }
    Attachment other = (Attachment) object;
    return !((this.attachmentID == null && other.attachmentID != null)
            || (this.attachmentID != null && !this.attachmentID.equals(other.attachmentID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Attachment[ attachmentID=" + attachmentID + " ]";
  }
}

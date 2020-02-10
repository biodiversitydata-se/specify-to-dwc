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
@Table(name = "attachmentimageattribute") 
@NamedQueries({
  @NamedQuery(name = "Attachmentimageattribute.findAll", query = "SELECT a FROM Attachmentimageattribute a") })
public class Attachmentimageattribute extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "AttachmentImageAttributeID")
  private Integer attachmentImageAttributeID;

  @Column(name = "MBImageID")
  private Integer mBImageID;
 
  @JoinColumn(name = "MorphBankViewID", referencedColumnName = "MorphBankViewID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Morphbankview morphBankWiew;

  @Size(max = 80)
  @Column(name = "ImageType") 
  private String imageType;
 
  @Size(max = 80)
  @Column(name = "ViewDescription")
  private String viewDescription;

  public Attachmentimageattribute() {
  }

  public Attachmentimageattribute(Integer attachmentImageAttributeID) {
    this.attachmentImageAttributeID = attachmentImageAttributeID;
  }

  public Attachmentimageattribute(Integer attachmentImageAttributeID, Date timestampCreated) {
    this.attachmentImageAttributeID = attachmentImageAttributeID;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(attachmentImageAttributeID);
  }

  @Override
  public int getEntityId() {
    return attachmentImageAttributeID;
  }

  public Integer getAttachmentImageAttributeID() {
    return attachmentImageAttributeID;
  }

  public void setAttachmentImageAttributeID(Integer attachmentImageAttributeID) {
    this.attachmentImageAttributeID = attachmentImageAttributeID;
  }

  public Integer getMBImageID() {
    return mBImageID;
  }

  public void setMBImageID(Integer mBImageID) {
    this.mBImageID = mBImageID;
  }
 
  public Morphbankview getMorphBankWiew() {
    return morphBankWiew;
  }

  public void setMorphBankWiew(Morphbankview morphBankWiew) {
    this.morphBankWiew = morphBankWiew;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }
 
  public String getViewDescription() {
    return viewDescription;
  }

  public void setViewDescription(String viewDescription) {
    this.viewDescription = viewDescription;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (attachmentImageAttributeID != null ? attachmentImageAttributeID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Attachmentimageattribute)) {
      return false;
    }
    Attachmentimageattribute other = (Attachmentimageattribute) object;
    return !((this.attachmentImageAttributeID == null
            && other.attachmentImageAttributeID != null)
            || (this.attachmentImageAttributeID != null
            && !this.attachmentImageAttributeID.equals(other.attachmentImageAttributeID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Attachmentimageattribute[ attachmentImageAttributeID=" + attachmentImageAttributeID + " ]";
  }
}

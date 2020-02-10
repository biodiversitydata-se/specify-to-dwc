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
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "morphbankview")
@NamedQueries({
  @NamedQuery(name = "Morphbankview.findAll", query = "SELECT m FROM Morphbankview m")})
public class Morphbankview extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "MorphBankViewID")
  private Integer morphBankViewID;

  @Size(max = 128)
  @Column(name = "DevelopmentState")
  private String developmentState;

  @Size(max = 128)
  @Column(name = "Form")
  private String form;

  @Size(max = 128)
  @Column(name = "ImagingPreparationTechnique")
  private String imagingPreparationTechnique;

  @Size(max = 128)
  @Column(name = "ImagingTechnique")
  private String imagingTechnique;

  @Column(name = "MorphBankExternalViewID")
  private Integer morphBankExternalViewID;

  @Size(max = 32)
  @Column(name = "Sex")
  private String sex;

  @Size(max = 128)
  @Column(name = "SpecimenPart")
  private String specimenPart;

  @Size(max = 128)
  @Column(name = "ViewAngle")
  private String viewAngle;

  @Size(max = 128)
  @Column(name = "ViewName")
  private String viewName;
  
  public Morphbankview() {
  }

  public Morphbankview(Integer morphBankViewID) {
    this.morphBankViewID = morphBankViewID;
  }

  public Morphbankview(Integer morphBankViewID, Date timestampCreated) {
    this.morphBankViewID = morphBankViewID;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(morphBankViewID);
  }

  @Override
  public int getEntityId() {
    return morphBankViewID;
  }

  public Integer getMorphBankViewID() {
    return morphBankViewID;
  }

  public void setMorphBankViewID(Integer morphBankViewID) {
    this.morphBankViewID = morphBankViewID;
  }

  public String getDevelopmentState() {
    return developmentState;
  }

  public void setDevelopmentState(String developmentState) {
    this.developmentState = developmentState;
  }

  public String getForm() {
    return form;
  }

  public void setForm(String form) {
    this.form = form;
  }

  public String getImagingPreparationTechnique() {
    return imagingPreparationTechnique;
  }

  public void setImagingPreparationTechnique(String imagingPreparationTechnique) {
    this.imagingPreparationTechnique = imagingPreparationTechnique;
  }

  public String getImagingTechnique() {
    return imagingTechnique;
  }

  public void setImagingTechnique(String imagingTechnique) {
    this.imagingTechnique = imagingTechnique;
  }

  public Integer getMorphBankExternalViewID() {
    return morphBankExternalViewID;
  }

  public void setMorphBankExternalViewID(Integer morphBankExternalViewID) {
    this.morphBankExternalViewID = morphBankExternalViewID;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getSpecimenPart() {
    return specimenPart;
  }

  public void setSpecimenPart(String specimenPart) {
    this.specimenPart = specimenPart;
  }

  public String getViewAngle() {
    return viewAngle;
  }

  public void setViewAngle(String viewAngle) {
    this.viewAngle = viewAngle;
  }

  public String getViewName() {
    return viewName;
  }

  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (morphBankViewID != null ? morphBankViewID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Morphbankview)) {
      return false;
    }
    Morphbankview other = (Morphbankview) object;
    return !((this.morphBankViewID == null && other.morphBankViewID != null)
            || (this.morphBankViewID != null && !this.morphBankViewID.equals(other.morphBankViewID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Morphbankview[ morphBankViewID=" + morphBankViewID + " ]";
  }
}

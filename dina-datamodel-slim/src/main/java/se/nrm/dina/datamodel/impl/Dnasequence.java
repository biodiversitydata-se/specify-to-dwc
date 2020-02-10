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
import javax.persistence.Lob;
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
@Table(name = "dnasequence")
@NamedQueries({
  @NamedQuery(name = "Dnasequence.findAll", query = "SELECT d FROM Dnasequence d"),
  @NamedQuery(name = "Dnasequence.findByDnaSequenceID", query = "SELECT d FROM Dnasequence d WHERE d.dnaSequenceID = :dnaSequenceID")})
public class Dnasequence extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "DnaSequenceID")
  private Integer dnaSequenceID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "CollectionMemberID")
  private int collectionMemberID;

  @Size(max = 32)
  @Column(name = "BOLDBarcodeID")
  private String bOLDBarcodeID;

  @Size(max = 32)
  @Column(name = "BOLDSampleID")
  private String bOLDSampleID;

  @Column(name = "CompA")
  private Integer compA;

  @Column(name = "CompC")
  private Integer compC;

  @Column(name = "CompG")
  private Integer compG;

  @Column(name = "compT")
  private Integer compT;

  @Size(max = 32)
  @Column(name = "GenBankAccessionNumber")
  private String genBankAccessionNumber;

  @Lob
  @Size(max = 65535)
  @Column(name = "GeneSequence")
  private String geneSequence;

  @Size(max = 32)
  @Column(name = "MoleculeType")
  private String moleculeType;
 
  @Size(max = 32)
  @Column(name = "TargetMarker")
  private String targetMarker;

  @Column(name = "TotalResidues")
  private Integer totalResidues;

  @JoinColumn(name = "AgentID", referencedColumnName = "AgentID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Agent agent;

  @JoinColumn(name = "CollectionObjectID", referencedColumnName = "CollectionObjectID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Collectionobject collectionObject;

  public Dnasequence() {
  }

  public Dnasequence(Integer dnaSequenceID) {
    this.dnaSequenceID = dnaSequenceID;
  }

  public Dnasequence(Integer dnaSequenceID, Date timestampCreated, int collectionMemberID) {
    this.dnaSequenceID = dnaSequenceID;
    this.timestampCreated = timestampCreated;
    this.collectionMemberID = collectionMemberID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(dnaSequenceID);
  }

  @Override
  public int getEntityId() {
    return dnaSequenceID;
  }

  public Integer getDnaSequenceID() {
    return dnaSequenceID;
  }

  public void setDnaSequenceID(Integer dnaSequenceID) {
    this.dnaSequenceID = dnaSequenceID;
  }

  public int getCollectionMemberID() {
    return collectionMemberID;
  }

  public void setCollectionMemberID(int collectionMemberID) {
    this.collectionMemberID = collectionMemberID;
  }

  public String getBOLDBarcodeID() {
    return bOLDBarcodeID;
  }

  public void setBOLDBarcodeID(String bOLDBarcodeID) {
    this.bOLDBarcodeID = bOLDBarcodeID;
  }

  public String getBOLDSampleID() {
    return bOLDSampleID;
  }

  public void setBOLDSampleID(String bOLDSampleID) {
    this.bOLDSampleID = bOLDSampleID;
  }

  public Integer getCompA() {
    return compA;
  }

  public void setCompA(Integer compA) {
    this.compA = compA;
  }

  public Integer getCompC() {
    return compC;
  }

  public void setCompC(Integer compC) {
    this.compC = compC;
  }

  public Integer getCompG() {
    return compG;
  }

  public void setCompG(Integer compG) {
    this.compG = compG;
  }

  public Integer getCompT() {
    return compT;
  }

  public void setCompT(Integer compT) {
    this.compT = compT;
  }

  public String getGenBankAccessionNumber() {
    return genBankAccessionNumber;
  }

  public void setGenBankAccessionNumber(String genBankAccessionNumber) {
    this.genBankAccessionNumber = genBankAccessionNumber;
  }

  public String getGeneSequence() {
    return geneSequence;
  }

  public void setGeneSequence(String geneSequence) {
    this.geneSequence = geneSequence;
  }

  public String getMoleculeType() {
    return moleculeType;
  }

  public void setMoleculeType(String moleculeType) {
    this.moleculeType = moleculeType;
  }
 
  public String getTargetMarker() {
    return targetMarker;
  }

  public void setTargetMarker(String targetMarker) {
    this.targetMarker = targetMarker;
  }

  public Integer getTotalResidues() {
    return totalResidues;
  }

  public void setTotalResidues(Integer totalResidues) {
    this.totalResidues = totalResidues;
  }

  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public Collectionobject getCollectionObject() {
    return collectionObject;
  }

  public void setCollectionObject(Collectionobject collectionObject) {
    this.collectionObject = collectionObject;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (dnaSequenceID != null ? dnaSequenceID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Dnasequence)) {
      return false;
    }
    Dnasequence other = (Dnasequence) object;
    return !((this.dnaSequenceID == null && other.dnaSequenceID != null)
            || (this.dnaSequenceID != null && !this.dnaSequenceID.equals(other.dnaSequenceID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Dnasequence[ dnaSequenceID=" + dnaSequenceID + " ]";
  }
}

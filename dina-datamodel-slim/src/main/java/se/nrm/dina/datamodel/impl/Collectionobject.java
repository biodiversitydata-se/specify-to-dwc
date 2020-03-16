package se.nrm.dina.datamodel.impl;

import java.util.Date;
import se.nrm.dina.datamodel.BaseEntity;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "collectionobject",
        uniqueConstraints = @UniqueConstraint(columnNames = {"collectionID", "CatalogNumber"}))
@NamedQueries({
  @NamedQuery(name = "Collectionobject.findAll", query = "SELECT c FROM Collectionobject c"),
  @NamedQuery(name = "Collectionobject.findByCollectionMemberId",
          query = "SELECT c FROM Collectionobject c where c.collectionMemberID = :collectionMemberID"),
  @NamedQuery(name = "Collectionobject.findAllIds",
          query = "SELECT c.collectionObjectID FROM Collectionobject c where c.collectionMemberID = :collectionMemberID ORDER BY c.collectionObjectID"),
  @NamedQuery(name = "Collectionobject.findCount", query = "SELECT COUNT(c) FROM Collectionobject c where c.collectionMemberID = :collectionMemberID"),
  @NamedQuery(name = "Collectionobject.findByCollectionObjectID", query = "SELECT c FROM Collectionobject c WHERE c.collectionObjectID = :collectionObjectID"),
  @NamedQuery(name = "collectionobject.findByCurrentDetermination",
          query = "SELECT c FROM Collectionobject c JOIN c.determinations d where d.collectionObject = c AND d.isCurrent = true"),
  @NamedQuery(name = "Collectionobject.findByDeaccessionedAndCollectingEvent",
          query = "SELECT c FROM Collectionobject c JOIN c.determinations d where d.collectionObject = c "
          + "AND d.isCurrent = true AND d.taxon.fullName = :fullName "
          + "AND c.collectingEvent.stationFieldNumber = :stationFieldNumber "
          + "AND c.collectionMemberID = :collectionMemberID")})
public class Collectionobject extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
//    @NotNull
  @Column(name = "CollectionObjectID")
  private Integer collectionObjectID;

  @Basic(optional = false)
  @NotNull
  @Min(value = 1, message = "collectionMemberID can not be null")
  @Column(name = "CollectionMemberID")
  private int collectionMemberID;

  @Size(max = 32)
  @Column(name = "AltCatalogNumber")
  private String altCatalogNumber; 
  
  @Size(max = 32)
  @Column(name = "CatalogNumber")
  private String catalogNumber;

  @Column(name = "CatalogedDate")
  @Temporal(TemporalType.DATE)
  private Date catalogedDate;

  @Column(name = "Deaccessioned")
  private Boolean deaccessioned;

  @Size(max = 255)
  @Column(name = "Description")
  private String description;

  @Size(max = 50)
  @Column(name = "FieldNumber")
  private String fieldNumber;
  
  @Size(max = 128)
  @Column(name = "GUID")
  private String guid;

  @Lob
  @Size(max = 65535)
  @Column(name = "Remarks")
  private String remarks;
  
  @Column(name = "CountAmt")
  private Integer countAmt;
 
  @Lob
  @Size(max = 65535)
  @Column(name = "Text1")
  private String text1;

  @Size(max = 128)
  @Column(name = "ReservedText")
  private String reservedText;

  @Size(max = 128)
  @Column(name = "ReservedText2")
  private String reservedText2;

  @Size(max = 128)
  @Column(name = "ReservedText3")
  private String reservedText3;

  @Column(name = "TimestampModified")
  @Temporal(TemporalType.TIMESTAMP)
  protected Date timestampModified;

  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH},
          orphanRemoval = true, mappedBy = "collectionObject",
          fetch = FetchType.LAZY)
  private Set<Determination> determinations;

  @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
          orphanRemoval = true, mappedBy = "collectionObject",
          fetch = FetchType.LAZY)
  private Set<Preparation> preparations;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "collectionObject", fetch = FetchType.LAZY)
  private Set<Collectionobjectattachment> collectionobjectattachments;

  @JoinColumn(name = "AccessionID", referencedColumnName = "AccessionID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Accession accession;

  @JoinColumn(name = "CatalogerID", referencedColumnName = "AgentID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Agent cataloger;

  @JoinColumn(name = "CollectionID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @NotNull(message = "collectionID can not be null")
  private Collection collection;

  @JoinColumn(name = "CollectingEventID", referencedColumnName = "CollectingEventID")
  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Collectingevent collectingEvent;

  @JoinColumn(name = "CollectionObjectAttributeID", referencedColumnName = "CollectionObjectAttributeID")
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collectionobjectattribute collectionObjectAttribute;

  @OneToMany(mappedBy = "collectionObject", fetch = FetchType.LAZY)
  private Set<Dnasequence> dnasequences;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "collectionObject", fetch = FetchType.LAZY)
  private List<Collectionobjectcitation> collectionobjectcitations;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "collectionObject", fetch = FetchType.LAZY)
  private List<Collectionobjectattr> collectionobjectattrs;

  public Collectionobject() {
  }

  public Collectionobject(Integer collectionObjectID) {
    this.collectionObjectID = collectionObjectID;
  }

  public Collectionobject(Integer collectionObjectID, Date timestampCreated, int collectionMemberID) {
    this.collectionObjectID = collectionObjectID;
    this.timestampCreated = timestampCreated;
    this.collectionMemberID = collectionMemberID;
  }

  @Override
  public String getIdentityString() {
    return guid;
//    return String.valueOf(collectionObjectID);
  }

  @Override
  public int getEntityId() {
    return collectionObjectID == null ? 0 : collectionObjectID;
  }

  public Integer getCollectionObjectID() {
    return collectionObjectID;
  }

  public void setCollectionObjectID(Integer collectionObjectID) {
    this.collectionObjectID = collectionObjectID;
  }

  public int getCollectionMemberID() {
    return collectionMemberID;
  }

  public void setCollectionMemberID(int collectionMemberID) {
    this.collectionMemberID = collectionMemberID;
  }

  public String getAltCatalogNumber() {
    return altCatalogNumber;
  }

  public void setAltCatalogNumber(String altCatalogNumber) {
    this.altCatalogNumber = altCatalogNumber;
  }

  public Integer getCountAmt() {
    return countAmt;
  }

  public void setCountAmt(Integer countAmt) {
    this.countAmt = countAmt;
  }


  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public Date getCatalogedDate() {
    return catalogedDate;
  }

  public void setCatalogedDate(Date catalogedDate) {
    this.catalogedDate = catalogedDate;
  }

  public Boolean getDeaccessioned() {
    return deaccessioned;
  }

  public void setDeaccessioned(Boolean deaccessioned) {
    this.deaccessioned = deaccessioned;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFieldNumber() {
    return fieldNumber;
  }

  public void setFieldNumber(String fieldNumber) {
    this.fieldNumber = fieldNumber;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
  
  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }
 
  public String getText1() {
    return text1;
  }

  public void setText1(String text1) {
    this.text1 = text1;
  }

  public String getReservedText() {
    return reservedText;
  }

  public void setReservedText(String reservedText) {
    this.reservedText = reservedText;
  }

  public String getReservedText2() {
    return reservedText2;
  }

  public void setReservedText2(String reservedText2) {
    this.reservedText2 = reservedText2;
  }

  public String getReservedText3() {
    return reservedText3;
  }

  public Date getTimestampModified() {
    return timestampModified;
  }

  public void setTimestampModified(Date timestampModified) {
    this.timestampModified = timestampModified;
  }

  public void setReservedText3(String reservedText3) {
    this.reservedText3 = reservedText3;
  }

  public Set<Determination> getDeterminations() {
    return determinations;
  }

  public void setDeterminationList(Set<Determination> determinations) {
    this.determinations = determinations;
  }

  public Set<Preparation> getPreparations() {
    return preparations;
  }

  public void setPreparations(Set<Preparation> preparations) {
    this.preparations = preparations;
  }

  public Set<Collectionobjectattachment> getCollectionobjectattachments() {
    return collectionobjectattachments;
  }

  public void setCollectionobjectattachments(Set<Collectionobjectattachment> collectionobjectattachments) {
    this.collectionobjectattachments = collectionobjectattachments;
  }

  public Accession getAccession() {
    return accession;
  }

  public void setAccession(Accession accession) {
    this.accession = accession;
  }

  public Agent getCataloger() {
    return cataloger;
  }

  public void setCataloger(Agent cataloger) {
    this.cataloger = cataloger;
  }

  public Collection getCollection() {
    return collection;
  }

  public void setCollection(Collection collection) {
    this.collection = collection;
  }

  public Collectingevent getCollectingEvent() {
    return collectingEvent;
  }

  public void setCollectingEvent(Collectingevent collectingEvent) {
    this.collectingEvent = collectingEvent;
  }

  public Collectionobjectattribute getCollectionObjectAttribute() {
    return collectionObjectAttribute;
  }

  public void setCollectionObjectAttributeID(Collectionobjectattribute collectionObjectAttribute) {
    this.collectionObjectAttribute = collectionObjectAttribute;
  }

  public Set<Dnasequence> getDnasequences() {
    return dnasequences;
  }

  public void setDnasequenceList(Set<Dnasequence> dnasequences) {
    this.dnasequences = dnasequences;
  }

  public List<Collectionobjectcitation> getCollectionobjectcitationList() {
    return collectionobjectcitations;
  }

  public void setCollectionobjectcitations(List<Collectionobjectcitation> collectionobjectcitations) {
    this.collectionobjectcitations = collectionobjectcitations;
  }

  public List<Collectionobjectattr> getCollectionobjectattrs() {
    return collectionobjectattrs;
  }

  public void setCollectionobjectattrs(List<Collectionobjectattr> collectionobjectattrs) {
    this.collectionobjectattrs = collectionobjectattrs;
  }

  public int getDnasequenceSize() {
    return dnasequences != null ? dnasequences.size() : 0;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (collectionObjectID != null ? collectionObjectID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Collectionobject)) {
      return false;
    }
    Collectionobject other = (Collectionobject) object;
    return !((this.collectionObjectID == null && other.collectionObjectID != null)
            || (this.collectionObjectID != null && !this.collectionObjectID.equals(other.collectionObjectID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collectionobject[ collectionObjectID=" + collectionObjectID + " ]";
  }
}

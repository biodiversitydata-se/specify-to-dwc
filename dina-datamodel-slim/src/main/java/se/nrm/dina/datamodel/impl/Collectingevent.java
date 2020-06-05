package se.nrm.dina.datamodel.impl;
 
import java.util.Calendar;
import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;  
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "collectingevent")
@NamedQueries({
  @NamedQuery(name = "Collectingevent.findAll", query = "SELECT c FROM Collectingevent c"), 
    @NamedQuery(name = "Collectingevent.findByEventId",
          query = "SELECT c FROM Collectingevent c where c.stationFieldNumber = :stationFieldNumber and c.discipline.userGroupScopeId = :userGroupScopeId and c.locality.localityID = :localityID"),
    @NamedQuery(name = "Collectingevent.findByCollectingEventID", query = "SELECT c FROM Collectingevent c WHERE c.collectingEventID = :collectingEventID"),
    @NamedQuery(name = "Collectingevent.findByEndDate", query = "SELECT c FROM Collectingevent c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "Collectingevent.findByStartDate", query = "SELECT c FROM Collectingevent c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Collectingevent.findByStationFieldNumber", query = "SELECT c FROM Collectingevent c WHERE c.stationFieldNumber = :stationFieldNumber")})
public class Collectingevent extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "CollectingEventID")
  private Integer collectingEventID;

  @Column(name = "EndDate")
  @Temporal(TemporalType.DATE)
  private Date endDate;
 
  @Lob
  @Size(max = 65535)
  @Column(name = "Remarks")
  private String remarks;

  @Column(name = "StartDate")
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @Size(max = 50)
  @Column(name = "StationFieldNumber")
  private String stationFieldNumber;

  @Column(name = "StartTime")
  private Short startTime;

  @Size(max = 50)
  @Column(name = "VerbatimDate")
  private String verbatimDate;
  
  @Lob
  @Size(max = 65535)
  @Column(name = "VerbatimLocality")
  private String verbatimLocality;

  @JoinColumn(name = "DisciplineID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Discipline discipline;

  @JoinColumn(name = "LocalityID", referencedColumnName = "LocalityID")
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Locality locality;

  @JoinColumn(name = "CollectingEventAttributeID", referencedColumnName = "CollectingEventAttributeID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Collectingeventattribute collectingEventAttribute;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "collectingEvent", fetch = FetchType.LAZY)
  private Set<Collector> collectors;

  @Transient
  private Calendar cal; 
 
  public Collectingevent() {  
  }

  public Collectingevent(Integer collectingEventID) {
    this.collectingEventID = collectingEventID;
  }

  public Collectingevent(Integer collectingEventID, Date timestampCreated) {
    this.collectingEventID = collectingEventID;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(collectingEventID);
  }

  @Override
  public int getEntityId() {
    return collectingEventID;
  }

  public Integer getCollectingEventID() {
    return collectingEventID;
  }
   
  public void setCollectingEventID(Integer collectingEventID) {
    this.collectingEventID = collectingEventID;
  }
   
  public String getVerbatimDate() {
    return verbatimDate;
  }

  public void setVerbatimDate(String verbatimDate) {
    this.verbatimDate = verbatimDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  public int getStartDayOfYear() {
    cal = Calendar.getInstance();
    cal.setTime(startDate);
    return cal.get(Calendar.DAY_OF_YEAR); 
  }
  
  public int getEndDayOfYear() {
    cal = Calendar.getInstance();
    cal.setTime(endDate);
    return cal.get(Calendar.DAY_OF_YEAR);  
  }
 
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
  
  public String getVerbatimLocality() {
    return verbatimLocality;
  }

  public void setVerbatimLocality(String verbatimLocality) {
    this.verbatimLocality = verbatimLocality;
  }

  public Short getStartTime() {
    return startTime;
  }

  public void setStartTime(Short startTime) {
    this.startTime = startTime;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getStationFieldNumber() {
    return stationFieldNumber;
  }

  public void setStationFieldNumber(String stationFieldNumber) {
    this.stationFieldNumber = stationFieldNumber;
  }

  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  public Locality getLocality() {
    return locality;
  }

  public void setLocality(Locality locality) {
    this.locality = locality;
  }

  public Collectingeventattribute getCollectingEventAttribute() {
    return collectingEventAttribute;
  }

  public void setCollectingEventAttribute(Collectingeventattribute collectingEventAttribute) {
    this.collectingEventAttribute = collectingEventAttribute;
  }

  public Set<Collector> getCollectors() {
    return collectors;
  }

  public void setCollectors(Set<Collector> collectors) {
    this.collectors = collectors;
  }
   
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (collectingEventID != null ? collectingEventID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Collectingevent)) {
      return false;
    }
    Collectingevent other = (Collectingevent) object;
    return !((this.collectingEventID == null && other.collectingEventID != null)
            || (this.collectingEventID != null && !this.collectingEventID.equals(other.collectingEventID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collectingevent[ collectingEventID=" + collectingEventID + " ]";
  }
}

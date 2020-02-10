package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "locality")
@NamedQueries({
  @NamedQuery(name = "Locality.findAll", query = "SELECT l FROM Locality l"),
  @NamedQuery(name = "Locality.findByLocalityID", query = "SELECT l FROM Locality l WHERE l.localityID = :localityID")})
public class Locality extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "LocalityID")
  private Integer localityID;

  @Size(max = 50)
  @Column(name = "Datum")
  private String datum;
  
  @Size(max = 50)
  @Column(name = "Lat1Text")
  private String lat1Text;
 
  @Column(name = "LatLongAccuracy")
  private Double latLongAccuracy;
   
  @Column(name = "Latitude1")
  private BigDecimal latitude1;
 
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "LocalityName")
  private String localityName;

  @Size(max = 50)
  @Column(name = "Long1Text")
  private String long1Text;
 
  @Column(name = "Longitude1")
  private BigDecimal longitude1;
 
  @Column(name = "MaxElevation")
  private Double maxElevation;

  @Column(name = "MinElevation")
  private Double minElevation;
  
  @Size(max = 50)
  @Column(name = "VerbatimElevation")
  private String verbatimElevation;

  @Size(max = 255)
  @Column(name = "NamedPlace")
  private String namedPlace;

  @Lob
  @Size(max = 65535)
  @Column(name = "Remarks")
  private String remarks;

  @Size(max = 32)
  @Column(name = "ShortName")
  private String shortName;
 
  @JoinColumn(name = "DisciplineID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Discipline discipline;

  @JoinColumn(name = "GeographyID", referencedColumnName = "GeographyID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Geography geography;

  @OneToMany(mappedBy = "locality")
  private Set<Localitydetail> localitydetails;
  
  @OneToMany(mappedBy = "locality")
  private Set<Geocoorddetail> geocoorddetails;
    
  public Locality() {
  }

  public Locality(Integer localityID) {
    this.localityID = localityID;
  }

  public Locality(Integer localityID, Date timestampCreated, String localityName, short srcLatLongUnit) {
    this.localityID = localityID;
    this.timestampCreated = timestampCreated;
    this.localityName = localityName;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(localityID);
  }

  @Override
  public int getEntityId() {
    return localityID;
  }

  public Integer getLocalityID() {
    return localityID;
  }

  public void setLocalityID(Integer localityID) {
    this.localityID = localityID;
  }

  public String getDatum() {
    return datum;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }

  public String getLat1Text() {
    return lat1Text;
  }

  public void setLat1Text(String lat1Text) {
    this.lat1Text = lat1Text;
  }
 
  public Double getLatLongAccuracy() {
    return latLongAccuracy;
  }

  public void setLatLongAccuracy(Double latLongAccuracy) {
    this.latLongAccuracy = latLongAccuracy;
  }
 
  public BigDecimal getLatitude1() {
    return latitude1;
  }

  public void setLatitude1(BigDecimal latitude1) {
    this.latitude1 = latitude1;
  }
   
  public String getVerbatimElevation() {
    return verbatimElevation;
  }

  public void setVerbatimElevation(String verbatimElevation) {
    this.verbatimElevation = verbatimElevation;
  }
 
  public String getLocalityName() {
    return localityName;
  }

  public void setLocalityName(String localityName) {
    this.localityName = localityName;
  }

  public String getLong1Text() {
    return long1Text;
  }

  public void setLong1Text(String long1Text) {
    this.long1Text = long1Text;
  }
 
  public BigDecimal getLongitude1() {
    return longitude1;
  }

  public void setLongitude1(BigDecimal longitude1) {
    this.longitude1 = longitude1;
  }
 
  public Double getMaxElevation() {
    return maxElevation;
  }

  public void setMaxElevation(Double maxElevation) {
    this.maxElevation = maxElevation;
  }

  public Double getMinElevation() {
    return minElevation;
  }

  public void setMinElevation(Double minElevation) {
    this.minElevation = minElevation;
  }

  public String getNamedPlace() {
    return namedPlace;
  }

  public void setNamedPlace(String namedPlace) {
    this.namedPlace = namedPlace;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
  
    
  
  @XmlTransient
  public Set<Geocoorddetail> getGeocoorddetailSet() {
    return geocoorddetails;
  }

  public void setGeocoorddetailSet(Set<Geocoorddetail> geocoorddetails) {
    this.geocoorddetails = geocoorddetails;
  }
 
  @XmlTransient
  public Set<Localitydetail> getLocalitydetails() {
    return localitydetails;
  }

  public void setLocalitydetails(Set<Localitydetail> localitydetails) {
    this.localitydetails = localitydetails;
  }
   
  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  public Geography getGeography() {
    return geography;
  }

  public void setGeography(Geography geography) {
    this.geography = geography;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (localityID != null ? localityID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Locality)) {
      return false;
    }
    Locality other = (Locality) object;
    return !((this.localityID == null && other.localityID != null)
            || (this.localityID != null && !this.localityID.equals(other.localityID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Locality[ localityID=" + localityID + " ]";
  }
}

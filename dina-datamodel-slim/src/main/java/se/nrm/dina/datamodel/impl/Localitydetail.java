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
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "localitydetail")
@NamedQueries({
  @NamedQuery(name = "Localitydetail.findAll", query = "SELECT l FROM Localitydetail l"),
  @NamedQuery(name = "Localitydetail.findByLocalityDetailID", 
          query = "SELECT l FROM Localitydetail l WHERE l.localityDetailID = :localityDetailID")})
public class Localitydetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "LocalityDetailID")
  private Integer localityDetailID;

  @Lob
  @Size(max = 65535)
  @Column(name = "GML")
  private String gml;

  @Size(max = 64)
  @Column(name = "NationalParkName")
  private String nationalParkName;

  @Size(max = 50)
  @Column(name = "RangeDesc")
  private String rangeDesc;

  @Size(max = 50)
  @Column(name = "RangeDirection")
  private String rangeDirection;

  @Size(max = 50)
  @Column(name = "Township")
  private String township;

  @Size(max = 50)
  @Column(name = "TownshipDirection")
  private String townshipDirection;

  @Size(max = 32)
  @Column(name = "PaleoLat")
  private String paleoLat;

  @Size(max = 32)
  @Column(name = "PaleoLng")
  private String paleoLng;
  
  @Size(max = 64)
  @Column(name = "WaterBody")
  private String waterBody;
  
  @Size(max = 64)
  @Column(name = "Island")
  private String island;
  
  @Size(max = 64)
  @Column(name = "IslandGroup")
  private String islandGroup;

  @JoinColumn(name = "LocalityID", referencedColumnName = "LocalityID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Locality locality;

  public Localitydetail() {
  }

  public Localitydetail(Integer localityDetailID) {
    this.localityDetailID = localityDetailID;
  }

  public Localitydetail(Integer localityDetailID, Date timestampCreated) {
    this.localityDetailID = localityDetailID;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(localityDetailID);
  }

  @Override
  public int getEntityId() {
    return localityDetailID;
  }

  public Integer getLocalityDetailID() {
    return localityDetailID;
  }

  public void setLocalityDetailID(Integer localityDetailID) {
    this.localityDetailID = localityDetailID;
  }

  public String getGml() {
    return gml;
  }

  public void setGml(String gml) {
    this.gml = gml;
  }

  public String getNationalParkName() {
    return nationalParkName;
  }

  public void setNationalParkName(String nationalParkName) {
    this.nationalParkName = nationalParkName;
  }

  public String getRangeDesc() {
    return rangeDesc;
  }

  public void setRangeDesc(String rangeDesc) {
    this.rangeDesc = rangeDesc;
  }

  public String getRangeDirection() {
    return rangeDirection;
  }

  public void setRangeDirection(String rangeDirection) {
    this.rangeDirection = rangeDirection;
  }

  public String getTownship() {
    return township;
  }

  public void setTownship(String township) {
    this.township = township;
  }

  public String getTownshipDirection() {
    return townshipDirection;
  }

  public void setTownshipDirection(String townshipDirection) {
    this.townshipDirection = townshipDirection;
  }

  public String getPaleoLat() {
    return paleoLat;
  }

  public void setPaleoLat(String paleoLat) {
    this.paleoLat = paleoLat;
  }

  public String getPaleoLng() {
    return paleoLng;
  }

  public void setPaleoLng(String paleoLng) {
    this.paleoLng = paleoLng;
  }
   
  public String getWaterBody() {
    return waterBody;
  }

  public void setWaterBody(String waterBody) {
    this.waterBody = waterBody;
  }
   
  public String getIsland() {
    return island;
  }

  public void setIsland(String island) {
    this.island = island;
  }
 
  public String getIslandGroup() {
    return islandGroup;
  }

  public void setIslandGroup(String islandGroup) {
    this.islandGroup = islandGroup;
  }

  public Locality getLocality() {
    return locality;
  }

  public void setLocality(Locality locality) {
    this.locality = locality;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (localityDetailID != null ? localityDetailID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Localitydetail)) {
      return false;
    }
    Localitydetail other = (Localitydetail) object;
    return !((this.localityDetailID == null && other.localityDetailID != null)
            || (this.localityDetailID != null && !this.localityDetailID.equals(other.localityDetailID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Localitydetail[ localityDetailID=" + localityDetailID + " ]";
  }
}

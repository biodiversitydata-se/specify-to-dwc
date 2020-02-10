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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "geographytreedef")
@NamedQueries({
  @NamedQuery(name = "Geographytreedef.findAll", query = "SELECT g FROM Geographytreedef g"),
  @NamedQuery(name = "Geographytreedef.findByGeographyTreeDefID", query = "SELECT g FROM Geographytreedef g WHERE g.geographyTreeDefID = :geographyTreeDefID"),
  @NamedQuery(name = "Geographytreedef.findByFullNameDirection", query = "SELECT g FROM Geographytreedef g WHERE g.fullNameDirection = :fullNameDirection"),
  @NamedQuery(name = "Geographytreedef.findByName", query = "SELECT g FROM Geographytreedef g WHERE g.name = :name")})
public class Geographytreedef extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "GeographyTreeDefID")
  private Integer geographyTreeDefID;

  @Column(name = "FullNameDirection")
  private Integer fullNameDirection;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 64)
  @Column(name = "Name")
  private String name;

  public Geographytreedef() {
  }

  public Geographytreedef(Integer geographyTreeDefID) {
    this.geographyTreeDefID = geographyTreeDefID;
  }

  public Geographytreedef(Integer geographyTreeDefID, Date timestampCreated, String name) {
    this.geographyTreeDefID = geographyTreeDefID;
    this.timestampCreated = timestampCreated;
    this.name = name;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(geographyTreeDefID);
  }

  @Override
  public int getEntityId() {
    return geographyTreeDefID;
  }

  public Integer getGeographyTreeDefID() {
    return geographyTreeDefID;
  }

  public void setGeographyTreeDefID(Integer geographyTreeDefID) {
    this.geographyTreeDefID = geographyTreeDefID;
  }

  public Integer getFullNameDirection() {
    return fullNameDirection;
  }

  public void setFullNameDirection(Integer fullNameDirection) {
    this.fullNameDirection = fullNameDirection;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
 
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (geographyTreeDefID != null ? geographyTreeDefID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Geographytreedef)) {
      return false;
    }
    Geographytreedef other = (Geographytreedef) object;
    return !((this.geographyTreeDefID == null && other.geographyTreeDefID != null)
            || (this.geographyTreeDefID != null && !this.geographyTreeDefID.equals(other.geographyTreeDefID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Geographytreedef[ geographyTreeDefID=" + geographyTreeDefID + " ]";
  }
}

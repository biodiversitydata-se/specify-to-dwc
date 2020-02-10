package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "division")
@NamedQueries({
  @NamedQuery(name = "Division.findAll", query = "SELECT d FROM Division d"),
  @NamedQuery(name = "Division.findByUserGroupScopeId", query = "SELECT d FROM Division d WHERE d.userGroupScopeId = :userGroupScopeId"),
  @NamedQuery(name = "Division.findByDisciplineType", query = "SELECT d FROM Division d WHERE d.disciplineType = :disciplineType"),
  @NamedQuery(name = "Division.findByDivisionId", query = "SELECT d FROM Division d WHERE d.divisionId = :divisionId"),
  @NamedQuery(name = "Division.findByName", query = "SELECT d FROM Division d WHERE d.name = :name")})
public class Division extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "UserGroupScopeId")
  private Integer userGroupScopeId;

  @Lob
  @Size(max = 65535)
  @Column(name = "Description")
  private String description;

  @Size(max = 64)
  @Column(name = "DisciplineType")
  private String disciplineType;

  @Column(name = "divisionId")
  private Integer divisionId;

  @Size(max = 255)
  @Column(name = "Name")
  private String name;

  @JoinColumn(name = "InstitutionID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Institution institution;

  public Division() {
  }

  public Division(Integer userGroupScopeId) {
    this.userGroupScopeId = userGroupScopeId;
  }

  public Division(Integer userGroupScopeId, Date timestampCreated) {
    this.userGroupScopeId = userGroupScopeId;
    this.timestampCreated = timestampCreated;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(userGroupScopeId);
  }

  @Override
  public int getEntityId() {
    return userGroupScopeId;
  }

  public Integer getUserGroupScopeId() {
    return userGroupScopeId;
  }

  public void setUserGroupScopeId(Integer userGroupScopeId) {
    this.userGroupScopeId = userGroupScopeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDisciplineType() {
    return disciplineType;
  }

  public void setDisciplineType(String disciplineType) {
    this.disciplineType = disciplineType;
  }

  public Integer getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(Integer divisionId) {
    this.divisionId = divisionId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Institution getInstitution() {
    return institution;
  }

  public void setInstitutionID(Institution institution) {
    this.institution = institution;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (userGroupScopeId != null ? userGroupScopeId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Division)) {
      return false;
    }
    Division other = (Division) object;
    return !((this.userGroupScopeId == null && other.userGroupScopeId != null)
            || (this.userGroupScopeId != null && !this.userGroupScopeId.equals(other.userGroupScopeId)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Division[ userGroupScopeId=" + userGroupScopeId + " ]";
  }
}

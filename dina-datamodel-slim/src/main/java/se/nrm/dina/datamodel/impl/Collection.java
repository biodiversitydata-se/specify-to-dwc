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
@Table(name = "collection")
@NamedQueries({
  @NamedQuery(name = "Collection.findAll", query = "SELECT c FROM Collection c"),
  @NamedQuery(name = "Collection.findByUserGroupScopeId", query = "SELECT c FROM Collection c WHERE c.userGroupScopeId = :userGroupScopeId"),
  @NamedQuery(name = "Collection.findByCode", query = "SELECT c FROM Collection c WHERE c.code = :code"),
  @NamedQuery(name = "Collection.findByCollectionId", query = "SELECT c FROM Collection c WHERE c.collectionId = :collectionId"),
  @NamedQuery(name = "Collection.findByCollectionName", query = "SELECT c FROM Collection c WHERE c.collectionName = :collectionName")})
public class Collection extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "UserGroupScopeId")
  private Integer userGroupScopeId;
 
  @Size(max = 50)
  @Column(name = "Code")
  private String code;

  @Column(name = "collectionId")
  private Integer collectionId;

  @Size(max = 50)
  @Column(name = "CollectionName")
  private String collectionName;

  @Size(max = 32)
  @Column(name = "CollectionType")
  private String collectionType;

  @Lob
  @Size(max = 65535)
  @Column(name = "Description")
  private String description;
 
  @JoinColumn(name = "DisciplineID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Discipline discipline;

  public Collection() {
  }

  public Collection(Integer userGroupScopeId) {
    this.userGroupScopeId = userGroupScopeId;
  }

  public Collection(Integer userGroupScopeId, Date timestampCreated,
          String catalogFormatNumName, boolean isEmbeddedCollectingEvent) {
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
 
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(Integer collectionId) {
    this.collectionId = collectionId;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getCollectionType() {
    return collectionType;
  }

  public void setCollectionType(String collectionType) {
    this.collectionType = collectionType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  } 
  
  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDisciplineID(Discipline discipline) {
    this.discipline = discipline;
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
    if (!(object instanceof Collection)) {
      return false;
    }
    Collection other = (Collection) object;
    return !((this.userGroupScopeId == null && other.userGroupScopeId != null)
            || (this.userGroupScopeId != null && !this.userGroupScopeId.equals(other.userGroupScopeId)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collection[ userGroupScopeId=" + userGroupScopeId + " ]";
  }
}

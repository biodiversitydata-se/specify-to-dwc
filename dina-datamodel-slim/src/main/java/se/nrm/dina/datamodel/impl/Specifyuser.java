package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.math.BigInteger;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "specifyuser")
@NamedQueries({
  @NamedQuery(name = "Specifyuser.findAll", query = "SELECT s FROM Specifyuser s"),
  @NamedQuery(name = "Specifyuser.findBySpecifyUserID", query = "SELECT s FROM Specifyuser s WHERE s.specifyUserID = :specifyUserID"),
  @NamedQuery(name = "Specifyuser.findByIsLoggedIn", query = "SELECT s FROM Specifyuser s WHERE s.isLoggedIn = :isLoggedIn"),
  @NamedQuery(name = "Specifyuser.findByLoginOutTime", query = "SELECT s FROM Specifyuser s WHERE s.loginOutTime = :loginOutTime")})
public class Specifyuser extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "SpecifyUserID")
  private Integer specifyUserID;

  @Column(name = "AccumMinLoggedIn")
  private BigInteger accumMinLoggedIn;

  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 64)
  @Column(name = "EMail")
  private String eMail;

  @Basic(optional = false)
  @NotNull
  @Column(name = "IsLoggedIn")
  private boolean isLoggedIn;

  @Column(name = "LoginOutTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date loginOutTime;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 64)
  @Column(name = "Name")
  private String name;

  @Size(max = 255)
  @Column(name = "Password")
  private String password;

  @Size(max = 32)
  @Column(name = "UserType")
  private String userType;

  public Specifyuser() {
  }

  public Specifyuser(Integer specifyUserID) {
    this.specifyUserID = specifyUserID;
  }

  public Specifyuser(Integer specifyUserID, Date timestampCreated, boolean isLoggedIn, boolean isLoggedInReport, String name) {
    this.specifyUserID = specifyUserID;
    this.timestampCreated = timestampCreated;
    this.isLoggedIn = isLoggedIn;
    this.name = name;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(specifyUserID);
  }
 
  @Override
  public int getEntityId() {
    return specifyUserID;
  }

  public Integer getSpecifyUserID() {
    return specifyUserID;
  }

  public void setSpecifyUserID(Integer specifyUserID) {
    this.specifyUserID = specifyUserID;
  }

  public BigInteger getAccumMinLoggedIn() {
    return accumMinLoggedIn;
  }

  public void setAccumMinLoggedIn(BigInteger accumMinLoggedIn) {
    this.accumMinLoggedIn = accumMinLoggedIn;
  }

  public String getEMail() {
    return eMail;
  }

  public void setEMail(String eMail) {
    this.eMail = eMail;
  }

  public boolean getIsLoggedIn() {
    return isLoggedIn;
  }

  public void setIsLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  public Date getLoginOutTime() {
    return loginOutTime;
  }

  public void setLoginOutTime(Date loginOutTime) {
    this.loginOutTime = loginOutTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (specifyUserID != null ? specifyUserID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Specifyuser)) {
      return false;
    }
    Specifyuser other = (Specifyuser) object;
    return !((this.specifyUserID == null && other.specifyUserID != null)
            || (this.specifyUserID != null && !this.specifyUserID.equals(other.specifyUserID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Specifyuser[ specifyUserID=" + specifyUserID + " ]";
  }
}

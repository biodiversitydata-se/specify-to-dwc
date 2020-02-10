package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "agent")
@NamedQueries({
  @NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a"),
  @NamedQuery(name = "Agent.findByAgentID", query = "SELECT a FROM Agent a WHERE a.agentID = :agentID"),
  @NamedQuery(name = "Agent.findByAgentType", query = "SELECT a FROM Agent a WHERE a.agentType = :agentType")})
public class Agent extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "AgentID")
  private Integer agentID;

  @Size(max = 50)
  @Column(name = "Abbreviation")
  private String abbreviation;

  @Basic(optional = false)
  @NotNull
  @Column(name = "AgentType")
  private short agentType;

  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 50)
  @Column(name = "Email")
  private String email;

  @Size(max = 50)
  @Column(name = "FirstName")
  private String firstName;

  @Size(max = 8)
  @Column(name = "Initials")
  private String initials;

  @Size(max = 128)
  @Column(name = "LastName")
  private String lastName;

  @Size(max = 50)
  @Column(name = "MiddleInitial")
  private String middleInitial; 
  
  @Size(max = 50)
  @Column(name = "Title")
  private String title;

  @Size(max = 50)
  @Column(name = "Suffix")
  private String suffix;
 
//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "agent", fetch = FetchType.LAZY)
//  private List<Collector> collectors;
   
  @Transient
  private StringBuilder fullNameSb; 
  @Transient
  private final String emptySpace = " "; 
   
  public Agent() {
  }

  public Agent(Integer agentID) {
    this.agentID = agentID;
  }

  public Agent(Integer agentID, Date timestampCreated, short agentType) {
    this.agentID = agentID;
    this.timestampCreated = timestampCreated;
    this.agentType = agentType;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(agentID);
  }

  @Override
  public int getEntityId() {
    return agentID;
  }

  public Integer getAgentID() {
    return agentID;
  }

  public void setAgentID(Integer agentID) {
    this.agentID = agentID;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public short getAgentType() {
    return agentType;
  }

  public void setAgentType(short agentType) {
    this.agentType = agentType;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }
  
  public String getFullName() {
    fullNameSb = new StringBuilder();
    if(firstName != null && !firstName.isEmpty()) {
      fullNameSb.append(firstName);
      fullNameSb.append(emptySpace);
    }
    if(middleInitial != null && !middleInitial.isEmpty()) {
      fullNameSb.append(middleInitial);
      fullNameSb.append(emptySpace);
    }
    if(lastName != null && !lastName.isEmpty()) {
      fullNameSb.append(lastName); 
    }
    return fullNameSb.toString().trim(); 
  }
   
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
// 
//  public List<Collector> getCollectors() {
//    return collectors;
//  }
//
//  public void setCollectors(List<Collector> collectors) {
//    this.collectors = collectors;
//  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (agentID != null ? agentID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Agent)) {
      return false;
    }
    Agent other = (Agent) object;
    return !((this.agentID == null && other.agentID != null)
            || (this.agentID != null && !this.agentID.equals(other.agentID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Agent[ agentID=" + agentID + " ]";
  }
}

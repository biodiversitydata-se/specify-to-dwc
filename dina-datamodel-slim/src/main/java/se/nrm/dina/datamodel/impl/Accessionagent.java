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
@Table(name = "accessionagent")
@NamedQueries({
  @NamedQuery(name = "Accessionagent.findAll", query = "SELECT a FROM Accessionagent a"),
  @NamedQuery(name = "Accessionagent.findByAccessionAgentID", query = "SELECT a FROM Accessionagent a WHERE a.accessionAgentID = :accessionAgentID"),
  @NamedQuery(name = "Accessionagent.findByRole", query = "SELECT a FROM Accessionagent a WHERE a.role = :role")})
public class Accessionagent extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "AccessionAgentID")
  private Integer accessionAgentID;
  
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "Role")
  private String role;

  @JoinColumn(name = "AgentID", referencedColumnName = "AgentID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Agent agent;

  @JoinColumn(name = "AccessionID", referencedColumnName = "AccessionID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Accession accession;

  public Accessionagent() {
  }

  public Accessionagent(Integer accessionAgentID) {
    this.accessionAgentID = accessionAgentID;
  }

  public Accessionagent(Integer accessionAgentID, Date timestampCreated, String role) {
    this.accessionAgentID = accessionAgentID;
    this.timestampCreated = timestampCreated;
    this.role = role;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(accessionAgentID);
  }

  @Override
  public int getEntityId() {
    return accessionAgentID;
  }

  public Integer getAccessionAgentID() {
    return accessionAgentID;
  }

  public void setAccessionAgentID(Integer accessionAgentID) {
    this.accessionAgentID = accessionAgentID;
  }
 
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public Accession getAccession() {
    return accession;
  }

  public void setAccession(Accession accession) {
    this.accession = accession;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (accessionAgentID != null ? accessionAgentID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Accessionagent)) {
      return false;
    }
    Accessionagent other = (Accessionagent) object;
    return (this.accessionAgentID != null || other.accessionAgentID == null)
            && (this.accessionAgentID == null
            || this.accessionAgentID.equals(other.accessionAgentID));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Accessionagent[ accessionAgentID=" + accessionAgentID + " ]";
  }
}

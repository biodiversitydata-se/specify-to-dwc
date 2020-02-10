package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "collector")
@NamedQueries({
  @NamedQuery(name = "Collector.findAll", query = "SELECT c FROM Collector c"),
  @NamedQuery(name = "Collector.findByCollectorID", query = "SELECT c FROM Collector c WHERE c.collectorID = :collectorID"),
  @NamedQuery(name = "Collector.findByIsPrimary", query = "SELECT c FROM Collector c WHERE c.isPrimary = :isPrimary")})
public class Collector extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "CollectorID")
  private Integer collectorID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "IsPrimary")
  private boolean isPrimary;

  @Basic(optional = false)
  @NotNull
  @Column(name = "OrderNumber")
  private int orderNumber;
 
  @JoinColumn(name = "AgentID", referencedColumnName = "AgentID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Agent agent;
 
  @JoinColumn(name = "CollectingEventID", referencedColumnName = "CollectingEventID")
  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collectingevent collectingEvent;
   
  public Collector() {
  }

  public Collector(Integer collectorID) {
    this.collectorID = collectorID;
  }

  public Collector(Integer collectorID, Date timestampCreated, boolean isPrimary, int orderNumber) {
    this.collectorID = collectorID;
    this.timestampCreated = timestampCreated;
    this.isPrimary = isPrimary;
    this.orderNumber = orderNumber;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(collectorID);
  }

  @Override
  public int getEntityId() {
    return collectorID;
  }

  public Integer getCollectorID() {
    return collectorID;
  }

  public void setCollectorID(Integer collectorID) {
    this.collectorID = collectorID;
  }

  public boolean getIsPrimary() {
    return isPrimary;
  }

  public void setIsPrimary(boolean isPrimary) {
    this.isPrimary = isPrimary;
  }

  public int getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }
 
  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }
 
  public Collectingevent getCollectingEvent() {
    return collectingEvent;
  }

  public void setCollectingEventID(Collectingevent collectingEvent) {
    this.collectingEvent = collectingEvent;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (collectorID != null ? collectorID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Collector)) {
      return false;
    }
    Collector other = (Collector) object;
    return !((this.collectorID == null && other.collectorID != null)
            || (this.collectorID != null && !this.collectorID.equals(other.collectorID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Collector[ collectorID=" + collectorID + " ]";
  }
}

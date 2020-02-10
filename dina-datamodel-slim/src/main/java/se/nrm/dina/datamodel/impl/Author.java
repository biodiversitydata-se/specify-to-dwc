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

/**
 *
 * @author idali
 */
@Entity
@Table(name = "author")
@NamedQueries({
  @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
  @NamedQuery(name = "Author.findByAuthorID", query = "SELECT a FROM Author a WHERE a.authorID = :authorID")})
public class Author extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "AuthorID")
  private Integer authorID;

  @Basic(optional = false)
  @NotNull
  @Column(name = "OrderNumber")
  private short orderNumber;
 
  @JoinColumn(name = "AgentID", referencedColumnName = "AgentID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Agent agent;

  public Author() {
  }

  public Author(Integer authorID) {
    this.authorID = authorID;
  }

  public Author(Integer authorID, Date timestampCreated, short orderNumber) {
    this.authorID = authorID;
    this.timestampCreated = timestampCreated;
    this.orderNumber = orderNumber;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(authorID);
  }

  @Override
  public int getEntityId() {
    return authorID;
  }

  public Integer getAuthorID() {
    return authorID;
  }

  public void setAuthorID(Integer authorID) {
    this.authorID = authorID;
  }

  public short getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(short orderNumber) {
    this.orderNumber = orderNumber;
  }
 
  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (authorID != null ? authorID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Author)) {
      return false;
    }
    Author other = (Author) object;
    return !((this.authorID == null && other.authorID != null)
            || (this.authorID != null && !this.authorID.equals(other.authorID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Author[ authorID=" + authorID + " ]";
  }
}

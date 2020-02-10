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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "loan")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l"),
  @NamedQuery(name = "Loan.findByLoanID", query = "SELECT l FROM Loan l WHERE l.loanID = :loanID"), 
  @NamedQuery(name = "Loan.findByOverdueLoan", 
          query = "SELECT l FROM Loan l WHERE l.isClosed = false AND l.currentDueDate  <= :currentDueDate"),
  @NamedQuery(name = "Loan.findByDateClosed", query = "SELECT l FROM Loan l WHERE l.dateClosed = :dateClosed"),
  @NamedQuery(name = "Loan.findByDateReceived", query = "SELECT l FROM Loan l WHERE l.dateReceived = :dateReceived"),
  @NamedQuery(name = "Loan.findByIsClosed", query = "SELECT l FROM Loan l WHERE l.isClosed = :isClosed"),
  @NamedQuery(name = "Loan.findByLoanDate", query = "SELECT l FROM Loan l WHERE l.loanDate = :loanDate"),
  @NamedQuery(name = "Loan.findByLoanNumber", query = "SELECT l FROM Loan l WHERE l.loanNumber = :loanNumber"), 
  @NamedQuery(name = "Loan.findByOverdueNotiSetDate", query = "SELECT l FROM Loan l WHERE l.overdueNotiSetDate = :overdueNotiSetDate")})
public class Loan extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "LoanID")
  private Integer loanID;

  @Column(name = "CurrentDueDate")
  @Temporal(TemporalType.DATE)
  private Date currentDueDate;

  @Column(name = "DateClosed")
  @Temporal(TemporalType.DATE)
  private Date dateClosed;

  @Column(name = "DateReceived")
  @Temporal(TemporalType.DATE)
  private Date dateReceived;

  @Column(name = "IsClosed")
  private Boolean isClosed;

  @Column(name = "LoanDate")
  @Temporal(TemporalType.DATE)
  private Date loanDate;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "LoanNumber")
  private String loanNumber;

  @Column(name = "OriginalDueDate")
  @Temporal(TemporalType.DATE)
  private Date originalDueDate;

  @Column(name = "OverdueNotiSetDate")
  @Temporal(TemporalType.DATE)
  private Date overdueNotiSetDate;
  
  @JoinColumn(name = "DisciplineID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne(optional = false)
  private Discipline discipline; 

  @JoinColumn(name = "DivisionID", referencedColumnName = "UserGroupScopeId")
  @ManyToOne
  private Division division;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "loan", fetch = FetchType.LAZY)
  private List<Loanagent> loanagents;

  public Loan() {
  }

  public Loan(Integer loanID) {
    this.loanID = loanID;
  }

  public Loan(Integer loanID, Date timestampCreated, String loanNumber) {
    this.loanID = loanID;
    this.timestampCreated = timestampCreated;
    this.loanNumber = loanNumber;
  }

  @XmlID
  @XmlAttribute(name = "id")
  @Override
  public String getIdentityString() {
    return String.valueOf(loanID);
  }

//    @XmlAttribute(name = "uuid") 
//    @Override
//    public String getUUID() {
//        return Util.getInstance().getURLLink(this.getClass().getSimpleName()) + loanID;
//    }
  @Override
  public int getEntityId() {
    return loanID;
  }

  public Integer getLoanID() {
    return loanID;
  }

  public void setLoanID(Integer loanID) {
    this.loanID = loanID;
  }

  public Date getCurrentDueDate() {
    return currentDueDate;
  }

  public void setCurrentDueDate(Date currentDueDate) {
    this.currentDueDate = currentDueDate;
  }

  public Date getDateClosed() {
    return dateClosed;
  }

  public void setDateClosed(Date dateClosed) {
    this.dateClosed = dateClosed;
  }

  public Date getDateReceived() {
    return dateReceived;
  }

  public void setDateReceived(Date dateReceived) {
    this.dateReceived = dateReceived;
  }

  public Boolean getIsClosed() {
    return isClosed;
  }

  public void setIsClosed(Boolean isClosed) {
    this.isClosed = isClosed;
  }

  public Date getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(Date loanDate) {
    this.loanDate = loanDate;
  }

  public String getLoanNumber() {
    return loanNumber;
  }

  public void setLoanNumber(String loanNumber) {
    this.loanNumber = loanNumber;
  }

  public Date getOriginalDueDate() {
    return originalDueDate;
  }

  public void setOriginalDueDate(Date originalDueDate) {
    this.originalDueDate = originalDueDate;
  }

  public Date getOverdueNotiSetDate() {
    return overdueNotiSetDate;
  }

  public void setOverdueNotiSetDate(Date overdueNotiSetDate) {
    this.overdueNotiSetDate = overdueNotiSetDate;
  }
 
  @XmlIDREF
  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }
  
  @XmlIDREF
  public Division getDivision() {
    return division;
  }

  public void setDivision(Division division) {
    this.division = division;
  }

  @XmlTransient
  public List<Loanagent> getLoanagents() {
    return loanagents;
  }

  public void setLoanagents(List<Loanagent> loanagents) {
    this.loanagents = loanagents;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (loanID != null ? loanID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Loan)) {
      return false;
    }
    Loan other = (Loan) object;
    return !((this.loanID == null && other.loanID != null) || (this.loanID != null && !this.loanID.equals(other.loanID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Loan[ loanID=" + loanID + " ]";
  }

}

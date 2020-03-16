package se.nrm.dina.datamodel.impl;

import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size; 

/**
 *
 * @author idali
 */
@Entity
@Table(name = "taxon")
@NamedQueries({
  @NamedQuery(name = "Taxon.findAll", query = "SELECT t FROM Taxon t"),
  @NamedQuery(name = "Taxon.findByTaxonID", query = "SELECT t FROM Taxon t WHERE t.taxonID = :taxonID"), 
  @NamedQuery(name = "Taxon.findByAuthor", query = "SELECT t FROM Taxon t WHERE t.author = :author"),
  @NamedQuery(name = "Taxon.findByRank", query = "SELECT t FROM Taxon t WHERE t.rank = :rank")})
public class Taxon extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "TaxonID")
  private Integer taxonID;

  @Size(max = 128)
  @Column(name = "Author")
  private String author;

  @Size(max = 128)
  @Column(name = "CommonName")
  private String commonName;

  @Size(max = 255)
  @Column(name = "FullName")
  private String fullName;

  @Column(name = "HighestChildNodeNumber")
  private Integer highestChildNodeNumber;

  @Column(name = "IsAccepted")
  private Boolean isAccepted;

  @Column(name = "IsHybrid")
  private Boolean isHybrid;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 64)
  @Column(name = "Name")
  private String name;

  @Column(name = "NodeNumber")
  private Integer nodeNumber;

  @Basic(optional = false)
  @NotNull
  @Column(name = "RankID")
  private int rank;

  @Lob
  @Size(max = 65535)
  @Column(name = "Remarks")
  private String remarks;

  @JoinColumn(name = "TaxonTreeDefItemID", referencedColumnName = "TaxonTreeDefItemID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Taxontreedefitem taxonTreeDefItem;

  @JoinColumn(name = "ParentID", referencedColumnName = "TaxonID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Taxon parent;

  @OneToMany(mappedBy = "accepted", fetch = FetchType.LAZY)
  private Set<Taxon> synomys;

  @JoinColumn(name = "AcceptedID", referencedColumnName = "TaxonID")
  @ManyToOne
  private Taxon accepted;

  @JoinColumn(name = "TaxonTreeDefID", referencedColumnName = "TaxonTreeDefID")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Taxontreedef taxonTreeDef;
    
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxon", fetch = FetchType.LAZY)
  private Set<Commonnametx> commonnametxs;

  @Transient
  private StringBuilder sb;

  public Taxon() {
  }

  public Taxon(Integer taxonID) {
    this.taxonID = taxonID;
  }

  public Taxon(Integer taxonID, Date timestampCreated, String name, int rank) {
    this.taxonID = taxonID;
    this.timestampCreated = timestampCreated;
    this.name = name;
    this.rank = rank;
  }

  public Integer getTaxonID() {
    return taxonID;
  }

  public void setTaxonID(Integer taxonID) {
    this.taxonID = taxonID;
  }

  @Override
  public String getIdentityString() {
    return String.valueOf(taxonID);
  }

  @Override
  public int getEntityId() {
    return taxonID;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getHighestChildNodeNumber() {
    return highestChildNodeNumber;
  }

  public void setHighestChildNodeNumber(Integer highestChildNodeNumber) {
    this.highestChildNodeNumber = highestChildNodeNumber;
  }

  public Boolean getIsAccepted() {
    return isAccepted;
  }

  public void setIsAccepted(Boolean isAccepted) {
    this.isAccepted = isAccepted;
  }

  public Boolean getIsHybrid() {
    return isHybrid;
  }

  public void setIsHybrid(Boolean isHybrid) {
    this.isHybrid = isHybrid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNodeNumber() {
    return nodeNumber;
  }

  public void setNodeNumber(Integer nodeNumber) {
    this.nodeNumber = nodeNumber;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Taxontreedefitem getTaxonTreeDefItem() {
    return taxonTreeDefItem;
  }

  public void setTaxonTreeDefItem(Taxontreedefitem taxonTreeDefItem) {
    this.taxonTreeDefItem = taxonTreeDefItem;
  }

  public Taxon getParent() {
    return parent;
  }

  public void setParent(Taxon parent) {
    this.parent = parent;
  }
  
  public Set<Taxon> getSynomys() {
    return synomys;
  }

  public void setSynomys(Set<Taxon> synomys) {
    this.synomys = synomys;
  }

  
  public Taxon getAccepted() {
    return accepted;
  }

  public void setAccepted(Taxon accepted) {
    this.accepted = accepted;
  }
  
  public Taxontreedef getTaxonTreeDef() {
    return taxonTreeDef;
  }

  public void setTaxonTreeDef(Taxontreedef taxonTreeDef) {
    this.taxonTreeDef = taxonTreeDef;
  }

  public Set<Commonnametx> getCommonnametxs() {
    return commonnametxs;
  }

  public void setCommonnametxs(Set<Commonnametx> commonnametxs) {
    this.commonnametxs = commonnametxs;
  }

  public String getSubspecies() {
    if (rank == 230) {
      return name;
    }
    if (rank > 230) {
      Taxon newParent = parent;
      while (newParent.getRank() > 230) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 230) {
        return newParent.getName();
      }
    }
    return null;
  }
  
  public String getSpecies() { 
    if (rank == 220) { 
      return name;
    }
    if (rank > 220) {
      Taxon newParent = parent;
      while (newParent.getRank() > 220) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 220) {
        return newParent.getName();
      }
    }
    return null;
  }
  
  public String getSubgenus() {
    if (rank == 190) {
      return name;
    }
    if (rank > 190) {
      Taxon newParent = parent;
      while (newParent.getRank() > 190) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 190) {
        return newParent.getName();
      }
    }
    return null;
  }

  public String getGenus() {
    if (rank == 180) {
      return name;
    }
    if (rank > 180) {
      Taxon newParent = parent;
      while (newParent.getRank() > 180) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 180) {
        return newParent.getName();
      }
    }
    return null;
  }

  public String getFamily() {
    if (rank == 140) {
      return name;
    }
    if (rank > 140) {
      Taxon newParent = parent;
      while (newParent.getRank() > 140) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 140) {
        return newParent.getName();
      }
    }
    return null;
  }
  
  public String getOrder() {
    if (rank == 100) {
      return name;
    }
    if (rank > 100) {
      Taxon newParent = parent;
      while (newParent.getRank() > 100) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 100) {
        return newParent.getName();
      }
    }
    return null;
  }
  
  public String getClazz() {
    if (rank == 60) {
      return name;
    }
    if (rank > 60) {
      Taxon newParent = parent;
      while (newParent.getRank() > 60) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 60) {
        return newParent.getName();
      }
    }
    return null;
  }
  
  public String getPhylum() {
    if (rank == 30) {
      return name;
    }
    if (rank > 30) {
      Taxon newParent = parent;
      while (newParent.getRank() > 30) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 30) {
        return newParent.getName();
      }
    }
    return null;
  }
  
   public String getKingdom() {
    if (rank == 10) {
      return name;
    }
    if (rank > 10) {
      Taxon newParent = parent;
      while (newParent.getRank() > 10) {
        newParent = newParent.getParent();
      }
      if (newParent.getRank() == 10) {
        return newParent.getName();
      }
    }
    return null;
  }


  public String getHighClassification() {
    sb = new StringBuilder();
    if (rank > 0) {
      Taxon newParent = parent;
      while (newParent.getRank() > 0) {
        if (sb.length() > 0) {
          sb.insert(0, "/");
        }
        sb.insert(0, newParent.getFullName());
        newParent = newParent.getParent();
      }
    }
    return sb.toString();
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (taxonID != null ? taxonID.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Taxon)) {
      return false;
    }
    Taxon other = (Taxon) object;
    return !((this.taxonID == null && other.taxonID != null)
            || (this.taxonID != null && !this.taxonID.equals(other.taxonID)));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.datamodel.Taxon[ taxonID=" + taxonID + " ]";
  }
}

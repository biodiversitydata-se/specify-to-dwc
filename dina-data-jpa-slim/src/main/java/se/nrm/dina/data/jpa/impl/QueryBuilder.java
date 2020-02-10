package se.nrm.dina.data.jpa.impl;

import java.util.Date;
import java.util.Map;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.util.HelpClass;
import se.nrm.dina.data.util.JpaReflectionHelper;
import se.nrm.dina.data.util.ValueType;

/**
 * QueryBuilder helps to build query with search conditions
 *
 * @author idali
 */
class QueryBuilder {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String BETWEEN = "between";
  private final String GREAT_THAN = "gt";
  private final String LESS_THAN = "lt";
  private final String MIN = "min";
  private final String MAX = "max";

  private static QueryBuilder instance = null;
  private StringBuilder sb;

  public static synchronized QueryBuilder getInstance() {
    if (instance == null) {
      instance = new QueryBuilder();
    }
    return instance;
  }

  /** 
   *
   * @param collectionId
   * @param isFilterWithIds
   * @return
   */
  public String buildQuery(int collectionId, Date startDate, Date toDate) {
    sb = new StringBuilder();
    sb.append("SELECT DISTINCT c FROM Collectionobject c ")
            .append("LEFT JOIN FETCH c.collectionObjectAttribute ")
            .append("LEFT JOIN FETCH c.determinations d ")
            .append("LEFT JOIN FETCH c.collection ct ")
            .append("LEFT JOIN FETCH ct.discipline dsc ")
            .append("LEFT JOIN FETCH dsc.division div ")
            .append("LEFT JOIN FETCH div.institution inst ")
            .append("LEFT JOIN FETCH c.collectingEvent ce ")
            .append("LEFT JOIN FETCH c.preparations p ")
            .append("LEFT JOIN FETCH ce.locality lc ")
            .append("LEFT JOIN FETCH ce.collectors clts ") 
            .append("LEFT JOIN FETCH clts.agent ")
            .append("LEFT JOIN FETCH ce.collectingEventAttribute cea ")
            .append("LEFT JOIN FETCH lc.geography g ") 
            .append("LEFT JOIN FETCH lc.localitydetails ld ")
            .append("LEFT JOIN FETCH lc.geocoorddetails gd ")
            .append("LEFT JOIN FETCH gd.agent gdagent ")
            .append("LEFT JOIN FETCH g.parent gp ")
            .append("LEFT JOIN FETCH gp.parent gpp ")
            .append("LEFT JOIN FETCH gpp.parent gppp ")
            .append("LEFT JOIN FETCH gppp.parent gpppp ")
            .append("LEFT JOIN FETCH gpppp.parent gppppp ")
            .append("LEFT JOIN FETCH gppppp.parent gpppppp ")  
            .append("LEFT JOIN FETCH d.preferredTaxon pt ")
            .append("LEFT JOIN FETCH pt.commonnametxs cn ") 
            .append("LEFT JOIN FETCH pt.taxonTreeDefItem tdi ") 
            .append("LEFT JOIN FETCH pt.parent ptp ")
            .append("LEFT JOIN FETCH ptp.parent ptpp ")
            .append("LEFT JOIN FETCH ptpp.parent ptppp ")
            .append("LEFT JOIN FETCH ptppp.parent ptpppp ")
            .append("LEFT JOIN FETCH ptpppp.parent ptppppp ")
            .append("LEFT JOIN FETCH ptppppp.parent ptpppppp ")
            .append("LEFT JOIN FETCH ptpppppp.parent ptppppppp ")
            .append("LEFT JOIN FETCH ptppppppp.parent ptpppppppp ")
            .append("LEFT JOIN FETCH ptpppppppp.parent ptppppppppp ")
            .append("LEFT JOIN FETCH ptppppppppp.parent ptpppppppppp ") 
            .append("LEFT JOIN FETCH d.determiner ") 
            .append("LEFT JOIN FETCH p.prepType ")   
            .append("WHERE c.collectionMemberID = :collectionMemberID ");
//            .append("AND d.isCurrent is true "); 
    if(startDate != null && toDate != null) {
      sb.append("AND c.timestampCreated BETWEEN :fromDate AND :toDate ");
    }        
    sb.append("ORDER BY c.collectionObjectID");   

    return sb.toString();
  }

  public String buildFindCollectionObjectsByCollectionCodeQuery(int collectionId, boolean isFilterWithIds) {
    sb = new StringBuilder();
    sb.append("SELECT DISTINCT c FROM Collectionobject c ")
            .append("LEFT JOIN FETCH c.accession ")
            .append("LEFT JOIN FETCH c.collectionObjectAttribute ")
            .append("LEFT JOIN FETCH c.determinations d ")
            .append("LEFT JOIN FETCH c.collection ct ")
            .append("LEFT JOIN FETCH c.collectingEvent ce ")
            .append("LEFT JOIN FETCH c.preparations p ")
            .append("LEFT JOIN FETCH c.collectionobjectattachments ca ")
            .append("LEFT JOIN FETCH ce.locality lc ")
            .append("LEFT JOIN FETCH ce.collectors clts ")
            .append("LEFT JOIN FETCH clts.agent ")
            .append("LEFT JOIN FETCH lc.geography g ")
            .append("LEFT JOIN FETCH g.parent gp ")
            .append("LEFT JOIN FETCH gp.parent gpp ")
            .append("LEFT JOIN FETCH gpp.parent gppp ")
            .append("LEFT JOIN FETCH gppp.parent gpppp ")
            .append("LEFT JOIN FETCH gpppp.parent gppppp ")
            .append("LEFT JOIN FETCH gppppp.parent gpppppp ")
            .append("LEFT JOIN FETCH d.preferredTaxon pt ")
            .append("LEFT JOIN FETCH pt.commonnametxs cn ")
            .append("LEFT JOIN FETCH pt.synomys ")
            .append("LEFT JOIN FETCH pt.parent ptp ")
            .append("LEFT JOIN FETCH ptp.parent ptpp ")
            .append("LEFT JOIN FETCH ptpp.parent ptppp ")
            .append("LEFT JOIN FETCH ptppp.parent ptpppp ")
            .append("LEFT JOIN FETCH ptpppp.parent ptppppp ")
            .append("LEFT JOIN FETCH ptppppp.parent ptpppppp ")
            .append("LEFT JOIN FETCH ptpppppp.parent ptppppppp ")
            .append("LEFT JOIN FETCH ptppppppp.parent ptpppppppp ")
            .append("LEFT JOIN FETCH ptpppppppp.parent ptppppppppp ")
            .append("LEFT JOIN FETCH ptppppppppp.parent ptpppppppppp ")
            .append("LEFT JOIN FETCH d.taxon t ")
            .append("LEFT JOIN FETCH t.synomys ")
            .append("LEFT JOIN FETCH t.commonnametxs cn ")
            //    if (collectionId != ornithologyCollectionId) {
            //      sb.append("LEFT JOIN FETCH t.commonnametxs cn ");
            //    }
            .append("LEFT JOIN FETCH t.parent tp ")
            .append("LEFT JOIN FETCH tp.parent tpp ")
            .append("LEFT JOIN FETCH tpp.parent tppp ")
            .append("LEFT JOIN FETCH tppp.parent tpppp ")
            .append("LEFT JOIN FETCH tpppp.parent tppppp ")
            .append("LEFT JOIN FETCH tppppp.parent tpppppp ")
            .append("LEFT JOIN FETCH tpppppp.parent tppppppp ")
            .append("LEFT JOIN FETCH tppppppp.parent tpppppppp ")
            .append("LEFT JOIN FETCH tpppppppp.parent tppppppppp ")
            .append("LEFT JOIN FETCH tppppppppp.parent tpppppppppp ")
            .append("LEFT JOIN FETCH d.determiner ")
            .append("LEFT JOIN FETCH p.storage ")
            .append("LEFT JOIN FETCH p.prepType ")
            .append("LEFT JOIN FETCH ca.attachment a ")
            .append("LEFT JOIN FETCH a.attachmentImageAttribute ai ")
            .append("LEFT JOIN FETCH ai.morphBankWiew ")
            .append("LEFT JOIN FETCH c.dnasequences dna ")
            .append("where c.collectionMemberID = :collectionMemberID ");
    if (isFilterWithIds) {
      sb.append("AND c.collectionObjectID in :ids ");
    }
    sb.append("ORDER BY c.collectionObjectID");

    return sb.toString();
  }

  public String buildGeographyParentQuery() {
    sb = new StringBuilder();
    sb.append("SELECT DISTINCT g FROM Geography g ");
    sb.append("LEFT JOIN FETCH g.parent gp ")
            .append("LEFT JOIN FETCH gp.parent gpp ")
            .append("LEFT JOIN FETCH gpp.parent gppp ")
            .append("LEFT JOIN FETCH gppp.parent gpppp ")
            .append("LEFT JOIN FETCH gpppp.parent gppppp ")
            .append("LEFT JOIN FETCH gppppp.parent gpppppp ")
            .append("WHERE g.geographyID = :geographyId ");
    return sb.toString();
  }

  public String buildTaxonParentsQuery() {
    sb = new StringBuilder();
    sb.append("SELECT DISTINCT t FROM Taxon t ");
    sb.append("LEFT JOIN FETCH t.parent tp ");
    sb.append("LEFT JOIN FETCH tp.parent tpp ");
    sb.append("LEFT JOIN FETCH tpp.parent tppp ");
    sb.append("LEFT JOIN FETCH tppp.parent tpppp ");
    sb.append("LEFT JOIN FETCH tpppp.parent tppppp ");
    sb.append("LEFT JOIN FETCH tppppp.parent tpppppp ");
    sb.append("LEFT JOIN FETCH tpppppp.parent tppppppp ");
    sb.append("LEFT JOIN FETCH tppppppp.parent tpppppppp ");
    sb.append("LEFT JOIN FETCH tpppppppp.parent tppppppppp ");
    sb.append("LEFT JOIN FETCH tppppppppp.parent tpppppppppp ");
    sb.append("WHERE t.taxonID = :taxonId ");
    return sb.toString();
  }

  public String buildFindSMTPEventDataQuery() {
    sb = new StringBuilder();
    sb.append("SELECT DISTINCT ce FROM Collectingevent ce ")
            .append("LEFT JOIN FETCH ce.locality lc ")
            .append("LEFT JOIN FETCH lc.geography g ")
            .append("LEFT JOIN FETCH g.parent gp ")
            .append("LEFT JOIN FETCH gp.parent gpp ")
            .append("LEFT JOIN FETCH gpp.parent gppp ")
            .append("LEFT JOIN FETCH gppp.parent gpppp ")
            .append("LEFT JOIN FETCH gpppp.parent gppppp ")
            .append("LEFT JOIN FETCH gppppp.parent gpppppp ")
            .append("where ce.stationFieldNumber like 'Event ID %'");
    return sb.toString();
  }

  public String buildOverdueLoanQuery() {
    sb = new StringBuilder();
    sb.append("SELECT DISTINCT l FROM Loan l ")
            .append("LEFT JOIN FETCH l.loanagentList a ")
            .append("where l.isClosed = false AND l.currentDueDate  <= :currentDueDate");
    return sb.toString();
  }

  /**
   * Build a namedQuery with parameters
   *
   * @param query
   * @param clazz
   * @param parameters
   * @param isFuzzSearch
   * @return Query
   */
  public Query createQuery(Query query, Class clazz, Map<String, String> parameters, boolean isExact) {
    logger.info("createQuery : {}", isExact);

    if (parameters != null) {
      parameters.entrySet()
              .stream()
              .forEach((entry) -> {
                String fieldName = entry.getKey();
                String value = entry.getValue();
                try {
                  ValueType valueType = JpaReflectionHelper.getInstance().getValueType(clazz, fieldName);
                  switch (valueType) {
                    case INT:
                      query.setParameter(fieldName, Integer.parseInt(entry.getValue()));
                      break;
                    case ENTITY:
                      query.setParameter(fieldName, Integer.parseInt(entry.getValue()));
                      break;
                    case BIGDECIMAL:
                      setBigDecimal(fieldName, value, query);
                      break;
                    case DATE:
                      setDate(fieldName, value, query);
                      break;
                    case SHORT:
                      setShotValue(fieldName, value, query);
                      break;
                    default:
                      setDefaultValue(fieldName, value, query, isExact);
                      break;
                  }
                } catch (DinaException e) {
                  throw e;
                }
              });
    }
    return query;
  }

  private void setBigDecimal(String fieldName, String value, Query query) {

    if (value.toLowerCase().startsWith(BETWEEN)) {
      query.setParameter(fieldName + MIN, HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, "(", ",")));
      query.setParameter(fieldName + MAX, HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, ",", ")")));
    } else if (value.toLowerCase().startsWith(GREAT_THAN)) {
      query.setParameter(fieldName + "v1", HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, "(", ")")));
    } else if (value.toLowerCase().startsWith(LESS_THAN)) {
      query.setParameter(fieldName + "v2", HelpClass.getInstance().convertStringToBigDecimal(StringUtils.substringBetween(value, "(", ")")));
    } else {
      query.setParameter(fieldName, HelpClass.getInstance().convertStringToBigDecimal(value));
    }
  }

  private void setDate(String fieldName, String value, Query query) {

    if (value.toLowerCase().startsWith(BETWEEN)) {
      query.setParameter(fieldName + MIN, HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, "(", ",")));
      query.setParameter(fieldName + MAX, HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, ",", ")")));
    } else if (value.toLowerCase().startsWith(GREAT_THAN)) {
      query.setParameter(fieldName + "v1", HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, "(", ")")));
    } else if (value.toLowerCase().startsWith(LESS_THAN)) {
      query.setParameter(fieldName + "v2", HelpClass.getInstance().convertStringToDate(StringUtils.substringBetween(value, "(", ")")));
    } else {
      query.setParameter(fieldName, HelpClass.getInstance().convertStringToDate(value));
    }
  }

  private void setShotValue(String fieldName, String value, Query query) {

    if (value != null && !value.isEmpty()) {
      short s = java.lang.Short.parseShort(value);
      query.setParameter(fieldName, s);
    }
  }

  private void setDefaultValue(String fieldName, String value, Query query, boolean isExact) {
    logger.info("setDefaultValue : {} -- {}", value, isExact);
    if (isExact) {
      query.setParameter(fieldName, value);
    } else {
      query.setParameter(fieldName, "%" + value + "%");
    }
  }

}

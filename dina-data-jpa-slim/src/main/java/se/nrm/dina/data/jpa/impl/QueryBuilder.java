package se.nrm.dina.data.jpa.impl;

import java.util.Date;
import java.util.Map;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils; 
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.util.HelpClass;
import se.nrm.dina.data.util.JpaReflectionHelper;
import se.nrm.dina.data.util.ValueType;

/**
 * QueryBuilder helps to build query with search conditions
 *
 * @author idali
 */
@Slf4j
class QueryBuilder { 

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
  
  public String buildGetIdsQuery(Date startDate, Date toDate) {
    log.info("buildGetIdsQuery : {} -- {}", startDate, toDate);
    sb = new StringBuilder();
    sb.append("SELECT c.collectionObjectID FROM Collectionobject c "); 
    sb.append("WHERE c.collectionMemberID = :collectionMemberID ");
    if(startDate != null && toDate != null) {
      sb.append("AND c.timestampModified BETWEEN :fromDate AND :toDate ");
    } else if(startDate != null && toDate == null) {
      sb.append("AND c.timestampModified > :fromDate ");
    } else if(startDate == null && toDate != null) {
      sb.append("AND c.timestampModified < :toDate ");
    }   
    sb.append("ORDER BY c.collectionObjectID");   
    return sb.toString();
  }
  
  public String buildBaseQuery() {
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
            .append("LEFT JOIN FETCH p.storage s ")
            .append("WHERE c.collectionMemberID = :collectionMemberID ")
            .append("AND d.isCurrent = 1 "); 
    return sb.toString();
  }
   
  /** 
   *
   * @param collectionId
   * @param isFilterWithIds
   * @return
   */
  public String buildQuery(Date startDate, Date toDate, 
          boolean filterWithIds, Map<String, String> filterMap) { 
    buildBaseQuery(); 
    if(startDate != null && toDate != null) {
      sb.append("AND c.timestampModified BETWEEN :fromDate AND :toDate ");
    } else if(startDate != null && toDate == null) {
      sb.append("AND c.timestampModified > :fromDate ");
    } else if(startDate == null && toDate != null) {
      sb.append("AND c.timestampModified < :toDate ");
    } 
    if (filterWithIds) {
      sb.append("AND c.collectionObjectID in :ids ");
    }
    if(filterMap != null && !filterMap.isEmpty()) { 
      filterMap.keySet()
              .stream()
              .forEach(key -> {
                sb.append("AND (");
                sb.append(key);
                sb.append(" <> :");
                sb.append(StringUtils.substringAfterLast(key, "."));
                sb.append(" OR ");
                sb.append(key); 
                sb.append(" is Null) "); 
              }); 
    }
    sb.append("ORDER BY c.collectionObjectID ");    
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
    log.info("createQuery : {}", isExact);

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
    log.info("setDefaultValue : {} -- {}", value, isExact);
    if (isExact) {
      query.setParameter(fieldName, value);
    } else {
      query.setParameter(fieldName, "%" + value + "%");
    }
  }

}

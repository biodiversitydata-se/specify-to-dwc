package se.nrm.dina.data.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import javax.persistence.Id; 
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.data.exceptions.ErrorMsg;
import se.nrm.dina.data.vo.ErrorBean;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.util.DataModelHelper;

/**
 *
 *
 * @author idali
 */
@Slf4j
public class JpaReflectionHelper { 

  private static JpaReflectionHelper instance = null;

  private final String DATATYPE_INT = "int";
  private final String DATATYPE_INTEGER = "java.lang.Integer";
  private final String DATATYPE_LIST = "java.util.List";
  private final String DATATYPE_DATE = "java.util.Date";
  private final String DATATYPE_SET = "java.util.Set";
  private final String DATATYPE_BIGDECIMAL = "java.math.BigDecimal";
  private final String DATATYPE_SHORT = "short";

  public static synchronized JpaReflectionHelper getInstance() {
    if (instance == null) {
      instance = new JpaReflectionHelper();
    }
    return instance;
  }

  /**
   * Converts entityname to class
   *
   * @param classname
   * @return Class
   */
  public Class convertClassNameToClass(String classname) { 
    log.info("convertClassNameToClass : {}", classname);

    try {
      return Class.forName(DataModelHelper.getInstance().getENTITY_PACKAGE() + reformClassName(classname));
    } catch (ClassNotFoundException ex) {
      ErrorBean error = new ErrorBean(classname, ErrorMsg.getInstance().getEntityNameErrorMsg());
      throw new DinaException(error, 400);
    }
  }

  /**
   * Reforms class name to uppercase on first letter
   *
   * @param s
   * @return
   */
  public String reformClassName(String s) { 

    if (s.length() == 0) {
      return s;
    }
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }

  /**
   * Creates an instance of entity of the given name
   *
   * @param <T>
   * @param clazz
   * @return EntityBean
   */
  public <T extends EntityBean> T createNewInstance(Class clazz) {
    try {
      return (T) clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      throw new DinaException(ErrorMsg.getInstance().getEntityNameErrorMsg(), 400);
    }
  }

  /**
   * Validates if the entityname is valid
   *
   * @param entityName
   * @return entityName
   */
  public String validateEntityName(String entityName) {
    EntityBean entity;
    try {
      entity = createNewInstance(convertClassNameToClass(entityName));
    } catch (DinaException e) {
      throw e;
    }
    return entity.getClass().getSimpleName();
  }

  /**
   * Checks if the field is int of Integer
   *
   * @param clazz
   * @param fieldName
   * @return boolean
   */
  public boolean isIntField(Class clazz, String fieldName) { 
    try {
      return clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_INT)
              || clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_INTEGER);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isIntField(superClass, fieldName);
      }
    }
  }

  public boolean isShortField(Class clazz, String fieldName) {
    log.info("isShortField : {} -- {}", clazz, fieldName);

    try {  
      return clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_SHORT);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isShortField(superClass, fieldName);
      }
    }
  }

  /**
   * Checks if the field is an Entity
   *
   * @param clazz
   * @param fieldName
   * @return boolean
   */
  public boolean isEntity(Class clazz, String fieldName) {
//        logger.info("isEntity : {} -- {}", clazz, fieldName);
    try {
      return clazz.getDeclaredField(fieldName).getType().getName().contains(DataModelHelper.getInstance().getENTITY_PACKAGE());
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isEntity(superClass, fieldName);
      }
    }
  }

  /**
   * Checks if the field is a collection
   *
   * @param clazz
   * @param fieldName
   * @return
   */
  public boolean isList(Class clazz, String fieldName) {  
    try { 
      return clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_LIST);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isList(superClass, fieldName);
      }
    }
  }
  
  public boolean isSet(Class clazz, String fieldName) {  
    try { 
      return clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_SET);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isSet(superClass, fieldName);
      }
    }
  }

  /**
   * Checks if the field is a java.util.date
   *
   * @param clazz
   * @param fieldName
   * @return
   */
  public boolean isDate(Class clazz, String fieldName) { 
    try {
      return clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_DATE);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isDate(superClass, fieldName);
      }

    }
  }

  /**
   * Checks if the field is a java.util.date
   *
   * @param clazz
   * @param fieldName
   * @return
   */
  public boolean isBigDecimal(Class clazz, String fieldName) { 
    try {
      return clazz.getDeclaredField(fieldName).getType().getName().equals(DATATYPE_BIGDECIMAL);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return isBigDecimal(superClass, fieldName);
      }
    }
  }

  /**
   * Creates an Entity
   *
   * @param clazz
   * @param fieldName
   * @return EntityBean
   */
  public EntityBean getEntity(Class clazz, String fieldName) { 
    try {
      return createNewInstance(convertClassNameToClass(clazz.getDeclaredField(fieldName).getType().getSimpleName()));
    } catch (DinaException e) {
      throw e;
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
      } else {
        return getEntity(superClass, fieldName);
      }
    }
  }

  public ValueType getValueType(Class clazz, String fieldName) {
    try {
      if (isIntField(clazz, fieldName)) {
        return ValueType.INT;
      } else if (isEntity(clazz, fieldName)) {
        return ValueType.ENTITY;
      } else if (isBigDecimal(clazz, fieldName)) {
        return ValueType.BIGDECIMAL;
      } else if (isDate(clazz, fieldName)) {
        return ValueType.DATE;
      } else if (isList(clazz, fieldName)) {
        return ValueType.LIST;
      } else if(isSet(clazz, fieldName)) {
        return ValueType.SET;
      } else if (isShortField(clazz, fieldName)) {
        return ValueType.SHORT;
      } else {
        return ValueType.STRING;
      }
    } catch (DinaException e) {
      throw e;
    }
  }

  /**
   * Validates one field in an entity
   *
   * @param clazz
   * @param fieldName
   * @return boolean
   */
  public boolean validateFields(Class clazz, String fieldName) { 
    try {
      clazz.getDeclaredField(fieldName);
      return true;
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {

        // should return false?
        // throw new DinaException(ErrorMsg.getInstance().getFieldNotExist(clazz.getSimpleName(), fieldName), 400);
        return false;
      } else {
        return validateFields(superClass, fieldName);
      }
    }
  }

  /**
   * Check if there is version field
   *
   * @param clazz
   * @return boolean
   */
  public boolean isVersioned(Class clazz) { 
    try {
      clazz.getDeclaredField(DataModelHelper.getInstance().getVERSION());
      return true;
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        return false;
      } else {
        return isVersioned(superClass);
      }
    }
  }

  /**
   *
   * @param clazz
   * @return Field
   */
  public Field getTimestampCreated(Class clazz) { 
    try {
      return clazz.getDeclaredField(DataModelHelper.getInstance().getTIME_CREATED_FIELD());
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        return null;
      } else {
        return getTimestampCreated(superClass);
      }
    }
  }

  public Class getCreatedByClazz() {
    return convertClassNameToClass(DataModelHelper.getInstance().getCREATED_BY_CLASS_NAME());
  }

  /**
   *
   * @param clazz
   * @return
   */
  public Field getCreatedByField(Class clazz) { 
    try {
      return clazz.getDeclaredField(DataModelHelper.getInstance().getCREATED_BY_FIELD());
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        return null;
      } else {
        return getCreatedByField(superClass);
      }
    }
  }

  /**
   * Find id field name for the entity bean
   *
   * @param bean
   * @return String, name of the id field of this entity bean
   */
  public String getIDFieldName(EntityBean bean) {
    return getIDFieldName(bean.getClass());
  }

  public String getIDFieldName(Class clazz) {
    return getIdField(clazz.getDeclaredFields()).getName();
  }

  private Field getIdField(Field[] fields) {
    try {
      return Arrays.asList(fields)
              .stream()
              .filter(f -> f.isAnnotationPresent(Id.class))
              .findAny()
              .get();
    } catch (java.util.NoSuchElementException ex) {
      throw new DinaException(ErrorMsg.getInstance().getNoIdAnnotatedFieldMsg(), 400);
    }
  }

  /**
   * Find id field name for the entity bean
   *
   * @param bean
   * @return String, name of the id field of this entity bean
   */
  public Field getIDField(EntityBean bean) {
    return getIdField(bean.getClass().getDeclaredFields());
  }

}

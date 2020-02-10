package se.nrm.dina.data.util;

import java.lang.reflect.Field;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import se.nrm.dina.data.exceptions.DinaException;
import se.nrm.dina.datamodel.BaseEntity;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;
import se.nrm.dina.datamodel.util.DataModelHelper;

/**
 *
 * @author idali
 */
public class JpaReflectionHelperNGTest {

  private JpaReflectionHelper testInstance;

  public JpaReflectionHelperNGTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  /**
   * Test of getInstance method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");

    testInstance = null;
    testInstance = JpaReflectionHelper.getInstance();

    assertNotNull(testInstance);
  }

  @Test
  public void testGetInstance1() {
    System.out.println("getInstance");

    testInstance = new JpaReflectionHelper();
    testInstance = JpaReflectionHelper.getInstance();

    assertNotNull(testInstance);
  }

  /**
   * Test of convertClassNameToClass method, of class JpaReflectionHelper.
   */
  @Test
  public void testConvertClassNameToClass() {
    System.out.println("convertClassNameToClass");

    String classname = "Testentity";
    Class expResult = Testentity.class;

    testInstance = new JpaReflectionHelper();

    Class result = testInstance.convertClassNameToClass(classname);
    assertEquals(result, expResult);
    assertEquals(result.getSimpleName(), classname);
  }

  @Test(expected = DinaException.class)
  public void testConvertClassNameToClassFailure() {
    System.out.println("convertClassNameToClass");

    String classname = "testFailure";

    testInstance = new JpaReflectionHelper();
    Class result = testInstance.convertClassNameToClass(classname);
    assertNull(result);
  }

  /**
   * Test of reformClassName method, of class JpaReflectionHelper.
   */
  @Test
  public void testReformClassName() {

    System.out.println("reformClassName");

    String entityName = "testEntity";
    String expResult = "Testentity";

    testInstance = new JpaReflectionHelper();
    String result = testInstance.reformClassName(entityName);
    assertEquals(result, expResult);
  }

  @Test
  public void testReformClassNameEmpty() {

    System.out.println("reformClassName");

    String entityName = "";
    String expResult = "";

    testInstance = new JpaReflectionHelper();
    String result = testInstance.reformClassName(entityName);
    assertEquals(result, expResult);
  }

  /**
   * Test of createNewInstance method, of class JpaReflectionHelper.
   */
  @Test
  public void testCreateNewInstance() {
    System.out.println("createNewInstance");

    Class clazz = Testentity.class;

    testInstance = new JpaReflectionHelper();
    Testentity result = testInstance.createNewInstance(clazz);
    assertNotNull(result);
    assertSame(clazz, result.getClass());
  }

  @Test(expected = DinaException.class)
  public void testCreateNewInstanceFailure() {
    System.out.println("createNewInstance");

    Class clazz = EntityBean.class;

    testInstance = new JpaReflectionHelper();
    Testentity result = testInstance.createNewInstance(clazz);
    assertNull(result);
  }

  @Test(expected = DinaException.class)
  public void testCreateNewInstanceFailure1() {
    System.out.println("createNewInstance");

    Class clazz = BaseEntity.class;

    testInstance = new JpaReflectionHelper();
    Testentity result = testInstance.createNewInstance(clazz);
    assertNull(result);
  }

  /**
   * Test of validateEntityName method, of class JpaReflectionHelper.
   */
  @Test
  public void testValidateEntityName() {
    System.out.println("validateEntityName");

    String entityName = "testEntity";

    testInstance = new JpaReflectionHelper();
    String result = testInstance.validateEntityName(entityName);
    assertEquals(result, Testentity.class.getSimpleName());
  }

  @Test(expected = Throwable.class)
  public void testValidateEntityNameFailure() {
    System.out.println("validateEntityName");

    String entityName = "baseentity";

    testInstance = new JpaReflectionHelper();
    String result = testInstance.validateEntityName(entityName);
    verify(testInstance, times(1)).convertClassNameToClass(entityName);
    verify(testInstance, times(1)).createNewInstance(testInstance.convertClassNameToClass(entityName));
    assertNull(result);
  }

  /**
   * Test of isIntField method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsIntFieldInt() {

    System.out.println("isIntField");

    testInstance = new JpaReflectionHelper();
    Class clazz = Testentity.class;
    String fieldName = "version";
    boolean result = testInstance.isIntField(clazz, fieldName);
    assertTrue(result);
  }

  @Test
  public void testIsIntFieldInteger() {

    System.out.println("isIntField");

    Class clazz = Testentity.class;
    String fieldName = "id";
    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isIntField(clazz, fieldName);
    assertTrue(result);
  }

  @Test
  public void testIsIntFieldFalse() {

    System.out.println("isIntField");

    Class clazz = Testentity.class;
    String fieldName = "bgDecimal";
    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isIntField(clazz, fieldName);
    assertFalse(result);
  }

  @Test(expected = DinaException.class)
  public void testIsIntFieldFailure() {

    System.out.println("isIntField");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isIntField(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of isIntField method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsShortFieldTrue() {

    System.out.println("isShortField");

    Class clazz = Testentity.class;
    String fieldName = "s";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isShortField(clazz, fieldName);
    assertTrue(result);
  }

  @Test
  public void testIsShortFieldFalse() {

    System.out.println("isShortField");

    Class clazz = Testentity.class;
    String fieldName = "version";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isShortField(clazz, fieldName);
    assertFalse(result);
  }

  @Test(expected = DinaException.class)
  public void testIsShortFieldFailure() {

    System.out.println("isShortField");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isShortField(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of isEntity method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsEntity() {
    System.out.println("isEntity");

    Class clazz = Testentity.class;
    String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isEntity(clazz, fieldName);

    assertTrue(result);
  }

  @Test
  public void testIsEntityFalse() {
    System.out.println("isEntity");

    Class clazz = Testentity.class;
    String fieldName = "version";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isEntity(clazz, fieldName);
    assertFalse(result);
  }

  @Test(expected = DinaException.class)
  public void testIsEntityFailure() {
    System.out.println("isEntity");

    String fieldName = "testField";
    Class clazz = Testentity.class;

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isEntity(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of isCollection method, of class JpaReflectionHelper.
   */
//    @Test
  public void testIsCollection() {
    System.out.println("isCollection");

    Class clazz = Testentity.class;
    String fieldName = "testList";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isList(clazz, fieldName);
    assertTrue(result);
  }

  @Test
  public void testIsCollectionFalse() {
    System.out.println("isCollection");

    Class clazz = Testentity.class;
    String fieldName = "version";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isList(clazz, fieldName);
    assertFalse(result);
  }

  @Test(expected = DinaException.class)
  public void testIsCollectionFailure() {
    System.out.println("isCollection");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isList(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of isDate method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsDate() {
    System.out.println("isDate");

    Class clazz = Testentity.class;
    String fieldName = DataModelHelper.getInstance().getTIME_CREATED_FIELD();

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isDate(clazz, fieldName);
    assertTrue(result);
  }

  /**
   * Test of isDate method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsDateFalse() {
    System.out.println("isDate");

    Class clazz = Testentity.class;
    String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();
    testInstance = new JpaReflectionHelper();

    boolean result = testInstance.isDate(clazz, fieldName);
    assertFalse(result);
  }

  @Test(expected = DinaException.class)
  public void testIsDateFailure() {
    System.out.println("isDate");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isDate(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of isBigDecimal method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsBigDecimal() {
    System.out.println("isBigDecimal");

    Class clazz = Testentity.class;
    String fieldName = "bgDecimal";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isBigDecimal(clazz, fieldName);
    assertTrue(result);
  }

  /**
   * Test of isBigDecimal method, of class JpaReflectionHelper.
   */
  @Test
  public void testIsBigDecimalFalse() {
    System.out.println("isBigDecimal");

    Class clazz = Testentity.class;
    String fieldName = "version";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isBigDecimal(clazz, fieldName);
    assertFalse(result);
  }

  /**
   * Test of isBigDecimal method, of class JpaReflectionHelper.
   */
  @Test(expected = DinaException.class)
  public void testIsBigDecimalFailure() {
    System.out.println("isBigDecimal");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isBigDecimal(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of getEntity method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetEntity() {
    System.out.println("getEntity");

    Class clazz = Testentity.class;
    String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();

    testInstance = new JpaReflectionHelper();
    EntityBean result = testInstance.getEntity(clazz, fieldName);

    assertNotNull(result);
  }

  @Test(expected = DinaException.class)
  public void testGetEntityFailure1() {
    System.out.println("getEntity");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    EntityBean result = testInstance.getEntity(clazz, fieldName);

    assertNull(result);
  }

  @Test(expected = DinaException.class)
  public void testGetEntityFailure() {
    System.out.println("getEntity");

    Class clazz = Testentity.class;
    String fieldName = "timestampCreated";

    testInstance = new JpaReflectionHelper();
    EntityBean result = testInstance.getEntity(clazz, fieldName);

    assertNull(result);
  }

  @Test(expected = DinaException.class)
  public void testGetEntityFailure2() {
    System.out.println("getEntity");

    Class clazz = Testentity.class;
    String fieldName = "version";

    testInstance = new JpaReflectionHelper();
    EntityBean result = testInstance.getEntity(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of getValueType method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetValueTypeInt() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = "version";

    ValueType expResult = ValueType.INT;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

  @Test
  public void testGetValueTypeEntity() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();

    ValueType expResult = ValueType.ENTITY;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

  @Test
  public void testGetValueTypeBigDecimal() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = "bgDecimal";

    ValueType expResult = ValueType.BIGDECIMAL;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

  @Test
  public void testGetValueTypeDate() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = DataModelHelper.getInstance().getTIME_CREATED_FIELD();

    ValueType expResult = ValueType.DATE;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

//    @Test
  public void testGetValueTypeList() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = "testList";

    ValueType expResult = ValueType.LIST;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

  @Test
  public void testGetValueTypeShort() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = "s";

    ValueType expResult = ValueType.SHORT;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

  @Test
  public void testGetValueTypeString() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = "string";

    ValueType expResult = ValueType.STRING;

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertEquals(result, expResult);
  }

  @Test(expected = DinaException.class)
  public void testGetValueTypeStringFailure() {

    System.out.println("getValueType");

    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    ValueType result = testInstance.getValueType(clazz, fieldName);
    assertNull(result);
  }

  /**
   * Test of validateFields method, of class JpaReflectionHelper.
   */
  @Test
  public void testValidateFields() {
    System.out.println("validateFields");
    Class clazz = Testentity.class;
    String fieldName = "version";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.validateFields(clazz, fieldName);
    assertTrue(result);
  }

  @Test
  public void testValidateFieldsFalse() {
    System.out.println("validateFields");
    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.validateFields(clazz, fieldName);
    assertFalse(result);
  }

  /**
   * Test of getIDFieldName method, of class JpaReflectionHelper.
   */
  @Test
  public void testValidateFields1() {
    System.out.println("validateFields");
    Class clazz = Testentity.class;
    String fieldName = "testField";

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.validateFields(clazz, fieldName);
    assertFalse(result);
  }

  @Test
  public void testIsVersionedTrue() {
    System.out.println("isVersioned");
    Class clazz = Testentity.class;

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isVersioned(clazz);

    assertTrue(result);
  }

  @Test
  public void testIsVersionedFalse() {
    System.out.println("isVersioned");
    Class clazz = EntityBean.class;

    testInstance = new JpaReflectionHelper();
    boolean result = testInstance.isVersioned(clazz);

    assertFalse(result);
  }

  /**
   * Test of getTimestampCreated method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetTimestampCreated() {
    System.out.println("getTimestampCreated");

    Class clazz = Testentity.class;

    String expected = DataModelHelper.getInstance().getTIME_CREATED_FIELD();

    testInstance = new JpaReflectionHelper();
    Field result = testInstance.getTimestampCreated(clazz);
    assertEquals(result.getName(), expected);
  }

  @Test
  public void testGetTimestampCreatedNull() {
    System.out.println("getTimestampCreated");

    Class clazz = EntityBean.class;

    testInstance = new JpaReflectionHelper();
    Field result = testInstance.getTimestampCreated(clazz);
    assertNull(result);
  }

  /**
   * Test of getCreatedByClazz method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetCreatedByClass() {
    System.out.println("testGetCreatedByClazz");

    String expactResult = DataModelHelper.getInstance().getCREATED_BY_CLASS_NAME();

    testInstance = new JpaReflectionHelper();
    Class result = testInstance.getCreatedByClazz();
    assertEquals(expactResult, result.getSimpleName());
  }

  /**
   * Test of getCreatedByField method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetCreatedByField() {
    System.out.println("getCreatedByField");

    Class clazz = Testentity.class;

    String expectedFieldName = DataModelHelper.getInstance().getCREATED_BY_FIELD();

    testInstance = new JpaReflectionHelper();
    Field result = testInstance.getCreatedByField(clazz);
    assertEquals(result.getName(), expectedFieldName);
  }

  @Test
  public void testGetCreatedByFieldNull() {
    System.out.println("getCreatedByField");

    Class clazz = BaseEntity.class;

    testInstance = new JpaReflectionHelper();
    Field result = testInstance.getCreatedByField(clazz);
    assertNull(result);
  }

  /**
   * Test of getIDFieldName method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetIDFieldNameEntityBean() {
    System.out.println("getIDFieldName");

    EntityBean bean = new Testentity();

    testInstance = new JpaReflectionHelper();
    String result = testInstance.getIDFieldName(bean);

    assertEquals(result, "id");
  }

  /**
   * Test of getIDFieldName method, of class JpaReflectionHelper.
   */
  @Test
  public void testGetIDFieldNameClass() {
    System.out.println("getIDFieldName");
    Class clazz = Testentity.class;

    testInstance = new JpaReflectionHelper();
    String result = testInstance.getIDFieldName(clazz);
    assertEquals(result, "id");
  }

  @Test(expected = DinaException.class)
  public void testGetIDFieldNameFailure() {
    System.out.println("getIDFieldName");

    Class clazz = BaseEntity.class;

    testInstance = new JpaReflectionHelper();
    String result = testInstance.getIDFieldName(clazz);
    assertNull(result);
  }

  @Test
  public void testGetIDField() {
    System.out.println("testGetIDField");
    EntityBean bean = new Testentity();

    testInstance = new JpaReflectionHelper();
    Field result = testInstance.getIDField(bean);
    assertEquals(result.getName(), "id");
  }
}

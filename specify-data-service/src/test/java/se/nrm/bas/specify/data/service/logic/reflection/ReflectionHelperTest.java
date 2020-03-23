package se.nrm.bas.specify.data.service.logic.reflection;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Agent;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
public class ReflectionHelperTest {
  
  private ReflectionHelper instance;
  
  public ReflectionHelperTest() {
  }
 
  @Before
  public void setUp() {
    instance = ReflectionHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class ReflectionHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    assertNotNull(instance); 
  }

  /**
   * Test of getStringValueFromMultipleFields method, of class ReflectionHelper.
   */
  @Test
  public void testGetStringValueFromMultipleFields() {
    System.out.println("getStringValueFromMultipleFields");
     
    Testentity bean = new Testentity();
    bean.setString("test");
    bean.setS((short)2);
    
    List<String> fields = new ArrayList();
    fields.add("string");
    fields.add("s");
     
    String expResult = "test 2";
    String result = instance.getStringValueFromMultipleFields(bean, fields);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetStringValueFromMultipleFieldsWithNull() {
    System.out.println("getStringValueFromMultipleFields");
     
    Testentity bean = new Testentity(); 
    bean.setS((short)2);
    
    List<String> fields = new ArrayList();
    fields.add("string");
    fields.add("s"); 
    
    String expResult = "2";
    String result = instance.getStringValueFromMultipleFields(bean, fields);
    System.out.println("result..." + result);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getValueFromFieldOrMethod method, of class ReflectionHelper.
   */
  @Test
  public void testGetValueFromFieldOrMethod() {
    System.out.println("getValueFromFieldOrMethod");
    
    Testentity bean = new Testentity(); 
    bean.setString("test");
    String fieldName = "string"; 
    Object expResult = "test";
    Object result = instance.getValueFromFieldOrMethod(bean, fieldName);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getFieldValue method, of class ReflectionHelper.
   */
  @Test
  public void testGetFieldValue() {
    System.out.println("getFieldValue");
    Testentity bean = new Testentity(); 
    bean.setString("test");
    String fieldName = "string"; 
    Object expResult = "test";
    Object result = instance.getValueFromFieldOrMethod(bean, fieldName);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetFieldValueWithMethod() {
    System.out.println("getFieldValue");
    Testentity bean = new Testentity(); 
    bean.setS((short)2);
    String fieldName = "s"; 
    short expResult = 2;
    Object result = instance.getValueFromFieldOrMethod(bean, fieldName);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetFieldValueWithMethod2() {
    System.out.println("getFieldValue");
    Testentity bean = new Testentity(); 
    bean.setS((short)2);
    String fieldName = "S"; 
    short expResult = 2;
    Object result = instance.getValueFromFieldOrMethod(bean, fieldName);
    assertEquals(expResult, result); 
  }
   
  @Test
  public void testGetFieldValueNull() {
    System.out.println("getFieldValue");
    Testentity bean = new Testentity();  
    Object result = instance.getValueFromFieldOrMethod(bean, "test");
    assertNull(result); 
  }



  /**
   * Test of getMethodValue method, of class ReflectionHelper.
   */
  @Test
  public void testGetMethodValue() {
    System.out.println("getMethodValue");
    Testentity bean = new Testentity(); 
    bean.setS((short)2);
    String fieldName = "S"; 
    short expResult = 2;
    Object result = instance.getValueFromFieldOrMethod(bean, fieldName);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getChildFromParent method, of class ReflectionHelper.
   */
  @Test
  public void testGetChildFromParent() {
    System.out.println("getChildFromParent");
    Testentity bean = new Testentity(); 
    Agent agent = new Agent();
    String childName = "createdByAgentID"; 
    bean.setCreatedByAgentID(agent); 
    EntityBean result = instance.getChildFromParent(bean, childName);
    assertEquals(agent, result); 
  }

  /**
   * Test of getChildListFromParent method, of class ReflectionHelper.
   */
  @Test
  public void testGetChildListFromParent() {
    System.out.println("getChildListFromParent");
     
    List<EntityBean> list = new ArrayList();
    Testentity bean = new Testentity(); 
    bean.setBeans(list);
    String childName = "beans"; 
    List<EntityBean> expResult = list;
    List<EntityBean> result = instance.getChildListFromParent(bean, childName);
    assertEquals(expResult, result); 
  }

  /**
   * Test of isCollection method, of class ReflectionHelper.
   */
  @Test
  public void testIsCollection() {
    System.out.println("isCollection");
    Class clazz = Testentity.class;
    String fieldName = "beans"; 
    boolean expResult = true;
    boolean result = instance.isCollection(clazz, fieldName);
    assertEquals(expResult, result); 
  }

  /**
   * Test of isList method, of class ReflectionHelper.
   */
  @Test
  public void testIsList() {
    System.out.println("isList");
    Class clazz = Testentity.class;
    String fieldName = "beans"; 
    boolean expResult = true;
    boolean result = instance.isCollection(clazz, fieldName);
    assertEquals(expResult, result); 
  }
  
}

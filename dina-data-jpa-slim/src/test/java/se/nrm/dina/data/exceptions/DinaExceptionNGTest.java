/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.data.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

import se.nrm.dina.data.vo.ErrorBean;

/**
 *
 * @author idali
 */
public class DinaExceptionNGTest {

  private static DinaException instance;

  private static ErrorBean errorBean;
  private static List<ErrorBean> errorBeans;
  private static int errorCode;

  public DinaExceptionNGTest() {
  }

  @BeforeClass
  public static void setUp() throws Exception {
    errorCode = 400;
    errorBean = new ErrorBean("testName", "test error");
    errorBeans = new ArrayList<>();
    errorBeans.add(errorBean);
  }

  @AfterClass
  public static void tearDown() throws Exception {
    instance = null;
  }

  @Test
  public void testDinaExceptionConstruct() {
    instance = new DinaException();
    assertNotNull(instance);
    assertEquals(0, instance.getErrorCode());
  }

  @Test
  public void testDinaExceptionConstructWithMsg() {
    instance = new DinaException("test error");
    assertNotNull(instance);
    assertEquals("test error", instance.getMessage());
  }

  @Test
  public void testDinaExceptionConstructWithMsgAndErrorCode() {
    instance = new DinaException("test error", errorCode);
    assertNotNull(instance);
    assertEquals("test error", instance.getMessage());
    assertEquals(400, instance.getErrorCode());
  }

  /**
   * Test of getErrorCode method, of class DinaException.
   */
  @Test
  public void testGetErrorCode() {
    System.out.println("getErrorCode");

    instance = new DinaException();
    int expResult = 0;
    int result = instance.getErrorCode();
    assertEquals(result, expResult);
  }

  /**
   * Test of getErrorBean method, of class DinaException.
   */
  @Test
  public void testGetErrorBean() {
    System.out.println("getErrorBean");
    instance = new DinaException(errorBean, 400);
    ErrorBean result = instance.getErrorBean();
    assertEquals(result, errorBean);
    assertEquals("test error", errorBean.getErrorMsg());
    assertEquals("testName", errorBean.getEntityName());
  }

  /**
   * Test of getErrorBeans method, of class DinaException.
   */
  @Test
  public void testGetErrorBeans() {
    System.out.println("getErrorBeans");
    instance = new DinaException(errorBeans, 400);

    List result = instance.getErrorBeans();
    assertNotNull(result);
    assertEquals(result, errorBeans);
    assertEquals(result.size(), 1);
  }
}

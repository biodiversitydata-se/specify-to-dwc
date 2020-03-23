package se.nrm.bas.specify.data.service.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author idali
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({se.nrm.bas.specify.data.service.logic.reflection.ReflectionSuite.class, se.nrm.bas.specify.data.service.logic.propertyfiles.PropertyfilesSuite.class, se.nrm.bas.specify.data.service.logic.SolrIndexBuilderLogicTest.class, se.nrm.bas.specify.data.service.logic.InitialPropertiesTest.class, se.nrm.bas.specify.data.service.logic.json.JsonSuite.class, se.nrm.bas.specify.data.service.logic.util.UtilSuite.class, se.nrm.bas.specify.data.service.logic.data.DataSuite.class})
public class LogicSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }
  
}

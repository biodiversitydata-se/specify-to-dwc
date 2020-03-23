package se.nrm.bas.specify.data.service;

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
@Suite.SuiteClasses({se.nrm.bas.specify.data.service.logic.LogicSuite.class, 
  se.nrm.bas.specify.data.service.RestApplicationTest.class, 
  se.nrm.bas.specify.data.service.solr.SolrSuite.class, se.nrm.bas.specify.data.service.CORSFilterTest.class})
public class ServiceSuite {

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

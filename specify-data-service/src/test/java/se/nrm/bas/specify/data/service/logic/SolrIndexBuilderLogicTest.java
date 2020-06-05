package se.nrm.bas.specify.data.service.logic;
  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.junit.After; 
import org.junit.AfterClass;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;   
import org.mockito.Mock; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.bas.specify.data.service.logic.data.DataReader;
import se.nrm.bas.specify.data.service.logic.json.JsonConverter;
import se.nrm.bas.specify.data.service.logic.propertyfiles.MappingFileReader;
import se.nrm.bas.specify.data.service.logic.util.Util;
import se.nrm.bas.specify.data.service.solr.SolrIndexBuilder;
import se.nrm.dina.datamodel.EntityBean;
import se.nrm.dina.datamodel.impl.Testentity;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class SolrIndexBuilderLogicTest {
  
  private SolrIndexBuilderLogic instance;
  
  private String institution;
  private int collectionCode;
  private String strFromDate;
  private String strToDate;
  private Date fromDate;
  private Date toDate; 
  private static InputStream fis; 
  private static Testentity testBean; 
  
  @Mock
  private DataReader reader;
  @Mock
  private JsonConverter converter;
  @Mock
  private SolrIndexBuilder indexBuilder; 
  @Mock
  private InitialProperties properties; 
  @Mock
  private MappingFileReader mappingFileReader;
   
   
  private static JsonObject json;
  private static Map<String, String> map;
    
  public SolrIndexBuilderLogicTest() {
  }
  
  @BeforeClass
  public static void setUpClass() { 
    testBean = new Testentity(10);
    testBean.setString("test string");
    testBean.setS((short) 18);
    testBean.setBgDecimal(BigDecimal.valueOf(18.7)); 
    testBean.setIsTrue(true);  
    
    
    String mappingFilePath = "src/test/resources/mapping.json";  
    try {
      fis = new FileInputStream(mappingFilePath);  
    } catch (FileNotFoundException ex) {
      System.out.println("error" + ex.getMessage());
    }
    json = Json.createReader(fis).readObject(); 
    
    map = new HashMap();
  }
  
    
    
  @AfterClass
  public static void tearDownClass() {
    if(fis != null) {
      try {
        fis.close();
      } catch (IOException ex) {
      }
    }
    json = null;
  }
  
  
 
  @Before
  public void setUp() {
    institution = "nrm";
    collectionCode = 163840;
    strFromDate = "2018-01-01";
    strToDate = "2018-02-01";
    fromDate = Util.getInstance().stringToDate(strFromDate);
    toDate = Util.getInstance().stringToDate(strToDate); 
    
    String solrPath = "http://localhost:8983";
    when(properties.getSolrPath()).thenReturn(solrPath);  
    when(mappingFileReader.read(any(String.class))).thenReturn(json);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  @Test
  public void testDefaultConstructor() {
    instance = new SolrIndexBuilderLogic();
    assertNotNull(instance);
  }
   
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructorException() {
    instance = new SolrIndexBuilderLogic();
    instance.init();
  }
   
  @Test
  public void testInit() {
    System.out.println("init");  
    
    instance = new SolrIndexBuilderLogic(reader, converter, indexBuilder, properties, mappingFileReader);
    instance.init();  
  }
  
  @Test(expected = NullPointerException.class)
  public void testException1() {
    instance = new SolrIndexBuilderLogic(); 
    instance.run(institution, collectionCode, strFromDate, strToDate); 
  }

  /**
   * Test of run method, of class SolrIndexBuilderLogic.
   */
  @Test
  public void testRunWith200() {
    System.out.println("run");
     
    boolean isNrm = true;
    
    List<Integer> ids = new ArrayList();
    ids.add(80);
    ids.add(81);
    ids.add(82);
     
    List<EntityBean> list = new ArrayList();
    list.add(new Testentity(80));
    list.add(new Testentity(81));
    list.add(new Testentity(82));
     
    
    when(reader.getCollectionIds(any(Integer.class), any(Date.class), 
            any(Date.class), any(Boolean.class))).thenReturn(ids);
    when(reader.fetchData(collectionCode, fromDate, toDate, ids, isNrm, null)).thenReturn(list); 
    when(converter.convert(any(List.class), any(String.class), any(Integer.class), any(JsonObject.class))).thenReturn(JsonValue.EMPTY_JSON_ARRAY); 
    when(indexBuilder.postToSolr(any(String.class), any(String.class))).thenReturn(200); 
  
     
    instance = new SolrIndexBuilderLogic(reader, converter, indexBuilder, properties, mappingFileReader);
    instance.init();
    
    int result = instance.run(institution, collectionCode, strFromDate, strToDate);
    assertEquals(200, result); 
     
    verify(reader, times(1)).getCollectionIds(any(Integer.class), 
            any(Date.class), any(Date.class), any(Boolean.class)); 
    verify(reader, times(1)).fetchData(collectionCode, fromDate, toDate, ids, isNrm, map); 
    verify(converter, times(1)).convert(any(List.class), any(String.class), any(Integer.class), any(JsonObject.class)); 
    verify(indexBuilder, times(1)).postToSolr(any(String.class), any(String.class)); 
  }
  
  @Test
  public void testRunWith400() {
    System.out.println("run");
     
    boolean isNrm = true;
    
    List<Integer> ids = new ArrayList();
    ids.add(80);
    ids.add(81);
    ids.add(82);
     
    List<EntityBean> list = new ArrayList();
    list.add(new Testentity(80));
    list.add(new Testentity(81));
    list.add(new Testentity(82));
    
    when(reader.getCollectionIds(any(Integer.class), any(Date.class), 
            any(Date.class), any(Boolean.class))).thenReturn(ids);
    when(reader.fetchData(collectionCode, fromDate, toDate, ids, isNrm, null)).thenReturn(list); 
    when(converter.convert(any(List.class), any(String.class), any(Integer.class), any(JsonObject.class))).thenReturn(JsonValue.EMPTY_JSON_ARRAY); 
    when(indexBuilder.postToSolr(any(String.class), any(String.class))).thenReturn(400); 
     
    instance = new SolrIndexBuilderLogic(reader, converter, indexBuilder, properties, mappingFileReader);
    int result = instance.run(institution, collectionCode, strFromDate, strToDate);
    assertEquals(400, result); 
    
    verify(reader, times(1)).getCollectionIds(any(Integer.class), 
            any(Date.class), any(Date.class), any(Boolean.class)); 
    verify(reader, times(1)).fetchData(collectionCode, fromDate, toDate, ids, isNrm, map); 
    verify(converter, times(1)).convert(any(List.class), any(String.class), any(Integer.class), any(JsonObject.class)); 
    verify(indexBuilder, times(1)).postToSolr(any(String.class), any(String.class)); 
  }
  
  @Test
  public void testRunWithLargeData() {
    
    List<Integer> ids = new ArrayList<>();
    for(int i = 0; i <= 2500; i++) {
      ids.add(i);
    }
     
    List<EntityBean> list = new ArrayList();  
    list.add(new Testentity(80));
    list.add(new Testentity(81));
    list.add(new Testentity(82));
    
    when(reader.getCollectionIds(any(Integer.class), any(Date.class), 
            any(Date.class), any(Boolean.class))).thenReturn(ids); 
    when(reader.fetchData(collectionCode, fromDate, toDate, ids, true, null)).thenReturn(list); 
    when(converter.convert(any(List.class), any(String.class), any(Integer.class), any(JsonObject.class))).thenReturn(JsonValue.EMPTY_JSON_ARRAY); 
    when(indexBuilder.postToSolr(any(String.class), any(String.class))).thenReturn(200); 
   
    instance = new SolrIndexBuilderLogic(reader, converter, indexBuilder, properties, mappingFileReader);
    int result = instance.run(institution, collectionCode, strFromDate, strToDate); 
    assertEquals(200, result); 
    
    verify(reader, times(1)).getCollectionIds(any(Integer.class), 
            any(Date.class), any(Date.class), any(Boolean.class)); 
    verify(reader, times(3)).fetchData(any(Integer.class), any(Date.class), any(Date.class), any(List.class), any(Boolean.class), any(Map.class)); 
    verify(converter, times(3)).convert(any(List.class), any(String.class), any(Integer.class), any(JsonObject.class)); 
    verify(indexBuilder, times(3)).postToSolr(any(String.class), any(String.class)); 
  }
  
}

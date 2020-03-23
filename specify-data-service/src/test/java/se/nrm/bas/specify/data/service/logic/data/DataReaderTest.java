package se.nrm.bas.specify.data.service.logic.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*; 
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.bas.specify.data.service.logic.util.Util;
import se.nrm.dina.data.jpa.DinaDao;
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class DataReaderTest {
  
  @Mock
  private DinaDao dao;
  
  private DataReader instance;
   
  private int collectionId;
  private Date fromDate;
  private Date toDate;
  private boolean isNrm;
  
  private List<Integer> mockedIdList; 
           
  public DataReaderTest() {
  }
  
  @Before
  public void setUp() {
    collectionId = 2878; 
    String strFromDate = "2018-01-01";
    String strToDate = "2018-02-01";
    fromDate = Util.getInstance().stringToDate(strFromDate);
    toDate = Util.getInstance().stringToDate(strToDate); 
    isNrm = true;  
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new DataReader();
    assertNotNull(instance);
  }

  @Test(expected = RuntimeException.class)
  public void testDefaultConstructorException() {
    instance = new DataReader();
    assertNotNull(instance);
    instance.getCollectionIds(collectionId, fromDate, toDate, isNrm);  
  }
   
  /**
   * Test of getCollectionIds method, of class DataReader.
   */
  @Test
  public void testGetCollectionIds() {
    System.out.println("getCollectionIds");
    
    mockedIdList = Mockito.mock(ArrayList.class);
    when(dao.findAllIds(any(Integer.class), any(Date.class), any(Date.class), any(Boolean.class))).thenReturn(mockedIdList);
 
    instance = new DataReader(dao); 
    List<Integer> result = instance.getCollectionIds(collectionId, fromDate, toDate, isNrm);
    assertNotNull(result); 
    
    verify(dao, times(1)).findAllIds(any(Integer.class), any(Date.class), any(Date.class), any(Boolean.class)); 
  }

  /**
   * Test of fetchData method, of class DataReader.
   */
  @Test
  public void testFetchData() {
    System.out.println("fetchData");
  
    List<EntityBean> list = new ArrayList();
    Stream<EntityBean> stream = mock(Stream.class); 
 
    when(stream.collect(Collectors.toList())).thenReturn(list);
    
    when(dao.findByCollectonId(any(Integer.class), any(Boolean.class), any(Date.class), any(Date.class), any(List.class))).thenReturn(stream);
     
    List<Integer> ids = new ArrayList();
    instance = new DataReader(dao); 
    instance.fetchData(collectionId, fromDate, toDate, ids, isNrm);
    verify(dao, times(1)).findByCollectonId(any(Integer.class), any(Boolean.class), any(Date.class), any(Date.class), any(List.class));
  }
  
}

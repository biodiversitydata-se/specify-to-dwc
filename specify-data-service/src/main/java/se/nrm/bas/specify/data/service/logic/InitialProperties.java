package se.nrm.bas.specify.data.service.logic;

import java.io.Serializable; 
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Slf4j
public class InitialProperties implements Serializable {
  
  private final static String CONFIG_INITIALLISING_ERROR = "Property not initialized";
 
  private String solrPath;
  private String defaultMappingFilePath;

  public InitialProperties() {
  }

  @Inject
  public InitialProperties(@ConfigurationValue("swarm.solr.path") String solrPath, 
          @ConfigurationValue("swarm.mapping-files.path") String defaultMappingFilePath) { 
    this.solrPath = solrPath;
    this.defaultMappingFilePath = defaultMappingFilePath;
    log.info("test injection : {} ", solrPath);
  }
  
  public String getSolrPath() {
    if (solrPath == null) {
      throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
    }
    return solrPath;
  } 
  
  public String getDefaultMappingFilePath() {
    if (defaultMappingFilePath == null) {
      throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
    }
    return defaultMappingFilePath;
  } 
}

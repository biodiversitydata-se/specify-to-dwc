package se.nrm.bas.specify.data.service;
 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag; 
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import se.nrm.bas.specify.data.service.logic.SolrIndexBuilderLogic; 
import se.nrm.bas.specify.data.service.logic.util.Util;

/**
 *
 * @author idali
 */ 
@Path("/")
@Api(tags = {"specifyToDwc"})
@SwaggerDefinition(tags = {
  @Tag(name = "specifyToDwc", description = "Export specify data to solr in DWC format")
})
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class SpecifyDataEndpoint {
   
  private final int badRequestStatusCode = 400; 
  private int statusCode;
   
  @Inject  
  private SolrIndexBuilderLogic logic;

  @GET
  @Path("/run")
  @ApiOperation(value = "Run service",
          notes = "Returns status",
          response = String.class
  )
  @Produces(MediaType.APPLICATION_JSON)
  public Response run(@QueryParam("inst") String institution,
          @QueryParam("collCode") int collectionCode,
          @QueryParam("from") String fromDate,
          @QueryParam("to") String toDate) {  
     
    if(institution == null || institution.isEmpty()) {
      return Response.status(badRequestStatusCode)
              .entity(Util.getInstance().getSolrPostResponse(badRequestStatusCode)).build();
    } 
    if(collectionCode == 0) {
      return Response.status(badRequestStatusCode)
              .entity(Util.getInstance().getSolrPostResponse(badRequestStatusCode)).build();
    } 
    statusCode = logic.run(institution, collectionCode, fromDate, toDate);
    return Response.status(statusCode)
              .entity(Util.getInstance().getSolrPostResponse(statusCode)).build(); 
  }
}

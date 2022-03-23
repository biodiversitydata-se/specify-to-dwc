package se.nrm.bas.specify.data.service;
 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag; 
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
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
  private final String processRunning = "Process running .....";
   
  @Inject  
  private SolrIndexBuilderLogic logic;

  @GET
  @Path("/run")
  @ApiOperation(value = "Run service",
          notes = "Returns status",
          response = String.class
  )
  @Produces(MediaType.APPLICATION_JSON)
  public void run(@QueryParam("inst") String institution,
          @QueryParam("collCode") int collectionCode,
          @QueryParam("from") String fromDate,
          @QueryParam("to") String toDate, @Suspended AsyncResponse asycResponse) {  
    
    log.info("Initially invoked on thread - {} . This will free up soon !", 
            Thread.currentThread().getName());
     
    Response response;
    if(institution == null || institution.isEmpty()) {
      response = Response.status(badRequestStatusCode)
              .entity(Util.getInstance()
                      .getSolrPostResponse(badRequestStatusCode)).build();
      asycResponse.resume(response);
      return;
    } 
    if(collectionCode == 0) {
      response = Response.status(badRequestStatusCode)
              .entity(Util.getInstance().getSolrPostResponse(badRequestStatusCode)).build();
      asycResponse.resume(response);
      return;
    } 
    
    asycResponse.setTimeout(10, TimeUnit.SECONDS); 
    //client will recieve a HTTP 408 (timeout error) after 10 seconds
    asycResponse.setTimeoutHandler((asyncResp) -> {
      asyncResp.resume(Response.status(Response.Status.OK).entity(processRunning).build());
    }); 
  
     new Thread() {
         @Override
         public void run() {
            statusCode = logic.run(institution, collectionCode, fromDate, toDate); 
            Response response = Response.status(statusCode)
                    .entity(Util.getInstance().getSolrPostResponse(statusCode)).build(); 
            asycResponse.resume(response);
         }
      }.start();
     
//    return Response.status(statusCode)
//              .entity(Util.getInstance().getSolrPostResponse(statusCode)).build(); 
  }
}

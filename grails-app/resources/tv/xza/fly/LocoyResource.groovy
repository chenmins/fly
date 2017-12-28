package tv.xza.fly

import grails.converters.JSON
import grails.converters.XML
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Api(value = "locoy", description = "火车头相关接口")
@Path('/api/locoy')
class LocoyResource {

    ColumnService columnService

    @GET
    @Path('/getAllColumns')
    @Produces(MediaType.APPLICATION_XML)
    @ApiOperation(value="获得所有分类")
    String getAllColumns() {
         return columnService.list() as XML
    }
    @POST
    @Path('/addTopic')
    //@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="获得所有分类")
    String addTopic(Topic topic){
        println topic
        topic.user = session.user
        topic.save()
        return topic as JSON
    }


}
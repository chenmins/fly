package tv.xza.fly

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

import io.swagger.annotations.Api

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path('/api/common')
@Api('common')
class CommonTestResource {

    TopicService topicService

    @GET
    @Path('/getTopicReadCountAndInc/{id}')
    @Produces('text/plain')
    String getTopicReadCountAndInc(@PathParam("id")int id) {
        def topic = topicService.get(id)
        if (topic == null)
            return 0;
        def count = topic.readCount
        count ++
        topic.readCount ++
        topicService.save(topic)
        return count
    }
}

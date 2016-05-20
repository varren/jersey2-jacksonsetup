package ru.varren;

import ru.varren.model.TestBean;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class MyResource {

    @GET
    @MixIn
    @Produces(MediaType.APPLICATION_JSON )
    public Response  getShortName() {
        return Response.ok(demoObj()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON )
    public Response  postLongName() {
        return Response.ok(demoObj()).build();
    }

    private TestBean demoObj (){
        TestBean tb = new TestBean();
        tb.setName("Test");
        tb.setId(123);
        return tb;
    }

}
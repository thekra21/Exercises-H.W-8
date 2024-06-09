package org.example;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.example.dao.EmployeeIdDto;
import org.example.dto.EmpSDDto;

/**
 * Root resource (exposed at "myresource" path)
 */
@Singleton
@Path("myresource")
public class MyResource {

    int count = 0;
    @Context HttpHeaders headers ;
    @Context UriInfo uriInfo;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@HeaderParam("apikey")String apikey,
                        @CookieParam("username")String username
//                        @Context HttpHeaders headers ,
//                        @Context UriInfo uriInfo
    )
    {

        System.out.println(headers.getDate());
        System.out.println(headers.getLanguage());
        System.out.println(headers.getMediaType());
        System.out.println(headers.getCookies());
        System.out.println("________________________________");
        System.out.println(uriInfo.getAbsolutePath());
        System.out.println(uriInfo.getPathParameters());
        System.out.println(uriInfo.getQueryParameters());
        System.out.println(uriInfo.getQueryParameters().get("jobId"));


        count += 1;


        return "Got it! name: "+ count + username + " , apikey: "+ apikey ;
    }


//    @GET
//    @Path("/employees/{empId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public EmpSDDto getDate(@PathParam("empId") EmpSDDto  empId) {
//        return empId;
//    }

    @GET
    @Path("/employees/{empId}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeIdDto getDate(@PathParam("empId") EmployeeIdDto empId) {
        return empId;
    }


}

package org.example.Control;

import jakarta.ws.rs.core.*;
import org.example.dao.JobsDAO;
import jakarta.ws.rs.*;
import org.example.dto.JobsDto;
import org.example.dto.JobsFileDto;
import org.example.models.Jobs;

import java.net.URI;
import java.util.ArrayList;
@Path("/jobs")
public class JobsController {

    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    JobsDAO dao = new JobsDAO();

    @GET
    public ArrayList<Jobs> getAllJobs(
//            @QueryParam("minSalary")Double minSalary ,
//            @QueryParam("limit")Integer limit,
//            @QueryParam("offset")int offset

            @BeanParam JobsFileDto Fliter
    ) {

        try {
            return dao.selectAllJobs(Fliter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{jobId}")
    public Response getJob(@PathParam("jobId") int jobId) {

        try {
            Jobs jobs = dao.selectJobs(jobId);

            JobsDto dto = new JobsDto();
          dto.setJob_id(jobs.getJob_id());
          dto.setJobs_Title(jobs.getJobs_Title());
            dto.setMax_sal(jobs.getMax_sal());
            dto.setMin_sal(jobs.getMin_sal());


            AddLink(dto);

            return Response.ok(dto).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void AddLink(JobsDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();


        dto.addLink(selfUri.toString(),"self");
    }

    @DELETE
    @Path("{jobId}")
    public void deleteJob(@PathParam("jobId") int jobId) {

        try {
            dao.deleteJobs(jobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response insertJob(Jobs job) {

        try {
            dao.insertJobs(job);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(job.getJob_id() + "").build();
            return Response.status(Response.Status.CREATED)
                    .cookie(new NewCookie("username", "OOOOO"))
                    .header("Created by", "Thekra")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{jobId}")
    public void updateDepartment(@PathParam("jobId") int jobId, Jobs job) {

        try {
            job.setJob_id(jobId);
            dao.updateJobs(job);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

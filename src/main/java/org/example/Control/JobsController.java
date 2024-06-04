package org.example.Control;

import org.example.dao.JobsDAO;
import jakarta.ws.rs.*;
import org.example.models.Jobs;

import java.util.ArrayList;
@Path("/jobs")
public class JobsController {


    JobsDAO dao = new JobsDAO();

    @GET
    public ArrayList<Jobs> getAllJobs(
            @QueryParam("minSalary")Double minSalary ,
            @QueryParam("limit")Integer limit,
            @QueryParam("offset")int offset
    ) {

        try {
            return dao.selectAllJobs(minSalary,limit,offset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{jobId}")
    public Jobs getJob(@PathParam("jobId") int jobId) {

        try {
            return dao.selectJobs(jobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public void insertJob(Jobs job) {

        try {
            dao.insertJobs(job);

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

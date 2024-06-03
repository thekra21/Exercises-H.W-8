package Control;

import dao.JobsDAO;
import jakarta.ws.rs.*;
import models.Jobs;

import java.util.ArrayList;

public class JobsController {


    JobsDAO dao = new JobsDAO();

    @GET
    public ArrayList<Jobs> getAllJobs() {

        try {
            return dao.selectAllJobs();
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

package org.example.Control;

import org.example.dao.EmploysDAO;

import jakarta.ws.rs.*;
import org.example.models.Employs;

import java.util.ArrayList;
@Path("/employees")

public class EmployController {


    EmploysDAO dao = new EmploysDAO();

    @GET
    public ArrayList<Employs> getAllJobs() {

        try {
            return dao.selectAllEmploys();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{employId}")
    public Employs getEmploy(@PathParam("employId") int employId) {

        try {
            return dao.selectEmployees(employId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{employId}")
    public void deleteEmploys(@PathParam("employId") int employId) {

        try {
            dao.deleteEmployees(employId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public void insertEmploys(Employs employs) {

        try {
            dao.insertEmployees(employs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{employId}")
    public void updateEmploys(@PathParam("employId") int employId, Employs employs) {

        try {
            employs.setEmployee_id(employId);
            dao.updateEmplyees(employs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GET
//    public ArrayList<Employs> getAllEmployees(@PathParam("jobId") Integer jobId) {
//        System.out.println(jobId);
//        ArrayList<Employs> employees = new ArrayList<>();
//
//        return employees;
//    }

//    @POST
//    public void insertEmployee(@PathParam("jobId") Integer jobId, Employs emp) {
//
//        System.out.println(jobId);
//        System.out.println(emp);
//    }


}

package org.example.Control;

import jakarta.ws.rs.core.*;
import org.example.dao.EmploysDAO;

import jakarta.ws.rs.*;
import org.example.dto.EmployeesDto;
import org.example.dto.EmployeesFileDto;
import org.example.dto.JobsDto;
import org.example.dto.JobsFileDto;
import org.example.exceptions.DataNotFoundException;
import org.example.models.Employs;
import org.example.models.Jobs;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
@Path("/employees")

public class EmployController {

    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    EmploysDAO dao = new EmploysDAO();

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response getAllEmploys(  @BeanParam EmployeesFileDto Fliter) {

        try {
            GenericEntity<ArrayList<Employs>> jobs = new GenericEntity<ArrayList<Employs>>(dao.selectAllEmploys(Fliter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(jobs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(jobs)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(jobs, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{employId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
    public Response getEmploy(@PathParam("employId") int employId)throws SQLException {

        try {
            Employs emp = dao.selectEmployees(employId);
            if (emp == null) {
                throw new DataNotFoundException("employees " + employId + "Not found");
            }
            EmployeesDto dto = new EmployeesDto();

            dto.setEmployee_id(emp.getEmployee_id());
            dto.setFirst_name(emp.getFirst_name());
            dto.setLast_name(emp.getLast_name());
            dto.setEmail(emp.getEmail());
            dto.setPhone_number(emp.getPhone_number());
            dto.setHire_date(emp.getHire_date());
            dto.setJob_id(emp.getJob_id());
            dto.setSalary(emp.getSalary());
            dto.setManager_id(emp.getManger_id());
            dto.setDepartment_id(emp.getDepartment_id());


            return Response.ok(dto).build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        }

    private void AddLink(EmployeesDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();

        dto.addLink(selfUri.toString(), "self");

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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertEmploys(Employs employs) {

        try {
            dao.insertEmployees(employs);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(employs.getEmployee_id() + "").build();
            return Response
                    .created(uri)
                    .cookie(cookie)
                    .header("Created by", "Wael")
                    .build();

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

    @Path("{employee_id}/jobs")
    public JobsIdController getEmployeeController() {
        return new JobsIdController();
    }


}

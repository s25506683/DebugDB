package com.example.demo.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;

@Path("/student")

@RestController
public class StudentController {

	@Autowired
	StudentDAO studentDAO;
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response get(@PathParam("id") String id) {
		Student student = new Student();
		student.setStd_id(id);
		student = studentDAO.get(student);
		return Response.status(200).entity(student).build();
		
	}
}

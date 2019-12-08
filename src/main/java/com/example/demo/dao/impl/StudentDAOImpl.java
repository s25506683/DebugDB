package com.example.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;
@Repository
public class StudentDAOImpl implements StudentDAO {
	
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Student get(Student student)
	{
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		final String sql = "SELECT * FROM student WHERE std_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, student.getStd_id());
			rs = smt.executeQuery();

			student = new Student();
			if (rs.next()) {
				student.setStd_id(rs.getString("std_id"));
				student.setStd_password(rs.getString("std_password"));
				student.setStd_address(rs.getString("std_address"));
				student.setStd_mail(rs.getString("std_mail"));
			}
			smt.close();
			rs.close();
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return student;
	}

}

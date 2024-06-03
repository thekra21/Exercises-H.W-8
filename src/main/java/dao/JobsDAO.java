package dao;

import models.Jobs;

import java.sql.*;
import java.util.ArrayList;

public class JobsDAO {
    private static final String URL ="jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\myproject\\src\\main\\java\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from jobs";
    private static final String SELECT_ONE_JOBS = "select * from jobs where job_id = ?";
    private static final String INSERT_JOBS = "insert into jobs values (?, ?, ?,?)";
    private static final String UPDATE_JOBS = "update departments set job_titel = ?, min Salary = ?,max Salary=? where job_id = ?";
    private static final String DELETE_JOBS = "delete from jobs where job_id = ?";


    public void insertJobs(Jobs j) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_JOBS);
        st.setInt(1, j.getJob_id());
        st.setString(2, j.getJobs_Title());
        st.setDouble(3,j.getMin_sal());
        st.setDouble(3,j.getMax_sal());
        st.executeUpdate();
    }
    public void updateJobs(Jobs j) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOBS);
        st.setInt(4, j.getJob_id());
        st.setString(1, j.getJobs_Title());
        st.setDouble(2,j.getMin_sal());
        st.setDouble(3,j.getMax_sal());
        st.executeUpdate();
    }

    public void deleteJobs(int job_id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_JOBS);
        st.setInt(1, job_id);
        st.executeUpdate();
    }

    public Jobs selectJobs(int job_id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOBS);
        st.setInt(1, job_id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Jobs(rs);
        } else {
            return null;
        }
    }
        public ArrayList<Jobs> selectAllJobs() throws SQLException {
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement st = conn.prepareStatement(SELECT_ALL_JOBS);
            ResultSet rs = st.executeQuery();
            ArrayList<Jobs> jobs = new ArrayList<>();
            while (rs.next()) {
                jobs.add(new Jobs(rs));
            }

            return jobs;
        }
    }



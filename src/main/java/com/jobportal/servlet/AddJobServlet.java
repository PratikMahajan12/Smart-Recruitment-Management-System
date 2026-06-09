package com.jobportal.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jobportal.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddJobServlet")
public class AddJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String company = request.getParameter("company");
        String location = request.getParameter("location");

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                "INSERT INTO jobs(title,company,location) VALUES(?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, company);
            ps.setString(3, location);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                response.getWriter().println("Job Added Successfully");
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

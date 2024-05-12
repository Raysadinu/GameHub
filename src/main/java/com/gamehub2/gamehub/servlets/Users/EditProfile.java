package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;
@DeclareRoles({"USER","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@WebServlet(name = "EditProfile", value = "/EditProfile")
public class EditProfile extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditProfile.class.getName());
    @Inject
    UserDetailsBean userDetailsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, ServletException, IOException {
        LOG.info("Entered EditProfile.doGet method");

        List<UserDetailsDto> userList = userDetailsBean.findAllUserDetails();
        UserDetailsDto selectedUser = userDetailsBean.getUserDetailsByUsername(request.getParameter("username"), userList);
        request.setAttribute("user", selectedUser);

        request.getRequestDispatcher("/WEB-INF/userPages/editProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Entered EditProfile.doPost method");

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String bio = request.getParameter("bio");
        String phoneNumber = request.getParameter("phone-number");
        String gender = request.getParameter("gender");
        String location = request.getParameter("location");
        String nickname = request.getParameter("nickname");
        String birthDateString = request.getParameter("birthdate");
        LocalDate birthDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            birthDate = LocalDate.parse(birthDateString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing the date: " + e.getMessage());
            LOG.severe("Error converting from String to LocalDate in EditProfile.doPost");
        }

        UserDetailsDto newUserDetails = new UserDetailsDto(username, firstName, lastName, birthDate, phoneNumber, gender, bio, location, nickname);

        userDetailsBean.updateUserDetails(newUserDetails);

        LOG.info("Exited EditProfile.doPost method");
        response.sendRedirect(request.getContextPath() + "/Profile");
    }

}
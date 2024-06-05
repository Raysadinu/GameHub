package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet(name = "EditProfile", value = "/EditProfile")
public class EditProfile extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditProfile.class.getName());
    @Inject
    UserDetailsBean userDetailsBean;
    @Inject
    UserBean userBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, ServletException, IOException {
        LOG.info("Entered EditProfile.doGet method");

        List<UserDetailsDto> userList = userDetailsBean.findAllUserDetails();
        UserDetailsDto selectedUser = userDetailsBean.getUserDetailsByUsername(request.getParameter("username"), userList);
        request.setAttribute("user", selectedUser);

        request.getRequestDispatcher("/WEB-INF/userPages/editProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Entered EditProfile.doPost method");
        request.setCharacterEncoding("UTF-8");
        // Get form parameters
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String bio = request.getParameter("bio");
        String phoneNumber = request.getParameter("phone-number");
        String gender = request.getParameter("gender");
        String location = request.getParameter("location");
        String nickname = request.getParameter("nickname");
        String birthDateString = request.getParameter("birthdate");


        byte[] profilePicture = null;
        String imageFormat = request.getParameter("imageFormat");
        String imageName = request.getParameter("imageName");

        Part profilePicturePart = request.getPart("profilePicture");
        if (profilePicturePart != null && profilePicturePart.getSize() > 0) {
            profilePicture = profilePicturePart.getInputStream().readAllBytes();
        }

        if (username != null) {
            if (firstName != null) {
                userDetailsBean.updateFirstName(username, firstName);
            }
            if (lastName != null) {
                userDetailsBean.updateLastName(username, lastName);
            }
            if (nickname != null) {
                userDetailsBean.updateNickname(username, nickname);
            }
            if (location != null) {
                userDetailsBean.updateLocation(username, location);
            }
            if (birthDateString != null && !birthDateString.isEmpty()) {
                userDetailsBean.updateBirthDate(username, LocalDate.parse(birthDateString));
            }
            if (gender != null) {
                userDetailsBean.updateGender(username, gender);
            }
            if (phoneNumber != null) {
                userDetailsBean.updatePhoneNumber(username, phoneNumber);
            }
            if (bio != null) {
                userDetailsBean.updateBio(username, bio);
            }
            if (profilePicture != null) {
                userDetailsBean.updateProfilePicture(username, imageName, imageFormat,profilePicture);
            }
        }

        LOG.info("Exited EditProfile.doPost method");
        response.sendRedirect(request.getContextPath() + "/Profile");
    }

}
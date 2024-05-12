package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Users.UserBean;
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

import java.util.List;
import java.util.logging.Logger;
@DeclareRoles({"USER","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@WebServlet(name = "Profile", value = "/Profile")
public class Profile extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @Inject
    UserDetailsBean userDetailsBean;

    /*@Inject
    FriendshipBean friendshipBean;
    @Inject
    AdminBean adminBean;*/


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Profile.doGet " + request.getParameter("username") + " **\n");

        String username = request.getRemoteUser(); // Get the username of the logged-in user

        // Check if a "username" parameter is provided in the URL and use it instead of the logged-in username if provided
        String requestedUsername = request.getParameter("username");
        if (requestedUsername != null && !requestedUsername.isEmpty()) {
            username = requestedUsername;
        }

        // Retrieve all friendships
        //List<FriendshipDto> allFriendships = friendshipBean.findAllFriendships();


        // Retrieve friend list of the current user
        //List<FriendshipDto> friendList = friendshipBean.findFriendshipsByUser("Raysa", allFriendships);

        // Retrieve details of the current user
        List<UserDetailsDto> allUserDetails = userDetailsBean.findAllUserDetails();
        UserDetailsDto thisUser = userDetailsBean.getUserDetailsByUsername(username, allUserDetails);

        // Set the "user" attribute in the HTTP request to be used in the JSP page
        request.setAttribute("user", thisUser);

        //request.setAttribute("friendList", friendList);


        // Check if the profile being viewed is in the friend list
        /*boolean isFriend = false;
        for (FriendshipDto friendship : friendList) {
            if (friendship.getFriend().getUsername().equals(username)) {
                isFriend = true;
                break;
            }
        }*/
        //request.setAttribute("isFriend", isFriend);
        LOG.info("\n** Exited Profile.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/profile.jsp").forward(request, response);
    }
}
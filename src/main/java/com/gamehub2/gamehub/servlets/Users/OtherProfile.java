package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Others.FollowDto;
import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Other.FollowBean;
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


@WebServlet(name = "OtherProfile", value = "/OtherProfile")
public class OtherProfile extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OtherProfile.class.getName());
    @Inject
    UserDetailsBean userDetailsBean;

    @Inject
    FollowBean followBean;
    /*@Inject
    AdminBean adminBean;*/


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered OtherProfile.doGet " + request.getParameter("username") + " **\n");

        String username = request.getParameter("username");
        String logUser=request.getRemoteUser();

        List<UserDetailsDto> allUserDetails = userDetailsBean.findAllUserDetails();
        UserDetailsDto thisUser = userDetailsBean.getUserDetailsByUsername(username, allUserDetails);

        request.setAttribute("user", thisUser);

        List<FollowDto> allFollowers = followBean.findAllFollows();
        List<FollowDto> followersList = followBean.findFollowsByUser(logUser, allFollowers);
        request.setAttribute("followersList", followersList);


        boolean isFollower = false;
        request.setAttribute("isFollower", isFollower);
        for (FollowDto follower : followersList) {
            if (follower.getFollowed().getUsername().equals(username)) {
                isFollower = true;
                break;
            }
        }

        request.setAttribute("isFollower",isFollower);
        LOG.info("\n** Exited OthetProfile.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/otherProfile.jsp").forward(request, response);
    }
}
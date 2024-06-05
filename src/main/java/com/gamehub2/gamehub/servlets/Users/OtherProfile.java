package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.FollowDto;
import com.gamehub2.gamehub.dto.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Other.FollowBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "OtherProfile", value = "/OtherProfile")
public class OtherProfile extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OtherProfile.class.getName());
    @Inject
    UserDetailsBean userDetailsBean;

    @Inject
    FollowBean followBean;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered OtherProfile.doGet " + request.getParameter("username") + " **\n");

        HttpSession session = request.getSession();
        User logUser = (User) session.getAttribute("user");

        String username = request.getParameter("username");


        List<UserDetailsDto> allUserDetails = userDetailsBean.findAllUserDetails();
        UserDetailsDto thisUser = userDetailsBean.getUserDetailsByUsername(username, allUserDetails);

        request.setAttribute("user", thisUser);

        List<FollowDto> allFollowers = followBean.findAllFollows();
        List<FollowDto> followersList = followBean.findFollowsByUser(logUser.getUsername(), allFollowers);
        request.setAttribute("followersList", followersList);


        boolean isFollower = false;
        for (FollowDto follower : followersList) {
            if (follower.getFollowed() != null && follower.getFollowed().getUsername().equals(username)) {
                isFollower = true;
                break;
            }
        }
        request.setAttribute("userDetails", thisUser);
        request.setAttribute("isFollower",isFollower);
        LOG.info("\n** Exited OthetProfile.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/otherProfile.jsp").forward(request, response);
    }
}
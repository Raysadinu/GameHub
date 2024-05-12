package com.gamehub2.gamehub.ejb.Users;

import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.entities.Users.UserDetails;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Stateless
public class UserDetailsBean {

    private static final Logger LOG = Logger.getLogger(UserDetailsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDetailsDto> findAllUserDetails() {
        LOG.info("\n** Entered findAllUserDetails method **\n");
        try {
            TypedQuery<UserDetails> typedQuery = entityManager.createQuery("SELECT ud FROM UserDetails ud", UserDetails.class);
            List<UserDetails> userDetailsList = typedQuery.getResultList();
            LOG.info("\n** Exited findAllUsers method **\n");
            return copyUserDetailsToDto(userDetailsList);
        } catch (Exception ex) {
            LOG.severe("\nError in findAllUserDetails method! "+ex.getMessage()+"\n");
            throw new EJBException(ex);
        }
    }

    private List<UserDetailsDto> copyUserDetailsToDto(List<UserDetails> userDetailsList) {
        LOG.info("\n** Entered findAllUserDetails method with list size of: "+userDetailsList.size()+" **\n");

        List<UserDetailsDto> listToReturn = new ArrayList<>();

        for (UserDetails ud : userDetailsList) {
            UserDetailsDto userDetailsDtoTemp = new UserDetailsDto(ud.getUsername(), ud.getFirstName(), ud.getLastName(), ud.getBirthDate(), ud.getPhoneNumber(), ud.getGender(), ud.getBio(), ud.getLocation(), ud.getNickname());
            listToReturn.add(userDetailsDtoTemp);
        }

        LOG.info("\n** Exited copyUserDetailsToDto method. **\n");
        return listToReturn;
    }

    public UserDetailsDto getUserDetailsByUsername(String username, List<UserDetailsDto> userDetailsDtoList) {
        LOG.info("\n** Entered getUserDetailsByUsername method for the username: "+username+" and with a list size of: "+userDetailsDtoList.size()+" **\n");

        UserDetailsDto userToReturn = null;

        for (UserDetailsDto udd : userDetailsDtoList) {
            if (Objects.equals(udd.getUsername(), username)) {
                userToReturn = udd;
            }
        }

        if (userToReturn == null) {
            userToReturn = new UserDetailsDto(null,null,null, LocalDate.now(),null,null,null,null,null);
        }

        LOG.info("\n** Exited getUserDetailsByUsername method. **\n");
        return userToReturn;
    }
    public void updateUserDetails(UserDetailsDto newUserDetails){
        LOG.info("\n** Entered updateUserDetails method for the username: "+ newUserDetails.getUsername() +" **\n");

        UserDetails userDetails=entityManager.find(UserDetails.class,newUserDetails.getUsername());

        userDetails.setFirstName(newUserDetails.getFirstName());
        userDetails.setLastName(newUserDetails.getLastName());
        userDetails.setPhoneNumber(newUserDetails.getPhoneNumber());
        userDetails.setBio(newUserDetails.getBio());
        userDetails.setGender(newUserDetails.getGender());
        if(newUserDetails.getBirthDate() != null){
            userDetails.setBirthDate(newUserDetails.getBirthDate());
        }
        userDetails.setLocation(newUserDetails.getLocation());
        userDetails.setNickname(newUserDetails.getNickname());
        LOG.info("\n** Exited updateUserDetails method. **\n");
    }


}

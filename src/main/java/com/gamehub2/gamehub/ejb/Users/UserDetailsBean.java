package com.gamehub2.gamehub.ejb.Users;


import com.gamehub2.gamehub.dto.Users.UserDetailsDto;
import com.gamehub2.gamehub.entities.Others.Picture;
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
            UserDetailsDto userDetailsDtoTemp = new UserDetailsDto(ud.getUsername(), ud.getFirstName(), ud.getLastName(), ud.getBirthDate(), ud.getPhoneNumber(), ud.getGender(), ud.getBio(), ud.getLocation(), ud.getNickname(), ud.getProfilePicture());
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
            userToReturn = new UserDetailsDto(null,null,null, LocalDate.now(),null,null,null,null,null,null);
        }

        LOG.info("\n** Exited getUserDetailsByUsername method. **\n");
        return userToReturn;
    }

    //Update
    public void updateFirstName(String username, String firstName) {
        LOG.info("\n Entered updateFirstName method for the username: "+username+" \n");

        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setFirstName(firstName);

        LOG.info("\n Exited updateFirstName method. \n");
    }
    public void updateLastName(String username, String lastName) {
        LOG.info("\n Entered updateLastName method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setLastName(lastName);
        LOG.info("\n Exited updateLastName method. \n");
    }
    public void updateNickname(String username, String nickname) {
        LOG.info("\n Entered updateNickname method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setNickname(nickname);
        LOG.info("\n Exited updateNickname method. \n");
    }

    public void updateLocation(String username, String location) {
        LOG.info("\n Entered updateLocation method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setLocation(location);
        LOG.info("\n Exited updateLocation method. \n");
    }
    public void updateBirthDate(String username, LocalDate birthDate) {
        LOG.info("\n Entered updateBirthDate method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setBirthDate(birthDate);
        LOG.info("\n Exited updateBirthDate method. \n");
    }
    public void updatePhoneNumber(String username, String phoneNumber) {
        LOG.info("\n Entered updatePhoneNumber method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setPhoneNumber(phoneNumber);
        LOG.info("\n Exited updatePhoneNumber method. \n");
    }
    public void updateGender(String username, String gender) {
        LOG.info("\n Entered updateGender method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setGender(gender);
        LOG.info("\n Exited updateGender method. \n");
    }
    public void updateBio(String username, String bio) {
        LOG.info("\n Entered updateBio method for the username: "+username+" \n");
        UserDetails ud=entityManager.find(UserDetails.class,username);
        ud.setBio(bio);
        LOG.info("\n Exited updateBio method. \n");
    }
    public void updateProfilePicture(String username,String imageName, String imageFormat,byte[] imageData) {
        LOG.info("\n Entered updateProfilePicture method for the username: " + username + " \n");
        UserDetails ud = entityManager.find(UserDetails.class, username);
        if (ud != null) {
            Picture existingProfilePicture = ud.getProfilePicture();
            if (existingProfilePicture != null) {
                entityManager.remove(existingProfilePicture);
            }
            Picture picture = new Picture();
            picture.setImageName(imageName);
            picture.setImageFormat(imageFormat);
            picture.setImageData(imageData);
            picture.setType(Picture.PictureType.USER_PROFILE);
            ud.setProfilePicture(picture);
            entityManager.persist(picture);
            LOG.info("\n Profile picture updated successfully for username: " + username + " \n");
        } else {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
    }

}



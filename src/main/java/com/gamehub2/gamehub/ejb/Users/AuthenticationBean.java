package com.gamehub2.gamehub.ejb.Users;

import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Stateless
public class AuthenticationBean {
    private static final Logger LOG = Logger.getLogger(AuthenticationBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public User login(String username, String password) {
        User user = entityManager.find(User.class, username);
        if (user == null) {
            LOG.warning("User not found for username: " + username);
            return null;
        }
        String encryptedPassword = encryptPassword(password);
        LOG.info("Provided password: " + password);
        LOG.info("Encrypted password: " + encryptedPassword);
        LOG.info("Stored password: " + user.getPassword());
        if (user.getPassword().equals(encryptedPassword)) {
            return user;
        } else {
            LOG.warning("Incorrect password for user: " + username);
            return null;
        }
    }
    public String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.warning("SHA-256 algorithm not found.");
            return null;
        }
    }
}
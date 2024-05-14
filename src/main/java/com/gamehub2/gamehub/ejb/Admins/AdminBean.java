package com.gamehub2.gamehub.ejb.Admins;

import com.gamehub2.gamehub.common.Admins.AdminDto;
import com.gamehub2.gamehub.entities.Admins.Admin;
import com.gamehub2.gamehub.entities.Others.PaymentRequest;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class AdminBean {

    private static final Logger LOG = Logger.getLogger(AdminBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public static void newPaymentRequest(PaymentRequest pr) {
        Admin a = new Admin();
        a.getPaymentRequests().add(pr);
    }

    public List<AdminDto> findAllAdmins() {
        LOG.info("\n** Entering findAllAdmins method **\n");
        try {
            TypedQuery<Admin> typedQuery = entityManager.createQuery("SELECT u FROM Admin u", Admin.class);
            List<Admin> Admins = typedQuery.getResultList();
            LOG.info("\n** Exited findAllAdmins method **\n");
            return copyAdminsToDto(Admins);
        } catch (Exception ex) {
            LOG.info("\nError in findAllAdmins method! "+ex.getMessage()+"\n");
            throw new EJBException(ex);
        }
    }
    public List<AdminDto> copyAdminsToDto(List<Admin> Admins) {
        LOG.info("\n** Entered copyAdminsToDto method with list size: "+ Admins.size() +"**\n");

        List<AdminDto> listToReturn = new ArrayList<>();

        for(Admin currentAdmin : Admins) {
            AdminDto AdminDtoTemp = new AdminDto(currentAdmin.getUsername());
            listToReturn.add(AdminDtoTemp);
        }

        LOG.info("\n** Exited copyAdminsToDto method. **\n");
        return listToReturn;
    }
    public AdminDto findAdminByUsername(String username, List<AdminDto> AdminList) {
        LOG.info("\n** Entered findAdminByUsername method with the username: "+ username +"**\n");
        for (AdminDto Admin : AdminList) {
            if (Admin.getUsername().equals(username)) {
                LOG.info("\n** Exiting findAdminByUsername method. **\n");
                return Admin;
            }
        }
        LOG.info("\n** Exited findAdminByUsername method. **\n");
        return null;
    }
    public void addAdmin(String username) {
        LOG.info("\n** Entered addAdmin method with the username: " + username + " **\n");

        try {

            User user = entityManager.find(User.class, username);
            if (user != null) {
                // Verificați dacă utilizatorul nu este deja admin
                Admin existingAdmin = entityManager.find(Admin.class, username);
                if (existingAdmin == null) {
                    // Creează un nou obiect Admin și îl persistă în baza de date
                    Admin admin = new Admin();
                    admin.setUsername(username);
                    admin.setUser(user);

                    entityManager.persist(admin);
                    LOG.info("\n** Added user " + username + " as admin **\n");
                } else {
                    LOG.info("\n** User " + username + " is already an admin **\n");
                }
            } else {
                LOG.info("\n** User " + username + " does not exist **\n");
            }
        } catch (Exception ex) {
            LOG.info("\nError in addAdmin method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }

        LOG.info("\n** Exited addAdmin method **\n");
    }
    public boolean isAdmin(String username) {
        LOG.info("\n** Entered isAdmin method with the username: " + username + " **\n");

        try {

            Admin admin = entityManager.find(Admin.class, username);

            if (admin != null) {
                LOG.info("\n** User " + username + " is an admin **\n");
                return true;
            } else {
                LOG.info("\n** User " + username + " is not an admin **\n");
                return false;
            }
        } catch (Exception ex) {
            LOG.info("\nError in isAdmin method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public void deleteAdmin(String username) {
        LOG.info("\n** Entered deleteAdmin method with the username  " + username + " **\n");

        try {
            Admin Admin = entityManager.find(Admin.class, username);
            if (Admin != null) {
                entityManager.remove(Admin);
                LOG.info("\n** Admin with username  " + username + " not found **\n");
            }
        } catch (Exception ex) {
            LOG.info("\nError in deleteAdmin method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }

        LOG.info("\n** Exited deleteAdmin method **\n");
    }
}
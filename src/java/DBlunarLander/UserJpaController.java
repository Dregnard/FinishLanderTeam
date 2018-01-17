/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBlunarLander;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Fabian
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Users findUserByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            return (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

   
    public void create(Users user) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public Boolean existByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            List<Users> list = em.createNamedQuery("Users.findByUsername").setParameter("username", username).getResultList();
            return !list.isEmpty();

        } finally {
            em.close();
        }
    }

    public Boolean existPassword(String password) {
        EntityManager em = getEntityManager();
        try {
            List<Users> list = em.createNamedQuery("Users.findByPassword").setParameter("password", password).getResultList();
            return !list.isEmpty();

        } finally {
            em.close();
        }

    }

    public Users findUser(String username) {
        EntityManager em = getEntityManager();
        try {
            return (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
